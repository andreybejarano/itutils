package com.almundo.commons.itutils.connections.impl;

import com.almundo.commons.itutils.connections.EmbeddedServer;
import com.almundo.commons.itutils.utils.DataBasesPort;
import com.almundo.commons.itutils.utils.PortsManagerUtils;
import com.almundo.commons.itutils.utils.YamlUtils;

import redis.embedded.RedisServer;

public class EmbeddedRedisServerImpl implements EmbeddedServer {

    private RedisServer server;
    private final static String REDIS_VERSION = "2.8.9"; 

    @Override
    public void start_server() {
        try {
            Integer redis_port = PortsManagerUtils.findFreePort();
            server = new RedisServer(REDIS_VERSION, redis_port);
            server.start();
           YamlUtils.addPort(redis_port, DataBasesPort.REDIS_PORT);
        } catch (Exception e) {
            throw new IllegalAccessError("Failed to init Redis Server");
            
        }

    }

    @Override
    public void shutdown_server() {
        try {
            server.stop();
        } catch (InterruptedException e) {
            throw new IllegalAccessError("Failed to stop Redis Server");
        }

    }

}
