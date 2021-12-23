package configuration.yaml;

public class EnvDetails {
    String envName;
    String webUrl;
    String title;
    String browser;
    String userEmail;
    String userPassword;
    String active;
    String loginFailMessage;

    public String getLoginFailMessage() {
        return loginFailMessage;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getActive() {
        return active;
    }

    public String getBrowser() {
        return browser;
    }

    public String getEnvName() {
        return envName;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public String getTitle() {
        return title;
    }
}
