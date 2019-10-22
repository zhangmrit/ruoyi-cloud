package com.ruoyi.socket.server.netty;

public class NettyServerStartFailedException extends RuntimeException {
    public NettyServerStartFailedException() {
    }

    public NettyServerStartFailedException(String message) {
        super(message);
    }

    public NettyServerStartFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public NettyServerStartFailedException(Throwable cause) {
        super(cause);
    }

    public NettyServerStartFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
