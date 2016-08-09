package com.almundo.commons.itutils.factory;

import java.util.List;
import java.util.Objects;

import com.almundo.commons.itutils.service.DataBasesService;
import com.almundo.commons.itutils.service.impl.DataBasesServiceImpl;
import com.almundo.commons.itutils.utils.DataBases;

public class ServiceFactory {

    private static DataBasesService service;
    
    public static DataBasesService getDataBaseService(List<DataBases> dataBases){
        if(Objects.isNull(service)){
            service = new DataBasesServiceImpl(dataBases); 
        }
        return service;
    }
}
