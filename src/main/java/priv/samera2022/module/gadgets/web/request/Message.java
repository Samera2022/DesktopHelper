package priv.samera2022.module.gadgets.web.request;

public class Message {
    private String content;
    private String role;
    public Message(){}
    public Message(String role, String content){
        this.role = role;
        this.content = content;
    }

    public String getContent() { return content; }
    public void setContent(String value) { this.content = value; }

    public String getRole() { return role; }
    public void setRole(String value) { this.role = value; }
}