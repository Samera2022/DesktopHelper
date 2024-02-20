package priv.samera2022.module.config;

public class Config {
    private boolean commandOutput;
    private String cf_api_key;
    private boolean cf_doa;
    public Config(){ }

    public void setCommandOutput(boolean commandOutput) { this.commandOutput = commandOutput; }
    public boolean isCommandOutput() { return commandOutput; }

    public void setCf_api_key(String cf_api_key) { this.cf_api_key = cf_api_key; }
    public String getCf_api_key() { return cf_api_key; }

    public void setCf_doa(boolean cf_doa) { this.cf_doa = cf_doa; }
    public boolean isCf_doa() { return cf_doa; }

}
