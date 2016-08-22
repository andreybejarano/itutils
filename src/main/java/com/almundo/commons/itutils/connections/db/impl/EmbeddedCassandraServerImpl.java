package com.almundo.commons.itutils.connections.db.impl;

import java.net.URL;

import org.cassandraunit.utils.EmbeddedCassandraServerHelper;

import com.almundo.commons.itutils.connections.EmbeddedDataBaseServer;
import com.almundo.commons.itutils.exception.ITUtilsException;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;

public class EmbeddedCassandraServerImpl implements EmbeddedDataBaseServer {

    private Integer port;

    @Override
    public int startServer() {
        try {
            EmbeddedCassandraServerHelper
                    .startEmbeddedCassandra(EmbeddedCassandraServerHelper.CASSANDRA_RNDPORT_YML_FILE);
            port = EmbeddedCassandraServerHelper.getNativeTransportPort();
            return port;
        } catch (Exception ex) {
            throw new IllegalAccessError("Error to start Embedded Cassandra Service");
        }

    }

    @Override
    public void shutdownServer() {
        EmbeddedCassandraServerHelper.cleanEmbeddedCassandra();
    }

    @Override
    public void runScript(String scriptPath) {

        try (
                Cluster cluster = Cluster.builder().addContactPoints("127.0.0.1").withPort(port).build();
                Session session = cluster.newSession()) {

            URL url = Resources.getResource(scriptPath);
            String stringScript = Resources.toString(url, Charsets.UTF_8);

            for (String scriptSentence : stringScript.split(";")) {
                session.execute(scriptSentence);
            }

        } catch (Exception e) {
            throw new ITUtilsException(e);
        }

    }

}
