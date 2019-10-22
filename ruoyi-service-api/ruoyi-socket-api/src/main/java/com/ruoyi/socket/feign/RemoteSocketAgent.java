package com.ruoyi.socket.feign;

import com.ruoyi.common.constant.ServiceNameConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.socket.feign.factory.RemoteSocketAgentFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * NettyServer服务层
 *
 * @author TP
 * @date 2019-10-15
 */
@FeignClient(name = ServiceNameConstants.SOCKET_SERVICE, fallbackFactory = RemoteSocketAgentFallback.class)
public interface RemoteSocketAgent {
    @GetMapping("list")
    public R list();

}
