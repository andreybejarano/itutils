package com.almundo.commons.itutils.utils;

public enum DataBasesPortsVariables {

    MONGO_PORT("mongoPort"), 
    HQLDB_PORT("hsqlPort"), 
    CASSANDRA_PORT("cassandraPort"),
    ELASTIC_PORT("elasticPort"),
    REDIS_PORT("redisPort");

    private String value;

    private DataBasesPortsVariables(String value) {

        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
