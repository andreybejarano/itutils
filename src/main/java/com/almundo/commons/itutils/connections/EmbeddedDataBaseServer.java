package com.almundo.commons.itutils.connections;

public interface EmbeddedDataBaseServer {

    public void start_server();
    public void shutdown_server();
    public Integer getPort();
}
