package com.almundo.commons.itutils.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.almundo.commons.itutils.connections.EmbeddedServer;
import com.almundo.commons.itutils.factory.DatabasesFactory;
import com.almundo.commons.itutils.service.DataBasesService;
import com.almundo.commons.itutils.utils.DataBases;
import com.almundo.commons.itutils.utils.YamlUtils;

public class DataBasesServiceImpl implements DataBasesService {

    
    private List<DataBases> dataBases;
    private Map<DataBases,EmbeddedServer> servers;
    
    public DataBasesServiceImpl(List<DataBases> dataBases){
        this.dataBases = dataBases;
        servers = new HashMap<DataBases,EmbeddedServer>();
        servers.put(DataBases.CASSANDRA, DatabasesFactory.getInstance().getCassandraService());
        servers.put(DataBases.HQLDB, DatabasesFactory.getInstance().getHsqlService());
        servers.put(DataBases.MONGO, DatabasesFactory.getInstance().getMongoService());
        servers.put(DataBases.REDIS, DatabasesFactory.getInstance().getRedisSerivce());
    }
    
    @Override
    public void start_servers() {
        dataBases.stream().forEach(dataBase->{
            servers.get(dataBase).start_server();
        });
        YamlUtils.generate_yml();
    }

    @Override
    public void shutdown_servers() {
        dataBases.stream().forEach(dataBase->{
            servers.get(dataBase).shutdown_server();
        });
        
    }

}
