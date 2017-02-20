package com.almundo.commons.itutils.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import com.almundo.commons.itutils.service.DataBasesService;
import com.github.tomakehurst.wiremock.WireMockServer;

public class ITUtils {

    private DataBasesService dbService;
    private WireMockServer wireMockServer;
    private Map<DataBase, Integer> dbPorts;
    private Integer wireMockPort;
    private YamlUtils yamlUtils;

    public ITUtils(DataBasesService dbService) {
        this.dbService = dbService;
        this.dbPorts = new HashMap<DataBase, Integer>();
        this.yamlUtils = new YamlUtils();
    }

    public void startMongo() {
        this.startDataBase(DataBase.MONGO, Optional.empty());
    }

    public void startRedis() {
        this.startDataBase(DataBase.REDIS, Optional.empty());
    }

    public void startHsql(Optional<String> initScript) {
        this.startDataBase(DataBase.HQLDB, initScript);
    }

    public void startCassandra(Optional<String> initScript) {
        this.startDataBase(DataBase.CASSANDRA, initScript);
    }

    private void startDataBase(DataBase db, Optional<String> initScript) {
        int port = this.dbService.startServer(db, initScript);
        this.dbPorts.put(db, port);
    }

    public WireMockServer startWireMock() {
        int port = PortsManagerUtils.findFreePort();
        this.wireMockServer = new WireMockServer(port);
        this.wireMockPort = port;
        this.wireMockServer.start();
        return wireMockServer;
    }

    public WireMockServer getWireMockServer() {
        return this.wireMockServer;
    }

    public int getDBPort(DataBase dataBase) {
        return this.dbPorts.get(dataBase);
    }
    
    public int getWireMockPort() {
        return this.wireMockPort;
    }

    public void shutDownServers() {
        if(!Objects.isNull(this.dbService)){
            this.dbService.shutdownServers();
        }
        if(!Objects.isNull(this.wireMockServer)){
            this.wireMockServer.shutdown();
        }
    }

    public void generateIntegrationYml() {
        this.yamlUtils.generateIntegrationYml(dbPorts, wireMockPort);
    }

}
