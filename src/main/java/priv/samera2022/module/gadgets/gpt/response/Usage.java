package priv.samera2022.module.gadgets.gpt.response;

public class Usage {
    private Integer promptTokens;
    private Integer completionTokens;
    private Integer totalTokens;

    public Integer getPromptTokens() { return promptTokens; }
    public Integer getCompletionTokens() { return completionTokens; }
    public Integer getTotalTokens() { return totalTokens; }

    public void setPromptTokens(Integer promptTokens) { this.promptTokens = promptTokens; }
    public void setCompletionTokens(Integer completionTokens) { this.completionTokens = completionTokens; }
    public void setTotalTokens(Integer totalTokens) { this.totalTokens = totalTokens; }
}