package com.ruoyi.socket.vo;

import com.ruoyi.socket.protobuf.Msg;
import lombok.Data;

import java.util.Date;

@Data
public class ChannelVO {
    private String channelKey;
    private Msg.MsgBody msgBody;
    private Date connectTime;
}
