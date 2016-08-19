package com.almundo.commons.itutils.service;

import java.util.List;

import com.almundo.commons.itutils.utils.DataBases;

public interface DataBasesService {

    public void enrollDataBases(List<DataBases> dataBases);

    public void startServers();

    public void shutdownServers();

    public Integer getPort(DataBases dataBase);
}
