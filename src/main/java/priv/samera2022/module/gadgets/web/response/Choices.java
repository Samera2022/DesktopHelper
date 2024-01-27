package priv.samera2022.module.gadgets.web.response;

import priv.samera2022.module.gadgets.web.request.Message;

public class Choices {
    private Integer index;
    private Message message;
    private Object logprobs;
    private String finishReason;

    public Integer getIndex() { return index; }
    public Message getMessage() { return message; }
    public Object getLogprobs() { return logprobs; }
    public String getFinishReason() { return finishReason; }

    public void setIndex(Integer index) { this.index = index; }
    public void setMessage(Message message) { this.message = message; }
    public void setLogprobs(Object logprobs) { this.logprobs = logprobs; }
    public void setFinishReason(String finishReason) { this.finishReason = finishReason; }
}
