package com.ruoyi.socket.server.netty.handler;

import com.ruoyi.socket.protobuf.Msg;
import com.ruoyi.socket.server.netty.repository.ChannelObject;
import com.ruoyi.socket.server.netty.repository.ChannelRepository;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Date;

/**
 * 服务端业务逻辑
 *
 * @author TP
 */
@Component
@Slf4j
@RequiredArgsConstructor
@ChannelHandler.Sharable
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

//    /**
//     * 空闲次数
//     */
//    private int idle_count = 1;
//    /**
//     * 发送次数
//     */
//    private int count = 1;


    /**
     * 建立连接时
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Assert.notNull(ChannelRepository.getChannels(), "[Assertion failed] - ChannelRepository is required; it must not be null");

        ctx.fireChannelActive();
        String channelKey = ctx.channel().remoteAddress().toString();
        ChannelRepository.put(channelKey, new ChannelObject(ctx.channel(), new Date(), null));
        super.channelActive(ctx);

        if (log.isDebugEnabled()) {
            log.debug("连接的客户端地址:" + ctx.channel().remoteAddress());
            log.debug("Binded Channel Count is {}", ChannelRepository.size());
        }
    }

    /**
     * 超时处理 如果5秒没有接受客户端的心跳，就触发; 如果超过两次，则直接关闭;
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object obj) throws Exception {
        if (obj instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) obj;
//            if (IdleState.READER_IDLE.equals(event.state())) { // 如果读通道处于空闲状态，说明没有接收到心跳命令
//                System.out.println("已经5秒没有接收到客户端的信息了");
//                if (idle_count > 1) {
//                    System.out.println("关闭这个不活跃的channel");
//                    ctx.channel().close();
//                }
//                idle_count++;
//            }
        } else {
            super.userEventTriggered(ctx, obj);
        }
    }

    /**
     * 业务逻辑处理
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("连接的客户端地址:" + ctx.channel().remoteAddress());
            log.debug("Binded Channel Count is {}", ChannelRepository.size());
        }
        try {
            // 如果是protobuf类型的数据
            if (msg instanceof Msg.MsgBody) {
                Msg.MsgBody msgBody = (Msg.MsgBody) msg;
                switch (msgBody.getEvent()) {
                    case STATE:
                        String channelKey = ctx.channel().remoteAddress().toString();
                        ChannelObject old = ChannelRepository.get(channelKey);
                        old.setMsgBody(msgBody);
                        ChannelRepository.put(channelKey, old);
//                        ctx.writeAndFlush(msgBody);
                        break;
                    default:
                        log.warn("未知命令!" + msg);
                }
            } else {
                log.warn("未知数据!" + msg);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
//        count++;
    }

    /**
     * 异常处理
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        Assert.notNull(ctx, "[Assertion failed] - ChannelHandlerContext is required; it must not be null");

        String channelKey = ctx.channel().remoteAddress().toString();
        ChannelRepository.remove(channelKey);
        if (log.isDebugEnabled()) {
            log.debug("Binded Channel Count is " + ChannelRepository.size());
            log.debug("断开的客户端地址:" + ctx.channel().remoteAddress());
        }
    }
}
