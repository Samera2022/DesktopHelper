package priv.samera2022.module.gadgets.gpt.config;

public class Config {
    private String authorization;
    private String model;
    private Double frequencyPenalty;
    private Object logitBias;
    private Long maxTokens;
    private Long n;
    private Double presencePenalty;
    private String stop;
    private Boolean stream;
    private Double temperature;
    private Double topP;
    private String user;

    public String getAuthorization() { return authorization; }
    public void setAuthorization(String authorization) { this.authorization = authorization; }

    public Double getFrequencyPenalty() { return frequencyPenalty; }
    public void setFrequencyPenalty(Double value) { this.frequencyPenalty = value; }

    public Object getLogitBias() { return logitBias; }
    public void setLogitBias(Object value) { this.logitBias = value; }

    public Long getMaxTokens() { return maxTokens; }
    public void setMaxTokens(Long value) { this.maxTokens = value; }

    public String getModel() { return model; }
    public void setModel(String value) { this.model = value; }

    public Long getN() { return n; }
    public void setN(Long value) { this.n = value; }

    public Double getPresencePenalty() { return presencePenalty; }
    public void setPresencePenalty(Double value) { this.presencePenalty = value; }

    public String getStop() { return stop; }
    public void setStop(String value) { this.stop = value; }

    public Boolean getStream() { return stream; }
    public void setStream(Boolean value) { this.stream = value; }

    public Double getTemperature() { return temperature; }
    public void setTemperature(Double value) { this.temperature = value; }

    public Double getTopP() { return topP; }
    public void setTopP(Double value) { this.topP = value; }

    public String getUser() { return user; }
    public void setUser(String value) { this.user = value; }

    //其实可以通过新建一个叫Optional的类再覆写toString()方法来解决这个问题
    //将所有选填的内容都设进Optional里面，但是这样所有逻辑都要重写，太麻烦了。
    public String formatJson(){
        return "{\n" +
                "  \""+Concept.AUTHORIZATION+"\": "+authorization+",\n" +
                "  \""+Concept.MODEL+"\": "+model+",\n" +
                "  \""+Concept.FREQUENCY_PENALTY +"\": "+(frequencyPenalty == null ?"null":frequencyPenalty)+",\n" +
                "  \""+Concept.LOGIT_BIAS+"\": "+(logitBias == null ?"null":logitBias)+",\n" +
                "  \""+Concept.MAX_TOKENS+"\": "+(maxTokens == null ?"null":maxTokens)+",\n" +
                "  \""+Concept.N+"\": "+(n == null ?"null":n)+",\n" +
                "  \""+Concept.PRESENCE_PENALTY+"\": "+(presencePenalty == null ?"null":presencePenalty)+",\n" +
                "  \""+Concept.STOP+"\": "+(stop == null ?"null":stop)+",\n" +
                "  \""+Concept.STREAM+"\": "+(stream == null ?"null":stream)+",\n" +
                "  \""+Concept.TEMPERATURE+"\": "+(temperature == null ?"null":temperature)+",\n" +
                "  \""+Concept.TOP_P+"\": "+(topP == null ?"null":topP)+",\n" +
                "  \""+Concept.USER+"\": "+(user == null ?"null":user)+",\n" +
                "}\n";
    }
}
