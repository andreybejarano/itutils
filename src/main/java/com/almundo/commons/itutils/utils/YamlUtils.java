package com.almundo.commons.itutils.utils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class YamlUtils {

    private final static Logger Logger = LoggerFactory.getLogger(YamlUtils.class);
    private final static String MOCK_SERVER_PORT = "mockServerPort";

    public void generateIntegrationYml(Map<DataBase, Integer> dbPorts, Integer mockServerPort) {
        try {
            StringBuilder urlReadBuilder = new StringBuilder();
            urlReadBuilder.append(System.getProperty("user.dir"));
            urlReadBuilder.append("/src/test/resources/application-template.yml");
            Path readPath = Paths.get(urlReadBuilder.toString());
            Path writePath = Paths
                    .get(YamlUtils.class.getClassLoader()
                            .getResource("application-template.yml")
                            .getPath());
            writePath = writePath.resolveSibling("application.yml");
            Charset charset = StandardCharsets.UTF_8;
            String content = new String(Files.readAllBytes(readPath), charset);
            Set<DataBase> portsVariables = dbPorts.keySet();
            for (DataBase key : portsVariables) {
                Integer portDb = dbPorts.get(key);
                content = content.replaceAll(key.getPortPlaceholder(), portDb.toString());
            }
            content = content.replaceAll(MOCK_SERVER_PORT, mockServerPort.toString());

            Files.write(writePath, content.getBytes(charset));
        } catch (Exception e) {
            Logger.error("Failed to replace ports variables");
        }

    }

}
