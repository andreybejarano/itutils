package com.almundo.commons.itutils.factory;

import org.junit.Before;
import org.junit.Test;

import com.almundo.commons.itutils.connections.EmbeddedCassandraService;

public class DataBasesFactoryTest {

    private EmbeddedCassandraService cassandraService;
    
    @Before
    public void init(){
        cassandraService = DatabasesFactory.getInstance().getCassandraService();
    }
    
    @Test
    public void start_cassandra(){
        cassandraService.init_cassandra();
        
        
    }
}
