package com.almundo.commons.itutils.connections.impl;

import org.cassandraunit.utils.EmbeddedCassandraServerHelper;

import com.almundo.commons.itutils.connections.EmbeddedCassandraService;
import com.almundo.commons.itutils.utils.DataBasesPortsVariables;
import com.almundo.commons.itutils.utils.PortsManagerUtils;
import com.almundo.commons.itutils.utils.YamlUtils;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

public class EmbeddedCassandraServiceImpl implements EmbeddedCassandraService {
    
    private Session session;
    private Cluster cluster;
    
    public void init_cassandra() {
        try {
            Integer cassandra_port = PortsManagerUtils.findFreePort();
            YamlUtils.replace_cassandra_file(cassandra_port);
            EmbeddedCassandraServerHelper.startEmbeddedCassandra("cu-cassandra-ownport.yaml");
            Cluster cluster = Cluster.builder().addContactPoints("127.0.0.1").withPort(cassandra_port).build();
            YamlUtils.replace_ports_variables(cassandra_port, DataBasesPortsVariables.CASSANDRA_PORT);
            session = cluster.newSession();
        } catch (Exception ex ) {
            throw new IllegalAccessError("Error to start Embedded Cassandra Service");
        }
        
    }
    
    public void shutdown_cassandra() {
        session.close();
        cluster.close();
        EmbeddedCassandraServerHelper.cleanEmbeddedCassandra();
    }

}



