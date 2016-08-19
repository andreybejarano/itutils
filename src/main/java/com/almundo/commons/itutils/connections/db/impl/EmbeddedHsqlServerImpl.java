package com.almundo.commons.itutils.connections.db.impl;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.hsqldb.Server;

import com.almundo.commons.itutils.connections.EmbeddedDataBaseServer;
import com.almundo.commons.itutils.exception.ITUtilsException;
import com.almundo.commons.itutils.utils.PortsManagerUtils;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;

public class EmbeddedHsqlServerImpl implements EmbeddedDataBaseServer {

    private Server server;
    private Integer port;

    @Override
    public int startServer() {
        server = new Server();
        port = PortsManagerUtils.findFreePort();
        server.setPort(port);
        server.setDatabaseName(0, "xdb");
        server.setDatabasePath(0, "mem:xdb");
        server.setAddress("127.0.0.1");
        server.start();
        return port;
    }

    @Override
    public void shutdownServer() {
        server.shutdown();

    }

    @Override
    public void runScript(String scriptPath) {

        try (
                Connection connection = DriverManager.getConnection("jdbc:hsqldb:mem:xdb", "sa", "");
                Statement statement = connection.createStatement()) {

            URL url = Resources.getResource(scriptPath);
            String stringScript = Resources.toString(url, Charsets.UTF_8);

            for (String scriptSentence : stringScript.split(";")) {
                statement.executeUpdate(scriptSentence);
            }
            
        } catch (Exception e) {
            throw new ITUtilsException(e);
        }

    }

}
