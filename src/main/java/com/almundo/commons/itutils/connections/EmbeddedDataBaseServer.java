package com.almundo.commons.itutils.connections;

public interface EmbeddedDataBaseServer {

    public int startServer();

    public void shutdownServer();

    public void runScript(String scriptPath);
}
