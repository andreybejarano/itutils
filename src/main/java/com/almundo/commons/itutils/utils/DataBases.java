package com.almundo.commons.itutils.utils;

public enum DataBases {


    MONGO("mongo"), 
    HQLDB("hsql"), 
    CASSANDRA("cassandra"),
    ELASTIC("elastic"),
    REDIS("redis");

    private String value;

    private DataBases(String value) {

        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
