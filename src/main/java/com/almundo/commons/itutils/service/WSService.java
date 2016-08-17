package com.almundo.commons.itutils.service;

import com.github.tomakehurst.wiremock.client.RemoteMappingBuilder;
import com.github.tomakehurst.wiremock.client.ScenarioMappingBuilder;

public interface WSService {
    
    public void startMockServers();
    public void shutdownMockServers();
    public Integer getWsPort();
    public void enrollEndpoint(RemoteMappingBuilder<RemoteMappingBuilder, ScenarioMappingBuilder> endpointBuilder);
}
