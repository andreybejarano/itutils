package com.almundo.commons.itutils.factory;

import java.util.Objects;

import com.almundo.commons.itutils.connections.EmbeddedServer;
import com.almundo.commons.itutils.connections.impl.EmbeddedCassandraServerImpl;
import com.almundo.commons.itutils.connections.impl.EmbeddedHsqlServerImpl;
import com.almundo.commons.itutils.connections.impl.EmbeddedMongoServerImpl;
import com.almundo.commons.itutils.connections.impl.EmbeddedRedisServerImpl;

public class DatabasesFactory {

    private static DatabasesFactory instance = new DatabasesFactory();;
    
    private EmbeddedServer mongoService =null; 
    private EmbeddedServer hqlService =null; 
    private EmbeddedServer cassandraService=null;
    private EmbeddedServer redisService = null;
    
    public EmbeddedServer getMongoService(){
        if(Objects.isNull(mongoService)){
            mongoService = new EmbeddedMongoServerImpl();
        }
        return mongoService;
    }
    
    public EmbeddedServer getHsqlService(){
        if(Objects.isNull(hqlService)){
            hqlService = new EmbeddedHsqlServerImpl();
        }
        return hqlService;
        
    }
    
    public EmbeddedServer getCassandraService(){
        if(Objects.isNull(cassandraService)){
            cassandraService = new EmbeddedCassandraServerImpl();
        }
        return cassandraService;
    }
    
    public EmbeddedServer getRedisSerivce(){
        if(Objects.isNull(redisService)){
            redisService = new EmbeddedRedisServerImpl();
        }
        return redisService;
    }

    public static DatabasesFactory getInstance() {
        return instance;
    }

  
    
    
}
