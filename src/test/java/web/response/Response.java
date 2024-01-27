package web.response;

public class Response {
    private String id;
    private String object;
    private String created;
    private String model;
    private Choices choices;
    private Usage usage;
    private String systemFingerprint;

    public Response() { }

    public Response(String id, String object, String created, String model, Choices choices,
                    Usage usage, String systemFingerprint) {
        this.id = id;
        this.object = object;
        this.created = created;
        this.model = model;
        this.choices = choices;
        this.usage = usage;
        this.systemFingerprint = systemFingerprint;
    }

    public String getId() { return id; }
    public String getObject() { return object; }
    public String getCreated() { return created; }
    public String getModel() { return model; }
    public Choices getChoices() { return choices; }
    public Usage getUsage() { return usage; }
    public String getSystemFingerprint() { return systemFingerprint; }

    public void setId(String id) { this.id = id; }
    public void setObject(String object) { this.object = object; }
    public void setCreated(String created) { this.created = created; }
    public void setModel(String model) { this.model = model; }
    public void setChoices(Choices choices) { this.choices = choices; }
    public void setUsage(Usage usage) { this.usage = usage; }
    public void setSystemFingerprint(String systemFingerprint) { this.systemFingerprint = systemFingerprint; }
}