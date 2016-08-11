package com.almundo.commons.itutils.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cassandraunit.shaded.com.google.common.collect.Lists;

import com.almundo.commons.itutils.connections.WebServicesServer;
import com.almundo.commons.itutils.connections.ws.impl.WebServicesServerImpl;
import com.almundo.commons.itutils.service.WSService;
import com.almundo.commons.itutils.utils.YamlUtils;
import com.github.tomakehurst.wiremock.junit.WireMockRule;

public class WSServiceImpl implements WSService {

    private WebServicesServer mockServer;
    private Integer port;

    public WSServiceImpl() {
        mockServer = new WebServicesServerImpl();
    }

    @Override
    public void startMockServers() {
        mockServer.getMockServer().start();
        port = mockServer.getMockServer().port();
        YamlUtils.addWSPort(mockServer.getMockServer().port());
    }

    @Override
    public WireMockRule getMockServer() {
        return mockServer.getMockServer();
    }

    @Override
    public void shutdownMockServers() {
        mockServer.shutdownServer();

    }

}
