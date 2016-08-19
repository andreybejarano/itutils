package com.almundo.commons.itutils.connections;

public interface EmbeddedDataBaseServer {

    public void startServer();

    public void shutdownServer();

    public Integer getPort();
}
