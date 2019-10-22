package com.ruoyi.socket.server.netty.repository;

import io.netty.channel.Channel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public class ChannelObject implements Serializable {

    private Channel channel;
    private com.ruoyi.socket.protobuf.Msg.MsgBody msgBody;
    private Date connectTime;

    public ChannelObject(Channel channel, Date connectTime, com.ruoyi.socket.protobuf.Msg.MsgBody msgBody) {
        this.channel = channel;
        this.connectTime = connectTime;
        this.msgBody = msgBody;
    }
}
