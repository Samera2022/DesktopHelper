package priv.samera2022.module.gadgets.gpt.response;

public class Response {
    private String id;
    private String object;
    private Integer created;
    private String model;
    private Choices choices;
    private Usage usage;
    private String systemFingerprint;

    public Response() {
    }

    public Response(String id, String object, Integer created, String model, Choices choices,
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
    public Integer getCreated() { return created; }
    public String getModel() { return model; }
    public Choices getChoices() { return choices; }
    public Usage getUsage() { return usage; }
    public String getSystemFingerprint() { return systemFingerprint; }

    public void setId(String id) { this.id = id; }
    public void setObject(String object) { this.object = object; }
    public void setCreated(Integer created) { this.created = created; }
    public void setModel(String model) { this.model = model; }
    public void setChoices(Choices choices) { this.choices = choices; }
    public void setUsage(Usage usage) { this.usage = usage; }
    public void setSystemFingerprint(String systemFingerprint) { this.systemFingerprint = systemFingerprint; }

    public String formatJson(){
        return "{\n" +
                "  \"id\": "+id+",\n" +
                "  \"object\": "+object+",\n" +
                "  \"created\": "+created+",\n" +
                "  \"model\": "+model+",\n" +
                "  \"choices\": [\n" +
                "    {\n" +
                "      \"index\": "+ choices.getIndex()+",\n" +
                "      \"message\": {\n" +
                "        \"role\": "+ choices.getMessage().getRole() +",\n" +
                "        \"content\": "+ choices.getMessage().getContent() +
                "      },\n" +
                "      \"logprobs\": "+ choices.getLogprobs() +",\n" +
                "      \"finish_reason\": "+ choices.getFinishReason() +
                "    }\n" +
                "  ],\n" +
                "  \"usage\": {\n" +
                "    \"prompt_tokens\": "+ usage.getPromptTokens() +",\n" +
                "    \"completion_tokens\": "+ usage.getCompletionTokens() +",\n" +
                "    \"total_tokens\": "+ usage.getTotalTokens() +
                "  },\n" +
                "  \"system_fingerprint\": "+ systemFingerprint +
                "}\n";
    }

    @Override
    public String toString(){
        return "{\"id\": "+id+",\"object\": "+object+",\"created\": "+created+",\"model\": "+model+"," +
                "\"choices\": [{\"index\": "+ choices.getIndex()+",\"message\": {\"role\": "+ choices.getMessage().getRole() +"," +
                "\"content\": "+ choices.getMessage().getContent() + "}," +
                "\"logprobs\": "+ choices.getLogprobs() +",\"finish_reason\": "+ choices.getFinishReason() + "}]," +
                "\"usage\": {\"prompt_tokens\": "+ usage.getPromptTokens() +",\"completion_tokens\": "+ usage.getCompletionTokens() +"," +
                "\"total_tokens\": "+ usage.getTotalTokens() + "},\"system_fingerprint\": "+ systemFingerprint + "}";
    }
}