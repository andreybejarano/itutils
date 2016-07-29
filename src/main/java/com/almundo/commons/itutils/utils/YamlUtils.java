package com.almundo.commons.itutils.utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class YamlUtils {

    private final static Logger LOG = LoggerFactory.getLogger(YamlUtils.class);

    public static void replace_ports_variables(Integer port, DataBasesPortsVariables portVariable){
        try {
            StringBuilder urlBuilder = new StringBuilder();
            urlBuilder.append(System.getProperty("user.dir"));
            urlBuilder.append("/src/test/resources/application.yml");
            Path path = Paths.get(urlBuilder.toString());
            Charset charset = StandardCharsets.UTF_8;
            String content = new String(Files.readAllBytes(path), charset);
            content = content.replaceAll(portVariable.getValue(),port.toString());
            Files.write(path, content.getBytes(charset));
        } catch (IOException e) {
           LOG.error("Failed to replace ports variables");
        }
    
    }   
}
