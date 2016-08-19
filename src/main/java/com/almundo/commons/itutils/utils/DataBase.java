package com.almundo.commons.itutils.utils;

public enum DataBase {

    MONGO("mongoPort"), HQLDB("hsqlPort"), CASSANDRA("cassandraPort"), REDIS("redisPort");

    private String portPlaceholder;

    private DataBase(String portPlaceholder) {

        this.portPlaceholder = portPlaceholder;
    }

    public String getPortPlaceholder() {
        return portPlaceholder;
    }

}
