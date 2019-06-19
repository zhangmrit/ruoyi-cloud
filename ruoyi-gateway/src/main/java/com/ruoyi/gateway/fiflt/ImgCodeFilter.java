/*
 *  Copyright (c) 2019-2020, 冷冷 (wangiegie@gmail.com).
 *  <p>
 *  Licensed under the GNU Lesser General Public License 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  <p>
 * https://www.gnu.org/licenses/lgpl.html
 *  <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ruoyi.gateway.fiflt;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.exception.ValidateCodeException;

import lombok.SneakyThrows;
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
            // 不是登录请求，直接向下执行
            if (!StringUtils.containsIgnoreCase(request.getURI().getPath(), AUTH_URL))
            {
                return chain.filter(exchange);
            }
            try
            {
                // 校验验证码
                checkCode(request);
            }
            catch (Exception e)
            {
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
                String msg = JSON.toJSONString(R.error(e.getMessage()));
                DataBuffer bodyDataBuffer = response.bufferFactory().wrap(msg.getBytes());
                return response.writeWith(Mono.just(bodyDataBuffer));
            }
            return chain.filter(exchange);
        };
    }

    /**
     * 检查code
     *
     * @param request
     */
    @SneakyThrows
    private void checkCode(ServerHttpRequest request)
    {
        String code = request.getQueryParams().getFirst("code");
        if (StringUtils.isBlank(code))
        {
            throw new ValidateCodeException("验证码不能为空");
        }
        String randomStr = request.getQueryParams().getFirst("randomStr");
        if (StringUtils.isBlank(randomStr))
        {
            randomStr = request.getQueryParams().getFirst("mobile");
        }
        String key = Constants.DEFAULT_CODE_KEY + randomStr;
        if (!redisTemplate.hasKey(key))
        {
            throw new ValidateCodeException("验证码不合法");
        }
        Object codeObj = redisTemplate.opsForValue().get(key);
        if (codeObj == null)
        {
            throw new ValidateCodeException("验证码不合法");
        }
        String saveCode = codeObj.toString();
        if (StringUtils.isBlank(saveCode))
        {
            redisTemplate.delete(key);
            throw new ValidateCodeException("验证码不合法");
        }
        if (!StringUtils.equals(saveCode, code))
        {
            redisTemplate.delete(key);
            throw new ValidateCodeException("验证码不合法");
        }
        redisTemplate.delete(key);
    }

    public static class Config
    {
    }
}
