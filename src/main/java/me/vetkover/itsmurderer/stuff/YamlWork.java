package me.vetkover.itsmurderer.stuff;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class YamlWork {

    public static void createYaml(File configFile) {
        try {
        FileWriter writer = new FileWriter(configFile);

        writer.write("#if something is broken just delete the file :3'\n");
        writer.write("defaultItsMurderer: false #do you want the person to get murderer status immediately?\n");
        writer.write("punishMurderDuration: 259200 #duration in seconds of punishment memory\n");
        writer.write("punishSusDuration: 86400 #duration in seconds of punishment memory\n");
        writer.write("spawnAreaProtect: 400 #specify the value at which distance with spawn the status will be automatically issued\n");

        writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    static String appDir = System.getProperty("user.dir");
    public static Object readYaml(String dataGet) {

            Yaml yaml = new Yaml();
            try {
                InputStream in = Files.newInputStream(Paths.get(appDir + "/plugins/ItsMurderer/config.yaml"));
                Map<String, Object> data = yaml.load(in);
                Object value = data.get(dataGet);
                return value;
            } catch (Exception e) {
                e.printStackTrace();
            }
        return null;
    }
    }



