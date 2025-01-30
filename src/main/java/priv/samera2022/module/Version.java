package priv.samera2022.module;

public class Version{
    private boolean isReleased;
    private String edition;
    private String time;
    private String content;
    public Version(boolean isReleased, String edition, String time, String content){
        this.isReleased = isReleased;
        this.edition = edition;
        this.time = time;
        this.content = content;
    }
    public Version(String edition, String time, String content){
        this.isReleased = true;
        this.edition = edition;
        this.time = time;
        this.content = content;
    }
    public boolean isReleased() { return isReleased; }
    public String getEdition() { return edition; }
    public String getTime() { return time; }
    public String getContent() { return content; }
    @Override
    public String toString(){ return head() + "\n" + content; }
    public String head(){ return " - [" + (isReleased?"R":"Unr") + "eleased] - [" + edition + "] - " + time; }
}