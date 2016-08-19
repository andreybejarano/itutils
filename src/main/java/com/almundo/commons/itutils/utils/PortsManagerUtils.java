package com.almundo.commons.itutils.utils;

import java.io.IOException;
import java.net.ServerSocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PortsManagerUtils {

    private final static Logger LOG = LoggerFactory.getLogger(PortsManagerUtils.class);

    public static Integer findFreePort() {
        ServerSocket socket = null;
        try {
            socket = new ServerSocket(0);
            return socket.getLocalPort();
        } catch (IOException e) {
            LOG.error("Failed to assign free port", e);
            throw new IllegalAccessError("Failed to assign free port");
        } finally {
            if (socket != null)
                try {
                    socket.close();
                } catch (IOException e) {
                    LOG.error("Failed to close socket", e);
                    throw new IllegalAccessError("Failed to close socket");
                }
        }

    }
}
