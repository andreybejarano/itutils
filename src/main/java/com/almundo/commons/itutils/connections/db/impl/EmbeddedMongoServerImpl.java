package com.almundo.commons.itutils.connections.db.impl;

import java.io.IOException;

import com.almundo.commons.itutils.connections.EmbeddedDataBaseServer;
import com.almundo.commons.itutils.utils.DataBasesPort;
import com.almundo.commons.itutils.utils.PortsManagerUtils;
import com.almundo.commons.itutils.utils.YamlUtils;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;

public class EmbeddedMongoServerImpl implements EmbeddedDataBaseServer {

    private MongodExecutable mongodExecutable;
    private Integer mongoPort;

    @Override
    public void startServer() {
        mongodExecutable = prepareServer();
        try {
            mongodExecutable.start();
            YamlUtils.addDbPort(mongoPort, DataBasesPort.MONGO_PORT);
        } catch (IOException e) {
            throw new IllegalAccessError("Failed to initialize embedded mongo server");
        }

    }

    @Override
    public void shutdownServer() {
        mongodExecutable.stop();

    }

    private MongodExecutable prepareServer() {
        mongoPort = PortsManagerUtils.findFreePort();
        try {
            MongodStarter mongodStarter = MongodStarter.getDefaultInstance();
            IMongodConfig mongodConfig = new MongodConfigBuilder().version(Version.Main.V2_4)
                    .net(new Net(mongoPort, false))
                    .build();
            return mongodStarter.prepare(mongodConfig);
        } catch (Exception e) {
            throw new IllegalAccessError("Failed to prepare embedded mongo server");
        }
    }

    @Override
    public Integer getPort() {
        return this.mongoPort;
    }

}
