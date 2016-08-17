package com.almundo.commons.itutils.connections.db.impl;

import org.cassandraunit.utils.EmbeddedCassandraServerHelper;

import com.almundo.commons.itutils.connections.EmbeddedDataBaseServer;
import com.almundo.commons.itutils.utils.DataBasesPort;
import com.almundo.commons.itutils.utils.YamlUtils;

public class EmbeddedCassandraServerImpl implements EmbeddedDataBaseServer {
    
  
    private Integer port;
    
    public void start_server() {
        try {
            EmbeddedCassandraServerHelper.startEmbeddedCassandra(EmbeddedCassandraServerHelper.CASSANDRA_RNDPORT_YML_FILE);
            port = EmbeddedCassandraServerHelper.getNativeTransportPort();
            YamlUtils.addDbPort(port, DataBasesPort.CASSANDRA_PORT);
        } catch (Exception ex ) {
            throw new IllegalAccessError("Error to start Embedded Cassandra Service");
        }
        
    }
    
    public void shutdown_server() {
        EmbeddedCassandraServerHelper.cleanEmbeddedCassandra();
    }

    @Override
    public Integer getPort() {
        return this.port;
    }

}



