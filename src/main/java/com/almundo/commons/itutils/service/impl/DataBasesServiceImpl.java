package com.almundo.commons.itutils.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.almundo.commons.itutils.connections.EmbeddedDataBaseServer;
import com.almundo.commons.itutils.factory.DatabasesFactory;
import com.almundo.commons.itutils.service.DataBasesService;
import com.almundo.commons.itutils.utils.DataBases;
import com.almundo.commons.itutils.utils.YamlUtils;

public class DataBasesServiceImpl implements DataBasesService {

    private List<DataBases> dataBases;
    private Map<DataBases, EmbeddedDataBaseServer> servers;

    public DataBasesServiceImpl() {
        servers = new HashMap<DataBases, EmbeddedDataBaseServer>();
        servers.put(DataBases.CASSANDRA, DatabasesFactory.getInstance().getCassandraService());
        servers.put(DataBases.HQLDB, DatabasesFactory.getInstance().getHsqlService());
        servers.put(DataBases.MONGO, DatabasesFactory.getInstance().getMongoService());
        servers.put(DataBases.REDIS, DatabasesFactory.getInstance().getRedisSerivce());
    }

    @Override
    public void startServers() {
        dataBases.stream().forEach(dataBase -> {
            servers.get(dataBase).startServer();
        });
    }

    @Override
    public void shutdownServers() {
        dataBases.stream().forEach(dataBase -> {
            servers.get(dataBase).shutdownServer();
        });

    }

    @Override
    public void enrollDataBases(List<DataBases> dataBases) {
        this.dataBases = dataBases;

    }

    @Override
    public Integer getPort(DataBases dataBase) {
        return servers.get(dataBase).getPort();
    }

}
