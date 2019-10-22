package com.ruoyi.socket.controller;

import com.ruoyi.common.auth.annotation.HasPermissions;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.socket.server.netty.repository.ChannelObject;
import com.ruoyi.socket.server.netty.repository.ChannelRepository;
import com.ruoyi.socket.vo.ChannelVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * SocketAgent 提供者
 *
 * @author TP
 * @date 2019-10-14
 */
@Slf4j
@RestController
@RequestMapping("agent")
public class SocketAgentController extends BaseController {

    /**
     * 查询Agent列表
     */
    @HasPermissions("socket:agent:view")
    @GetMapping("list")
    public R list() {
        List<ChannelVO> list = new ArrayList<>();
        for (Map.Entry<String, ChannelObject> entry : ChannelRepository.getChannels().entrySet()) {
            ChannelVO channelVO = new ChannelVO();
            channelVO.setChannelKey(entry.getKey());
            channelVO.setMsgBody(entry.getValue().getMsgBody());
            channelVO.setConnectTime(entry.getValue().getConnectTime());
            list.add(channelVO);
        }
        return result(list);
    }

}
