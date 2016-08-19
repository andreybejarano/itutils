package com.almundo.commons.itutils.factory;

import java.util.Objects;

import com.almundo.commons.itutils.service.impl.DataBasesServiceImpl;
import com.almundo.commons.itutils.utils.ITUtils;

public class ITUtilsFactory {

    private static ITUtils gateway;

    public static ITUtils getITUtils() {
        if (Objects.isNull(gateway)) {
            gateway = new ITUtils(new DataBasesServiceImpl());
        }
        return gateway;
    }
}
