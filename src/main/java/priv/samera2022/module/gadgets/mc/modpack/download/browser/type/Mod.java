package priv.samera2022.module.gadgets.mc.modpack.download.browser.type;

public class Mod {
    private int projectID;
    private int fileID;
    private boolean required;

    public Mod(){}
    public Mod(int projectID, int fileID, boolean required) {
        this.projectID = projectID;
        this.fileID = fileID;
        this.required = required;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public int getFileID() {
        return fileID;
    }

    public void setFileID(int fileID) {
        this.fileID = fileID;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    @Override
    public String toString() {
        return "Mod{" +
                "projectID=" + projectID +
                ", fileID=" + fileID +
                ", required=" + required +
                '}';
    }
}