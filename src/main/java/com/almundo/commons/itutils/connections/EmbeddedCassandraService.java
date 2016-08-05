package com.almundo.commons.itutils.connections;

public interface EmbeddedCassandraService {
    
    public void init_cassandra();
    public void shutdown_cassandra();
}
