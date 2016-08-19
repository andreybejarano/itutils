package com.almundo.commons.itutils.connections.db.impl;

import com.almundo.commons.itutils.connections.EmbeddedDataBaseServer;
import com.almundo.commons.itutils.utils.DataBasesPort;
import com.almundo.commons.itutils.utils.PortsManagerUtils;
import com.almundo.commons.itutils.utils.YamlUtils;

import redis.embedded.RedisServer;

public class EmbeddedRedisServerImpl implements EmbeddedDataBaseServer {

    private RedisServer server;
    private Integer port;
    private final static String REDIS_VERSION = "2.8.9";

    @Override
    public void startServer() {
        try {
            port = PortsManagerUtils.findFreePort();
            server = new RedisServer(REDIS_VERSION, port);
            server.start();
            YamlUtils.addDbPort(port, DataBasesPort.REDIS_PORT);
        } catch (Exception e) {
            throw new IllegalAccessError("Failed to init Redis Server");

        }

    }

    @Override
    public void shutdownServer() {
        try {
            server.stop();
        } catch (InterruptedException e) {
            throw new IllegalAccessError("Failed to stop Redis Server");
        }
    }

    public Integer getPort() {
        return port;
    }

}
