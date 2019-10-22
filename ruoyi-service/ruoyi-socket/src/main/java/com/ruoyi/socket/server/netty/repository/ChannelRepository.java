package com.ruoyi.socket.server.netty.repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Channel repository using HashMap
 */
public class ChannelRepository {
    private static ConcurrentMap<String, ChannelObject> channelCache = new ConcurrentHashMap<>();

    public static void put(String key, ChannelObject value) {
        channelCache.put(key, value);
    }

    public static Map<String, ChannelObject> getChannels() {
        return channelCache;
    }

    public static ChannelObject get(String key) {
        return channelCache.get(key);
    }

    public static void remove(String key) {
        channelCache.remove(key);
    }

    public static int size() {
        return channelCache.size();
    }
}
