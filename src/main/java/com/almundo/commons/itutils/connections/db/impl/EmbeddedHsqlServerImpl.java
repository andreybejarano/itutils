package com.almundo.commons.itutils.connections.db.impl;

import org.hsqldb.Server;

import com.almundo.commons.itutils.connections.EmbeddedDataBaseServer;
import com.almundo.commons.itutils.utils.DataBasesPort;
import com.almundo.commons.itutils.utils.PortsManagerUtils;
import com.almundo.commons.itutils.utils.YamlUtils;

public class EmbeddedHsqlServerImpl implements EmbeddedDataBaseServer {

    private Server server;
    private Integer hsqlPort;

    @Override
    public void start_server() {
        server = new Server();
        hsqlPort = PortsManagerUtils.findFreePort();
        server.setPort(hsqlPort);
        server.setDatabaseName(0, "xdb");
        server.setDatabasePath(0, "jdbc:hsqldb:hsql://localhost/xdb");
        server.setAddress("127.0.0.1");
        server.start();
        YamlUtils.addDbPort(hsqlPort, DataBasesPort.HQLDB_PORT);
        
    }

    @Override
    public void shutdown_server() {
        server.shutdown();

    }

    @Override
    public Integer getPort() {
        return this.hsqlPort;
    }

}
