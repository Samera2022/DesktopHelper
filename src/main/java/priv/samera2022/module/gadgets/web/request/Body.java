package priv.samera2022.module.gadgets.web.request;

@SuppressWarnings("unused")
public class Body {
    /**
     * 要使用的模型的 ID。有关哪些模型适用于聊天 API
     * 的详细信息，请参阅[模型端点兼容性表。](https://platform.openai.com/docs/models/model-endpoint-compatibility)
     */
    private String model;
    /**
     * -2.0 和 2.0 之间的数字。正值会根据新标记在文本中的现有频率对其进行惩罚，从而降低模型逐字重复同一行的可能性。
     * [查看有关频率和存在惩罚的更多信息。](https://platform.openai.com/docs/api-reference/parameter-details)
     */
    private Double frequencyPenalty;
    /**
     * 修改指定标记出现在完成中的可能性。  接受一个 json 对象，该对象将标记（由标记器中的标记 ID 指定）映射到从 -100 到 100
     * 的关联偏差值。从数学上讲，偏差会在采样之前添加到模型生成的 logits 中。确切的效果因模型而异，但 -1 和 1 之间的值应该会减少或增加选择的可能性；像 -100 或
     * 100 这样的值应该导致相关令牌的禁止或独占选择。
     */
    private Object logitBias;
    /**
     * 聊天完成时生成的最大令牌数。  输入标记和生成标记的总长度受模型上下文长度的限制。
     */
    private Long maxTokens;

    /**
     * 以[聊天格式](https://platform.openai.com/docs/guides/chat/introduction)生成聊天完成的消息。
     */
    private Message message;

    /**
     * 为每个输入消息生成多少个聊天完成选项。
     */
    private Long n;
    /**
     * -2.0 和 2.0 之间的数字。正值会根据到目前为止是否出现在文本中来惩罚新标记，从而增加模型谈论新主题的可能性。
     * [查看有关频率和存在惩罚的更多信息。](https://platform.openai.com/docs/api-reference/parameter-details)
     */
    private Double presencePenalty;
    /**
     * API 将停止生成更多令牌的最多 4 个序列。
     */
    private String stop;
    /**
     * 如果设置，将发送部分消息增量，就像在 ChatGPT
     * 中一样。当令牌可用时，令牌将作为纯数据[服务器发送事件](https://developer.mozilla.org/en-US/docs/Web/API/Server-sent_events/Using_server-sent_events#Event_stream_format)`data:
     * [DONE]`发送，流由消息终止。[有关示例代码](https://github.com/openai/openai-cookbook/blob/main/examples/How_to_stream_completions.ipynb)，请参阅
     * OpenAI Cookbook 。
     */
    private Boolean stream;
    /**
     * 使用什么采样温度，介于 0 和 2 之间。较高的值（如 0.8）将使输出更加随机，而较低的值（如 0.2）将使输出更加集中和确定。
     * 我们通常建议改变这个或`top_p`但不是两者。
     */
    private Double temperature;
    /**
     * 一种替代温度采样的方法，称为核采样，其中模型考虑具有 top_p 概率质量的标记的结果。所以 0.1 意味着只考虑构成前 10% 概率质量的标记。
     * 我们通常建议改变这个或`temperature`但不是两者。
     */
    private Double topP;
    /**
     * 代表您的最终用户的唯一标识符，可以帮助 OpenAI
     * 监控和检测滥用行为。[了解更多](https://platform.openai.com/docs/guides/safety-best-practices/end-user-ids)。
     */
    private String user;

    public Double getFrequencyPenalty() { return frequencyPenalty; }
    public void setFrequencyPenalty(Double value) { this.frequencyPenalty = value; }

    public Object getLogitBias() { return logitBias; }
    public void setLogitBias(Object value) { this.logitBias = value; }

    public Long getMaxTokens() { return maxTokens; }
    public void setMaxTokens(Long value) { this.maxTokens = value; }

    public Message getMessage() { return message; }
    public void setMessage(Message message) { this.message = message; }

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

    @Override
    public String toString(){
        String output = "{\n  \"model\": \""+model+"\",\n  \"messages\": [{\"role\": \""+message.getRole()+"\", \"content\": \""+message.getContent()+"\"}]\n}";
        int length = output.length();
        if (temperature!=null) output = add(temperature,"temperature",output);
        if (topP!=null) output = add(topP,"top_p",output);
        if (n!=null) output = add(n,"n",output);
        if (stream!=null) output = add(n,"n",output);
        if (stop!=null) output = add(stop,"stop",output);
        if (maxTokens!=null) output = add(maxTokens,"max_tokens",output);
        if (presencePenalty!=null) output = add(presencePenalty,"presence_penalty",output);
        if (frequencyPenalty!=null) output = add(frequencyPenalty,"frequency_penalty",output);
        if (logitBias!=null) output = add(logitBias,"logit_bias",output);
        if (user!=null) output = add(user,"user",output);
        return output;
    }

    private static String add(Object object, String name, String output){
        return output.substring(0,output.length()-2) + ", \n  \""+name+"\": " + object + output.substring(output.length()-2);
    }
}
