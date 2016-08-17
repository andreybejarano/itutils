package com.almundo.commons.itutils.service.impl;

import com.almundo.commons.itutils.connections.WebServicesServer;
import com.almundo.commons.itutils.connections.ws.impl.WebServicesServerImpl;
import com.almundo.commons.itutils.service.WSService;
import com.almundo.commons.itutils.utils.YamlUtils;
import com.github.tomakehurst.wiremock.client.RemoteMappingBuilder;
import com.github.tomakehurst.wiremock.client.ScenarioMappingBuilder;

public class WSServiceImpl implements WSService {

    private WebServicesServer mockServer;

    public WSServiceImpl() {
        mockServer = new WebServicesServerImpl();
    }

    @Override
    public void startMockServers() {
       mockServer.startMockServer();
       YamlUtils.addWSPort(mockServer.getPort());
    }

   
    @Override
    public void shutdownMockServers() {
        mockServer.shutdownServer();

    }

    @Override
    public void enrollEndpoint(RemoteMappingBuilder<RemoteMappingBuilder, ScenarioMappingBuilder> endpointBuilder) {
        mockServer.enrollEndPoint(endpointBuilder);
        
    }

    @Override
    public Integer getWsPort() {
        return mockServer.getPort();
    }
    
    

}
