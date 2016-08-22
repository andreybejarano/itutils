package com.almundo.commons.itutils.service;

import java.util.Optional;

import com.almundo.commons.itutils.utils.DataBase;

public interface DataBasesService {

    public int startServer(DataBase dataBase, Optional<String> scriptPath);
    

    public void shutdownServers();

}
