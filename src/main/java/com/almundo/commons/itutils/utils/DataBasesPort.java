package com.almundo.commons.itutils.utils;

public enum DataBasesPort {

    MONGO_PORT("mongoPort"), 
    HQLDB_PORT("hsqlPort"), 
    CASSANDRA_PORT("cassandraPort"),
    REDIS_PORT("redisPort");

    private String value;

    private DataBasesPort(String value) {

        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
