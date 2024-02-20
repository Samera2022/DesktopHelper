package priv.samera2022.module.gadgets.gpt;

//import out.json.jParser;


import priv.samera2022.module.gadgets.gpt.request.Message;
import priv.samera2022.module.gadgets.gpt.response.Choices;
import priv.samera2022.module.gadgets.gpt.response.Response;
import priv.samera2022.module.gadgets.gpt.response.Usage;
import priv.samera2022.module.mainFrame;

import java.util.HashMap;

public class Convert {

    @Deprecated
    public static HashMap<String, Object> deprecated_convert(String json) {
        HashMap<String, Object> output = new HashMap<>();
        String copy = json;
        while (copy.contains("\"")) {
            int a1 = copy.indexOf("\"");
            int a2 = copy.indexOf("\": ");
            String key = copy.substring(a1 + 1, a2);

            int b1 = copy.indexOf("[");
            int b2 = copy.indexOf("]");

            int c1 = copy.indexOf("{");
            int c2 = copy.indexOf("}");

            int a3 = copy.indexOf(",");

            int in1 = 0;
            int in2 = 0;
            if (a3 < b1) {
                in1 = a2 + 3;
                in2 = a3;
            } else if (b1 != -1 && a3 > b1) {
                in1 = b1;
                in2 = b2 + 1;
            } else if (b1 == -1 && c1 != -1) {
                in1 = c1;
                in2 = c2 + 1;
            } else if (b1 == -1 && c1 == -1) {
                //本来按道理(b1==-1 && c1==-1)和b1 == -1都可以的，因为按照if的逻辑，判断完上一个才会进入下一个。
                //所以到这里的时候c1肯定是-1了，不过还是把c1 == -1 写上了，因为这样逻辑上更好理解一点。
                in1 = a2 + 3;
                in2 = copy.length() - 2;
            }
            String value = copy.substring(in1, in2);
            copy = copy.substring(in2 + 1);
            output.put(key, value);
        }
        return output;
    }


    public static Response convert(String json) {
        mainFrame.logger.warn("Method convert could only handle SIMPLE QUESTIONS!!!");
        HashMap<String, Object> result = getResponse(json);

        Response response = new Response();
        response.setId((String) result.get("id"));
        response.setObject((String) result.get("object"));
        response.setCreated(Integer.parseInt((String) result.get("created")));
        response.setModel((String) result.get("model"));

        Choices choices = new Choices();
        HashMap<String, Object> choicesMap = getChoices((String) result.get("choices"));
        choices.setIndex(Integer.parseInt((String) choicesMap.get("index")));
        choices.setLogprobs(choicesMap.get("logprobs"));
        choices.setFinishReason((String) choicesMap.get("finish_reason"));
        Message message = new Message();
        HashMap<String,String> messageMap = getMessage((String) choicesMap.get("message"));
        message.setRole(messageMap.get("role"));
        message.setContent(messageMap.get("content"));
        choices.setMessage(message);
        response.setChoices(choices);

        Usage usage = new Usage();
        HashMap<String, Integer> usageMap = getUsage((String) result.get("usage"));
        usage.setPromptTokens(usageMap.get("prompt_tokens"));
        usage.setCompletionTokens(usageMap.get("completion_tokens"));
        usage.setTotalTokens(usageMap.get("total_tokens"));
        response.setUsage(usage);
        response.setSystemFingerprint((String) result.get("system_fingerprint"));

        return response;
    }

    private static HashMap<String, Object> getResponse(String json) {
        HashMap<String, Object> output = new HashMap<>();
        String copy = json;
        while (copy.contains("\"")) {
            int a1 = copy.indexOf("\"");
            int a2 = copy.indexOf("\":");
            String key = copy.substring(a1 + 1, a2);

            int b1 = copy.indexOf("[");
            int b2 = copy.indexOf("]");

            int c1 = copy.indexOf("{");
            int c2 = copy.indexOf("}");

            int a3 = copy.indexOf(",");

            int in1 = 0;
            int in2 = 0;
            if (a3 < b1) {
                in1 = a2 + 2;
                in2 = a3;
            } else if (b1 != -1 && a3 > b1) {
                in1 = b1;
                in2 = b2 + 1;
            } else if (b1 == -1 && c1 != -1) {
                in1 = c1;
                in2 = c2 + 1;
            } else if (b1 == -1 && c1 == -1) {
                //本来按道理(b1==-1 && c1==-1)和b1 == -1都可以的，因为按照if的逻辑，判断完上一个才会进入下一个。
                //所以到这里的时候c1肯定是-1了，不过还是把c1 == -1 写上了，因为这样逻辑上更好理解一点。
                in1 = a2 + 2;
                in2 = copy.length() - 2;
            }
            String value = copy.substring(in1, in2);
            copy = copy.substring(in2 + 1);
            output.put(key, value);
        }
        return output;
    }

    private static HashMap<String, Object> getChoices(String choices) {
        HashMap<String, Object> output = new HashMap<>();
        choices = choices.substring(choices.indexOf("{") + 1, choices.lastIndexOf("}")+1);
        //留下最后一个}，方便后面用三元运算符
        String copy = choices;
        while (copy.contains("\"")) {
            int a1 = copy.indexOf("\"");
            int a2 = copy.indexOf("\":");
            String key = copy.substring(a1 + 1, a2);

            int c1 = copy.indexOf("{");
            int c2 = copy.indexOf("}");

            int a3 = copy.indexOf(",");

            int in1;
            int in2;
            if (a3 < c1 || c1 == -1) {
                in1 = a2 + 2;
                in2 = a3 == -1 ? copy.length()-1 : a3;
                //可以把a3==-1单独拉一个分支出来，拉出判断分支理论上来说效率会高一些，但是实际上这个方法本身不用来处理大量的数据，所以我认为牺牲一点点效率换代码更简洁还是可以的。
            } else {
                in1 = c1;
                in2 = c2 + 1;
            }
            String value = copy.substring(in1, in2);
            copy = copy.substring(in2 + 1);
            output.put(key, value);
        }
        return output;
    }

    private static HashMap<String, String> getMessage(String message) {
        HashMap<String, String> output = new HashMap<>();
        message = message.substring(message.indexOf("{") + 1, message.lastIndexOf("}"));
        String copy = message;
        int a1 = copy.indexOf("\",");
        output.put("role",copy.substring(copy.indexOf("\":\"")+2,a1+1));
        copy = copy.substring(a1+1);
        output.put("content",copy.substring(copy.indexOf("\":\"")+2,copy.lastIndexOf("\"")+1));
        return output;
    }

    private static HashMap<String, Integer> getUsage(String usage) {
        HashMap<String, Integer> output = new HashMap<>();
        usage = usage.substring(usage.indexOf("{") + 1, usage.lastIndexOf("}")+1);
        String copy = usage;
        while (copy.contains("\"")) {
            int a1 = copy.indexOf("\"");
            int a2 = copy.indexOf("\":");
            String key = copy.substring(a1 + 1, a2);
            int a3 = copy.indexOf(",");
            int a4 = a3 == -1 ? copy.length()-1 : a3;
            String value = copy.substring(a2 + 2, a4);
            copy = copy.substring(a4 + 1);
            output.put(key, Integer.parseInt(value));
        }
        return output;
    }
}