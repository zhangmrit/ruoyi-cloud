package com.ruoyi.gateway.fiflt;

import java.net.URI;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.exception.ValidateCodeException;

import lombok.SneakyThrows;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 验证码处理
 */
@Component
public class ImgCodeFilter extends AbstractGatewayFilterFactory<ImgCodeFilter.Config>
{
    private final static String AUTH_URL = "/auth/login";

    @Autowired
    private StringRedisTemplate redisTemplate;

    public ImgCodeFilter()
    {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config)
    {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            URI uri = request.getURI();
            // 不是登录请求，直接向下执行
            if (!StringUtils.containsIgnoreCase(uri.getPath(), AUTH_URL))
            {
                return chain.filter(exchange);
            }
            try
            {
                String bodyStr = resolveBodyFromRequest(request);
                JSONObject bodyJson=JSONObject.parseObject(bodyStr);
                String code = (String) bodyJson.get("captcha");
                String randomStr = (String) bodyJson.get("randomStr");
                // 校验验证码
                checkCode(code, randomStr);
            }
            catch (Exception e)
            {
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
                response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
                String msg = JSON.toJSONString(R.error(e.getMessage()));
                DataBuffer bodyDataBuffer = response.bufferFactory().wrap(msg.getBytes());
                return response.writeWith(Mono.just(bodyDataBuffer));
            }
            return chain.filter(exchange);
        };
    }

    private String resolveBodyFromRequest(ServerHttpRequest serverHttpRequest)
    {
        // 获取请求体
        Flux<DataBuffer> body = serverHttpRequest.getBody();
        AtomicReference<String> bodyRef = new AtomicReference<>();
        body.subscribe(buffer -> {
            CharBuffer charBuffer = StandardCharsets.UTF_8.decode(buffer.asByteBuffer());
            DataBufferUtils.release(buffer);
            bodyRef.set(charBuffer.toString());
        });
        return bodyRef.get();
    }

    /**
     * 检查code
     *
     * @param request
     */
    @SneakyThrows
    private void checkCode(String code, String randomStr)
    {
        if (StringUtils.isBlank(code))
        {
            throw new ValidateCodeException("验证码不能为空");
        }
        if (StringUtils.isBlank(randomStr))
        {
            throw new ValidateCodeException("验证码不合法");
        }
        String key = Constants.DEFAULT_CODE_KEY + randomStr;
        String saveCode = redisTemplate.opsForValue().get(key);
        redisTemplate.delete(key);
        if (!code.equalsIgnoreCase(saveCode))
        {
            throw new ValidateCodeException("验证码不合法");
        }
    }

    public static class Config
    {
    }
}
