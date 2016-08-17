package com.almundo.commons.itutils.utils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class YamlUtils {

    private final static Logger LOG = LoggerFactory.getLogger(YamlUtils.class);
    private final static Map<DataBasesPort,Integer> dbPorts = new HashMap<DataBasesPort,Integer>();
    private static Integer mockServerPort;
    private final static String MOCK_SERVER_PORT = "mockServerPort";
    public static void generateIntegrationYml(){
        try {
            StringBuilder urlReadBuilder = new StringBuilder();
            urlReadBuilder.append(System.getProperty("user.dir"));
            urlReadBuilder.append("/src/test/resources/application-template.yml");
            Path readPath = Paths.get(urlReadBuilder.toString());
            Path writePath = Paths.get(YamlUtils.class.getClassLoader().getResource("application-template.yml").getPath());
            writePath = writePath.resolveSibling("application.yml");
            Charset charset = StandardCharsets.UTF_8;
            String content = new String(Files.readAllBytes(readPath), charset);
            Set<DataBasesPort> portsVariables = dbPorts.keySet();
            for(DataBasesPort key:portsVariables ){
                Integer portDb =dbPorts.get(key);
                content = content.replaceAll(key.getValue(),portDb.toString());
            }
            content = content.replaceAll(MOCK_SERVER_PORT,mockServerPort.toString());
            
            Files.write(writePath, content.getBytes(charset));
        } catch (Exception e) {
           LOG.error("Failed to replace ports variables");
        }
    
    }   
    
    public static void addDbPort(Integer port, DataBasesPort portVariable){
        dbPorts.put(portVariable, port);
    }
    
    public static void addWSPort(Integer port){
        mockServerPort = port;
    }
    
   
}
