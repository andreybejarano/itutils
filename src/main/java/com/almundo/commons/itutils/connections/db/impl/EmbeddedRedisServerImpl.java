package com.almundo.commons.itutils.connections.db.impl;

import com.almundo.commons.itutils.connections.EmbeddedDataBaseServer;
import com.almundo.commons.itutils.utils.PortsManagerUtils;

import redis.embedded.RedisServer;

public class EmbeddedRedisServerImpl implements EmbeddedDataBaseServer {

    private RedisServer server;
    private Integer port;
    private final static String REDIS_VERSION = "2.8.9";

    @Override
    public int startServer() {
        try {
            port = PortsManagerUtils.findFreePort();
            server = new RedisServer(REDIS_VERSION, port);
            server.start();
            return port;
        } catch (Exception e) {
            throw new IllegalAccessError("Failed to init Redis Server");

        }

    }

    @Override
    public void shutdownServer() {
        try {
            server.stop();
        } catch (Exception e) {
            throw new IllegalAccessError("Failed to stop Redis Server");
        }
    }

    @Override
    public void runScript(String scriptPath) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

}
