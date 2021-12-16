package configuration.yaml;

import java.io.IOException;

public class Loader {

    public Loader() throws IOException {
        YamlReader yamlReader = new YamlReader();
        String testWebURL = yamlReader.getConfig().getEnvironment().getTest().getWebUrl();
        System.setProperty("testWebURL",testWebURL);
        String devWebURL = yamlReader.getConfig().getEnvironment().getDev().getWebUrl();
        System.setProperty("devWebURL",devWebURL);
        String testTitle = yamlReader.getConfig().getEnvironment().getTest().getTitle();
        System.setProperty("title",testTitle);
        String devTitle = yamlReader.getConfig().getEnvironment().getDev().getTitle();
        System.setProperty("title",devTitle);
    }
}
