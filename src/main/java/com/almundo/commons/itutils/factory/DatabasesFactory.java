package com.almundo.commons.itutils.factory;

import java.util.Objects;

import com.almundo.commons.itutils.connections.EmbeddedCassandraService;
import com.almundo.commons.itutils.connections.EmbeddedHsqlService;
import com.almundo.commons.itutils.connections.EmbeddedMongoService;
import com.almundo.commons.itutils.connections.impl.EmbeddedCassandraServiceImpl;
import com.almundo.commons.itutils.connections.impl.EmbeddedHsqlServiceImpl;
import com.almundo.commons.itutils.connections.impl.EmbeddedMongoServiceImpl;

public class DatabasesFactory {

    private static DatabasesFactory instance = new DatabasesFactory();;
    
    private EmbeddedMongoService mongoService =null; 
    private EmbeddedHsqlService hqlService =null; 
    private EmbeddedCassandraService cassandraService=null;
    
    public EmbeddedMongoService getMongoService(){
        if(Objects.isNull(mongoService)){
            mongoService = new EmbeddedMongoServiceImpl();
        }
        return mongoService;
    }
    
    public EmbeddedHsqlService getHsqlService(){
        if(Objects.isNull(hqlService)){
            hqlService = new EmbeddedHsqlServiceImpl();
        }
        return hqlService;
        
    }
    
    public EmbeddedCassandraService getCassandraService(){
        if(Objects.isNull(cassandraService)){
            cassandraService = new EmbeddedCassandraServiceImpl();
        }
        return cassandraService;
    }

    public static DatabasesFactory getInstance() {
        return instance;
    }

  
    
    
}
