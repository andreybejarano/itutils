package com.almundo.commons.itutils.factory;

import java.util.Objects;

import com.almundo.commons.itutils.connections.EmbeddedDataBaseServer;
import com.almundo.commons.itutils.connections.db.impl.EmbeddedCassandraServerImpl;
import com.almundo.commons.itutils.connections.db.impl.EmbeddedHsqlServerImpl;
import com.almundo.commons.itutils.connections.db.impl.EmbeddedMongoServerImpl;
import com.almundo.commons.itutils.connections.db.impl.EmbeddedRedisServerImpl;

public class DatabasesFactory {

    private static DatabasesFactory instance = new DatabasesFactory();

    private EmbeddedDataBaseServer mongoService = null;
    private EmbeddedDataBaseServer hqlService = null;
    private EmbeddedDataBaseServer cassandraService = null;
    private EmbeddedDataBaseServer redisService = null;

    public EmbeddedDataBaseServer getMongoService() {
        if (Objects.isNull(mongoService)) {
            mongoService = new EmbeddedMongoServerImpl();
        }
        return mongoService;
    }

    public EmbeddedDataBaseServer getHsqlService() {
        if (Objects.isNull(hqlService)) {
            hqlService = new EmbeddedHsqlServerImpl();
        }
        return hqlService;

    }

    public EmbeddedDataBaseServer getCassandraService() {
        if (Objects.isNull(cassandraService)) {
            cassandraService = new EmbeddedCassandraServerImpl();
        }
        return cassandraService;
    }

    public EmbeddedDataBaseServer getRedisSerivce() {
        if (Objects.isNull(redisService)) {
            redisService = new EmbeddedRedisServerImpl();
        }
        return redisService;
    }

    public static DatabasesFactory getInstance() {
        return instance;
    }

}
