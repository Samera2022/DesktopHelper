package priv.samera2022.module.gadgets.mc.modpack.download.browser.type;

public class ModLoader {
    private String id;
    private boolean primary;
    public ModLoader(){}
    public ModLoader(String id,boolean primary){
        this.id = id;
        this.primary = primary;
    }
    public String getId() {
        return id;
    }
    public boolean isPrimary() {
        return primary;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setPrimary(boolean primary) {
        this.primary = primary;
    }
}
