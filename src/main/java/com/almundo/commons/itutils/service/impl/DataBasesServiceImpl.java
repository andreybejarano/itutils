package com.almundo.commons.itutils.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.almundo.commons.itutils.connections.EmbeddedDataBaseServer;
import com.almundo.commons.itutils.factory.DatabasesFactory;
import com.almundo.commons.itutils.service.DataBasesService;
import com.almundo.commons.itutils.utils.DataBase;
import com.google.common.collect.Lists;

public class DataBasesServiceImpl implements DataBasesService {

    private Map<DataBase, EmbeddedDataBaseServer> dataBasesServers;
    private List<EmbeddedDataBaseServer> runningServers;

    public DataBasesServiceImpl() {
        dataBasesServers = new HashMap<>();
        dataBasesServers.put(DataBase.CASSANDRA, DatabasesFactory.getInstance().getCassandraService());
        dataBasesServers.put(DataBase.HQLDB, DatabasesFactory.getInstance().getHsqlService());
        dataBasesServers.put(DataBase.MONGO, DatabasesFactory.getInstance().getMongoService());
        dataBasesServers.put(DataBase.REDIS, DatabasesFactory.getInstance().getRedisSerivce());

        runningServers = Lists.newLinkedList();
    }

    @Override
    public int startServer(DataBase dataBase, Optional<String> scriptPath) {
        
        EmbeddedDataBaseServer dbServer = dataBasesServers.get(dataBase);
        int port = dbServer.startServer();
        scriptPath.ifPresent(sp -> dbServer.runScript(sp));
        runningServers.add(dbServer);
        return port;
    }

    @Override
    public void shutdownServers() {
        runningServers.stream().forEach(dbServer -> dbServer.shutdownServer());
    }

}
