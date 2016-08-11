package com.almundo.commons.itutils.connections;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

public interface WebServicesServer {

    public void startMockServer();
    public void shutdownServer();
    public WireMockRule getMockServer();
    
}
