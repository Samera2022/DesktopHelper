package web;

//import out.json.jParser;


import java.util.HashMap;

public class Convert {

    public static void main2(String[] args) {
//        String json = "{\"a\":\"a\",\"b\":\"b\",\"c\":c,\"d\":\"d\",\"e\":[{\"f\":0,\"g\":{\"h\":\"i\",\"j\":\"k\"},\"l\":m,\"n\":\"o\"}],\"p\":{\"q\":r,\"s\":t,\"u\":v},\"w\":\"x\"}\n";
//        System.out.println(json);
        String json = "abc";
        System.out.println("indexOf(\"b\") = " + json.indexOf("b"));
        System.out.println("subString(indexOf(\"b\")) = " + json.substring(json.indexOf("b")));
        //subString indexOf是从该字符的前一个开始分割
//        System.out.println((String) jParser.get(json, "e", "g"));

    }

    public static void main(String[] args) {
        HashMap<String,Object> hashMap = convert("{\n" +
                "  \"id\": \"chatcmpl-8lXIsOspyxkeNtEVYYQhPyoZ4SO7C\",\n" +
                "  \"object\": \"chat.completion\",\n" +
                "  \"created\": 1706340126,\n" +
                "  \"model\": \"gpt-3.5-turbo-1106\",\n" +
                "  \"choices\": [\n" +
                "    {\n" +
                "      \"index\": 0,\n" +
                "      \"message\": {\n" +
                "        \"role\": \"assistant\",\n" +
                "        \"content\": \"Hi there! How can I assist you today?\"\n" +
                "      },\n" +
                "      \"logprobs\": null,\n" +
                "      \"finish_reason\": \"stop\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"usage\": {\n" +
                "    \"prompt_tokens\": 9,\n" +
                "    \"completion_tokens\": 10,\n" +
                "    \"total_tokens\": 19\n" +
                "  },\n" +
                "  \"system_fingerprint\": \"fp_b57c83dd65\"\n" +
                "}");
        for (String key : hashMap.keySet()){
            System.out.println("key: "+key+"\nvalue: "+hashMap.get(key));
        }
    }

    @SuppressWarnings("all")
    public static HashMap<String,Object> convert(String json) {
        HashMap<String,Object> output = new HashMap<>();
        String copy = json;
        while (copy.contains("\"")) {
            int a1 = copy.indexOf("\"");
            int a2 = copy.indexOf("\": ");
            String key = copy.substring(a1 + 1, a2);
            System.out.println("key: " + key);

            int b1 = copy.indexOf("[");
            int b2 = copy.indexOf("]");

            int c1 = copy.indexOf("{");
            int c2 = copy.indexOf("}");

            int a3 = copy.indexOf(",");
            if (a3<b1) {
                String value = copy.substring(a2 + 3, a3);
                System.out.println("value: " + value);
                copy = copy.substring(a3 + 1);
                output.put(key,value);
            } else if (b1!=-1 && a3>b1){
                String value = copy.substring(b1,b2+1);
                System.out.println("value: " + value);
                copy = copy.substring(b2+2);
                output.put(key,value);
            } else if (b1==-1 && c1!=-1){
                String value = copy.substring(c1,c2+1);
                System.out.println("value: " + value);
                copy = copy.substring(c2+2);
                output.put(key,value);
            } else if (b1==-1 && c1==-1){
                String value = copy.substring(a2+3,copy.length()-2);
                System.out.println("value: " + value);
                copy = "";
                output.put(key,value);
            }
        }
        return output;
    }

}