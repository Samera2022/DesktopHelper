package priv.samera2022.module.config;

public class Config {
    private boolean commandOutput;
    private String cf_api_key;
    private boolean cf_doa;
    private boolean simpleLoggerOutput;
    private boolean enableCharacterization;
    private String bgPath;
    private boolean darkMode;
    public Config(){ }

    public void setCommandOutput(boolean commandOutput) { this.commandOutput = commandOutput; }
    public boolean isCommandOutput() { return commandOutput; }

    public void setCf_api_key(String cf_api_key) { this.cf_api_key = cf_api_key; }
    public String getCf_api_key() { return cf_api_key; }

    public void setCf_doa(boolean cf_doa) { this.cf_doa = cf_doa; }
    public boolean isCf_doa() { return cf_doa; }

    public void setSimpleLoggerOutput(boolean simpleLoggerOutput) { this.simpleLoggerOutput = simpleLoggerOutput; }
    public boolean isSimpleLoggerOutput() { return simpleLoggerOutput; }

    public void setEnableCharacterization(boolean enableCharacterization) { this.enableCharacterization = enableCharacterization; }
    public boolean isEnableCharacterization() { return enableCharacterization; }

    public void setBgPath(String bgPath) { this.bgPath = bgPath; }
    public String getBgPath() { return bgPath; }

    public void setDarkMode(boolean darkMode) { this.darkMode = darkMode; }
    public boolean isDarkMode() { return darkMode; }
}
