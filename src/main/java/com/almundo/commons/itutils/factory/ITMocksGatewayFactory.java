package com.almundo.commons.itutils.factory;

import java.util.Objects;

import com.almundo.commons.itutils.gateway.ITMocksGateway;
import com.almundo.commons.itutils.service.impl.DataBasesServiceImpl;
import com.almundo.commons.itutils.service.impl.WSServiceImpl;

public class ITMocksGatewayFactory {

    private static ITMocksGateway gateway;
    
    public static ITMocksGateway getITMocksGateway(){
        if(Objects.isNull(gateway)){
            gateway = new ITMocksGateway(new DataBasesServiceImpl(), new WSServiceImpl());
        }
        return gateway;
    }
}
