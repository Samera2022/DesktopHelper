package priv.samera2022.module.gadgets.mc.modpack.download;

import priv.samera2022.module.mainFrame;

import java.util.ArrayList;
import java.util.HashMap;

public class JsonReader {

    /**
     * @author Samera2022
     * @param json Formatted Json Content
     * Load readFJ(String json, int indent) with indent 0
     * */
    public static HashMap<String,Object> readFJ(String json){ return readFJ(json,0); }

    /**
     * @author Samera2022
     * @param json Formatted Json Content
     * @param indent Indent level
     * */
    public static HashMap<String,Object> readFJ(String json, int indent){
        if (indent!=0)
            json = FJ_replace(json, indent);
        HashMap<String,Object> map = new HashMap<>();
        json = json.substring(0,json.length()-2)+",\n  \"";
        while (json.contains(",")) {
            json = json.substring(json.indexOf("\"") + 1);
//            System.out.println(json);
////            System.out.println("--------");
            String key = json.substring(0, json.indexOf("\":"));
            mainFrame.logger.debug("key: " + key);
            Object value;
            String valueC;
            valueC = json.substring(json.indexOf(": ") + ": ".length(), json.indexOf(",\n  \""));
//            if (json.indexOf(",  \n  \"")<json.indexOf(",\n  \"") && json.contains(",  \n  \""))
//                valueC = json.substring(json.indexOf(": ") + ": ".length(), json.indexOf(",  \n  \""));
//            else valueC = json.substring(json.indexOf(": ") + ": ".length(), json.indexOf(",\n  \""));

//                valueC = json.substring(json.indexOf(": ") + ": ".length(), json.indexOf(",\n  \""));
//            else valueC = json.substring(json.indexOf(": ") + ": ".length(), json.indexOf(",  \n  \""));
////            System.out.println("valueC: "+valueC);
            if (valueC.startsWith("\"") && valueC.endsWith("\"")) value = valueC.substring(1, valueC.length() - 1);//String
            else if (valueC.startsWith("{")) value = readFJ(valueC,indent+=1);//对象
            else if (valueC.startsWith("[")) value = FJ_handleArray(valueC);//数组
            else value = valueC;
            //或可考虑注释语法
            mainFrame.logger.debug("value: " + value);
            map.put(key, value);
            json = json.substring(json.indexOf(",\n  \"") + ",\n  \"".length() - 1);
////            System.out.println("--------");
        }
        return map;
    }

    private static String FJ_replace(String json, int indent){
        StringBuilder target = new StringBuilder(",\n  ");
        for (int i = 0; i<indent; i++){
            target.append("  ");
        }
        StringBuilder replacement = new StringBuilder(target.substring(0,target.length()-2));
        target.append("\"");
        replacement.append("\"");
        return json.replace(target,replacement);
    }

    public static void test() {
//      ??? i++既能作为参数，返回i，又能作为运算符 ???
        int i = 0;
        int a = i++;
        System.out.println(a);
        System.out.println(i);
        System.out.println(i+=1);
        System.out.println(i);
    }

    private static ArrayList<HashMap<String,Object>> FJ_handleArray(String json){
//        System.out.println(json);
        json = json.substring(json.indexOf("[")+2,json.lastIndexOf("]"));
        String _target = json.substring(0,json.indexOf("{"));
        String target = _target + "{";
        String target2 = _target+"  \"";
        String target3 = _target+"}";
        json = json.replace(target,"{");
        json = json.replace(target2,"  \"");
        json = json.replace(target3,"}");
        ArrayList<HashMap<String,Object>> als = new ArrayList<>();
        String[] arr = json.split("\\{\n");
        for (int i = 1; i<arr.length; i++) {
            String str = arr[i];
            String str2 = str.substring(0,str.indexOf("}")-1);
            str2 = str2 + ",\n";
            HashMap<String,Object> map = readFJ(str2);
            als.add(map);
        }
        return als;
    }
}
