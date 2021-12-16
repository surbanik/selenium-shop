package configuration.yaml;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Loader {

    public List<EnvDetails> createEnvList() throws IOException {
        YamlReader yamlReader = new YamlReader();
        EnvDetails test = yamlReader.getConfig().environments.getTest();
        EnvDetails dev = yamlReader.getConfig().environments.getDev();
        List<EnvDetails> envList = new ArrayList<>();
        envList.add(test);
        envList.add(dev);
        return envList;
    }


    public Loader() throws IOException {
        for (EnvDetails env : createEnvList()){
            if (env.getActive().equals("y")){
                System.setProperty("webURL", env.getWebUrl());
                System.setProperty("title",env.getTitle());
                System.setProperty("browser",env.getBrowser());
            }
        }
    }
}
