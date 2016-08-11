package com.almundo.commons.itutils.connections.ws.impl;

import com.almundo.commons.itutils.connections.WebServicesServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit.WireMockRule;

public class WebServicesServerImpl implements WebServicesServer {

    
    private WireMockRule server;
    
    public WebServicesServerImpl(){
        this.server = new WireMockRule(WireMockConfiguration.DYNAMIC_PORT);
    }
    
   @Override
    public void startMockServer() {
       server.start();
    }

   @Override
   public void shutdownServer() {
      server.shutdown();
       
   }
    
   @Override
    public WireMockRule getMockServer() {
       return this.server;
    }

    
    
    
}
