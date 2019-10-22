package com.ruoyi.socket.client;

import com.ruoyi.socket.protobuf.Msg;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.EventLoop;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;

import java.util.Date;

/**
 * NettyClientHandler
 *
 * @author TP
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter {


    /**
     * 循环次数
     */
    private int fcount = 1;

    /**
     * 建立连接时
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("建立连接时：" + new Date());
        ctx.fireChannelActive();
    }

    /**
     * 关闭连接时
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("关闭连接时：" + new Date());
        final EventLoop eventLoop = ctx.channel().eventLoop();
        NettyClient.nettyClient.doConnect(new Bootstrap(), eventLoop);
        super.channelInactive(ctx);
    }

    /**
     * 心跳请求处理 每4秒发送一次心跳请求;
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object obj) throws Exception {
        System.out.println("循环请求的时间：" + new Date() + "，次数" + fcount);
        if (obj instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) obj;
            if (IdleState.WRITER_IDLE.equals(event.state())) { // 如果写通道处于空闲状态,就发送心跳命令
//				UserInfo.UserMsg.Builder userState = UserInfo.UserMsg.newBuilder().setState(2);
//				ctx.channel().writeAndFlush(userState);
                fcount++;
            }
        }
    }

    /**
     * 业务逻辑处理
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 如果不是protobuf类型的数据
        if (!(msg instanceof com.ruoyi.socket.protobuf.Msg.MsgBody)) {
            System.out.println("未知数据!" + msg);
            return;
        }
        try {
            // 得到protobuf的数据
            Msg.MsgBody msgBody = (Msg.MsgBody) msg;
            // 进行相应的业务处理。。。
            System.out.println(
                    "客户端接受到的信息。编号:" + msgBody.getId() + ",时间:" + msgBody.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

}
