package priv.samera2022.module.gadgets.web.config;

import priv.samera2022.module.FileHandler;
import java.util.HashMap;

public class ConfigHandler {
    private static final String CONFIG_GPT_PATH = FileHandler.FOLDER_PATH+FileHandler.CONFIG_PATH+FileHandler.CONFIG_GPT_PATH;

    public static void write(){
        String path = CONFIG_GPT_PATH+"default.json";
        Config config = new Config();
        config.setAuthorization("\"Your Authorization\"");
        config.setModel("\"Your Model Name\"");
        config.setFrequencyPenalty(null);
        config.setLogitBias(null);
        config.setMaxTokens(null);
        config.setN(null);
        config.setPresencePenalty(null);
        config.setStop(null);
        config.setTemperature(0.7);
        config.setTopP(null);
        config.setUser(null);
        FileHandler.write(path,config.formatJson(),false);
    }

    public static Config read(String configName){
        HashMap<String,Object> map = toMap(FileHandler.read(CONFIG_GPT_PATH+configName+".json"));
        Config config = new Config();
        //术语标准化（（
        config.setAuthorization(format((String) map.get(Concept.AUTHORIZATION)));
        config.setModel(format((String) map.get(Concept.MODEL)));

        String in1 = (String) map.get(Concept.FREQUENCY_PENALTY);
        if (in1!=null) config.setFrequencyPenalty(Double.parseDouble(in1));

        config.setLogitBias(map.get(Concept.LOGIT_BIAS));
        //小心字符串里面的""符号!

        String in2 = (String) map.get(Concept.MAX_TOKENS);
        if (in2!=null) config.setMaxTokens(Long.parseLong(in2));

        String in3 = (String) map.get(Concept.N);
        if (in3!=null) config.setN(Long.parseLong(in3));

        String in4 = (String) map.get(Concept.PRESENCE_PENALTY);
        if (in4!=null) config.setPresencePenalty(Double.parseDouble(in4));

        String sin1 = (String) map.get(Concept.STOP);
        if (sin1!=null) sin1 = format(sin1);
        config.setStop(sin1);

        String in5 = (String) map.get(Concept.TEMPERATURE);
        if (in5!=null) config.setTemperature(Double.parseDouble(in5));

        String in6 = (String) map.get(Concept.TOP_P);
        if (in6!=null) config.setTopP(Double.parseDouble(in6));

        String sin2 = (String) map.get(Concept.USER);
        if (sin2!=null) sin2 = format(sin2);
        config.setUser(sin2);

        return config;
    }

    private static String format(String string){ return string.substring(1,string.length()-1); }

    private static HashMap<String, Object> toMap(String json) {
        HashMap<String, Object> output = new HashMap<>();
        json = json.substring(json.indexOf("{") + 1, json.lastIndexOf("}")+1);
        String copy = json;
        while (copy.contains("\"")) {
            int a1 = copy.indexOf("\"");
            int a2 = copy.indexOf("\":");
            String key = copy.substring(a1 + 1, a2);
            int a3 = copy.indexOf(",");
            int a4 = a3 == -1 ? copy.length() : a3;
            String value = copy.substring(a2 + 3, a4);

            if (value.equals("null")) value = null;
            //这里已经默认处理了null，所以在Body.toString()的时候已经处理掉了这些空的值
            copy = copy.substring(a4 + 1);
            output.put(key, value);
        }
        return output;
    }

}
