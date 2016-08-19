package com.almundo.commons.itutils.connections;

import com.github.tomakehurst.wiremock.client.RemoteMappingBuilder;
import com.github.tomakehurst.wiremock.client.ScenarioMappingBuilder;

public interface WebServicesServer {

    public void enrollEndPoint(RemoteMappingBuilder<RemoteMappingBuilder, ScenarioMappingBuilder> endpointBuilder);

    public void startMockServer();

    public void shutdownServer();

    public Integer getPort();

}
