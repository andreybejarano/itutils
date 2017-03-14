package com.almundo.commons.itutils.connections.db.impl;

import com.almundo.commons.itutils.connections.EmbeddedDataBaseServer;
import com.almundo.commons.itutils.exception.ITUtilsException;
import com.almundo.commons.itutils.utils.PortsManagerUtils;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;

public class EmbeddedMongoServerImpl implements EmbeddedDataBaseServer {

    private Integer port;
    private MongodExecutable mongodExecutable;

    @Override
    public int startServer() {
        try {
            port = PortsManagerUtils.findFreePort();
            MongodStarter mongodStarter = MongodStarter.getDefaultInstance();
            IMongodConfig mongodConfig = new MongodConfigBuilder()
                    .version(Version.Main.V3_4)
                    .net(new Net(port, false))
                    .build();

            mongodExecutable = mongodStarter.prepare(mongodConfig);
            mongodExecutable.start();
            return port;
        } catch (Exception e) {
            throw new ITUtilsException(e);
        }

    }

    @Override
    public void shutdownServer() {
        mongodExecutable.stop();

    }

    @Override
    public void runScript(String scriptPath) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

}
