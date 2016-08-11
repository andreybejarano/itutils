package com.almundo.commons.itutils.connections.db.impl;

import org.cassandraunit.utils.EmbeddedCassandraServerHelper;

import com.almundo.commons.itutils.connections.EmbeddedDataBaseServer;
import com.almundo.commons.itutils.utils.DataBasesPort;
import com.almundo.commons.itutils.utils.PortsManagerUtils;
import com.almundo.commons.itutils.utils.YamlUtils;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

public class EmbeddedCassandraServerImpl implements EmbeddedDataBaseServer {
    
    private Session session;
    private Cluster cluster;
    private Integer port;
    
    public void start_server() {
        try {
            port = PortsManagerUtils.findFreePort();
            YamlUtils.replace_cassandra_file(port);
            EmbeddedCassandraServerHelper.startEmbeddedCassandra("cu-cassandra-ownport.yaml");
            Cluster cluster = Cluster.builder().addContactPoints("127.0.0.1").withPort(port).build();
            YamlUtils.addDbPort(port, DataBasesPort.CASSANDRA_PORT);
            session = cluster.newSession();
        } catch (Exception ex ) {
            throw new IllegalAccessError("Error to start Embedded Cassandra Service");
        }
        
    }
    
    public void shutdown_server() {
        session.close();
        cluster.close();
        EmbeddedCassandraServerHelper.cleanEmbeddedCassandra();
    }

    @Override
    public Integer getPort() {
        return this.port;
    }

}



