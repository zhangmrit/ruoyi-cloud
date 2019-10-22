package com.ruoyi.socket.feign.factory;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.socket.feign.RemoteSocketAgent;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RemoteSocketAgentFallback implements FallbackFactory<RemoteSocketAgent> {
    @Override
    public RemoteSocketAgent create(Throwable throwable) {
        log.error(throwable.getMessage());
        return new RemoteSocketAgent() {

            @Override
            public R list() {
                return R.error();
            }

        };
    }
}
