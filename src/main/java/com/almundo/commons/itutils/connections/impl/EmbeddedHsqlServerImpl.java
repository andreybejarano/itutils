package com.almundo.commons.itutils.connections.impl;

import org.hsqldb.Server;

import com.almundo.commons.itutils.connections.EmbeddedServer;
import com.almundo.commons.itutils.utils.DataBasesPort;
import com.almundo.commons.itutils.utils.PortsManagerUtils;
import com.almundo.commons.itutils.utils.YamlUtils;

public class EmbeddedHsqlServerImpl implements EmbeddedServer {

    private Server server;
    private Integer hsql_port;

    @Override
    public void start_server() {
        server = new Server();
        hsql_port = PortsManagerUtils.findFreePort();
        server.setPort(hsql_port);
        server.setDatabaseName(0, "xdb");
        server.setDatabasePath(0, "jdbc:hsqldb:hsql://localhost/xdb");
        server.setAddress("127.0.0.1");
        server.start();
        YamlUtils.addPort(hsql_port, DataBasesPort.HQLDB_PORT);
        
    }

    @Override
    public void shutdown_server() {
        server.shutdown();

    }

}
