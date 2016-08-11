package com.almundo.commons.itutils.service;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

public interface WSService {
    
    public void startMockServers();
    public void shutdownMockServers();
    public WireMockRule getMockServer();
}
