package com.almundo.commons.itutils.connections.ws.impl;

import com.almundo.commons.itutils.connections.WebServicesServer;
import com.almundo.commons.itutils.utils.PortsManagerUtils;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.RemoteMappingBuilder;
import com.github.tomakehurst.wiremock.client.ScenarioMappingBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;

public class WebServicesServerImpl implements WebServicesServer {

    private WireMockServer server;
    private Integer port;

    public WebServicesServerImpl() {
        this.port = PortsManagerUtils.findFreePort();
        this.server = new WireMockServer(port);
        WireMock.configureFor(port);

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
    public void enrollEndPoint(RemoteMappingBuilder<RemoteMappingBuilder, ScenarioMappingBuilder> endpointBuilder) {
        WireMock.stubFor(endpointBuilder);

    }

    public Integer getPort() {
        return port;
    }

}
