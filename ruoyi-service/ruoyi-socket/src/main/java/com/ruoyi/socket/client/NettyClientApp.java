package com.ruoyi.socket.client;

/**
 * NettyClientApp
 *
 * @author TP
 */
public class NettyClientApp {

    /**
     * @param args
     */
    public static void main(String[] args) {
        NettyClient nettyClient = new NettyClient();
        nettyClient.run();
    }

}
