package com.almundo.commons.itutils.gateway;

import java.util.List;

import com.almundo.commons.itutils.service.DataBasesService;
import com.almundo.commons.itutils.service.WSService;
import com.almundo.commons.itutils.utils.DataBases;
import com.almundo.commons.itutils.utils.YamlUtils;
import com.github.tomakehurst.wiremock.junit.WireMockRule;

public class ITMocksGateway {

    private DataBasesService dbService;
    private WSService wsService;
   
    
    public ITMocksGateway(DataBasesService dbService, WSService wsService){
        this.dbService = dbService;
        this.wsService = wsService;
    }
    
    public void startEmbeddedDbs(List<DataBases> dataBases){
        dbService.enrollDataBases(dataBases);
        dbService.startServers();
    }
    
    public  void startWSMocks(){
          wsService.startMockServers();
    }
    
    public void shutdownDbs(){
        dbService.shutdownServers();
    }
    
       
    public void shutdownWsMocks(){
        wsService.shutdownMockServers();
    }
    
    public WireMockRule getWSMockServer(){
        return wsService.getMockServer();
    }
    
    public Integer dbPort(DataBases dataBase){
        return dbService.getPort(dataBase);
    }
    
    public void generateIntegrationYml(){
        YamlUtils.generateIntegrationYml();
    }
    
   
}
