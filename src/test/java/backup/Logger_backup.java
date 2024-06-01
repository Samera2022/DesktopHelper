package backup;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

public class Logger_backup {
    private String content = "";
    public Logger_backup(){}

    public static final String DEBUG = "[DEBUG]";
    public static final String INFO = "[INFO]";
    public static final String WARN = "[WARN]";
    public static final String ERROR = "[ERROR]";
    public static final String FATAL = "[FATAL]";
    private static final List<String> names = Arrays.asList("debug","info","warn","error","fatal");

    public void debug(Object message){ record(message,DEBUG);}
//    public void debug(Object message){ String in1 = "[DEBUG]" + output() + message; format(in1);}
    public void info(Object message){ String in1 = "[INFO]" + output() + message; format(in1);}
    public void warn(Object message){ String in1 = "[WARN]" + output() + message; format(in1);}
    public void error(Object message){ String in1 = "[ERROR]" + output() + message; format(in1);}
    public void fatal(Object message){ String in1 = "[FATAL]" + output() + message; format(in1);}

    public String prefix() { return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS").format(System.currentTimeMillis()); }


    @Override
    public String toString(){ return content; }

    private void format(String in1){
        System.out.println(in1);
        content = content + in1 + "\n";
    }
    private String output(){
//        return " "+prefix() +" "+ current() +" ";
        return null;
    }

//    // record方法尝试获取调用它的任意方法的信息
//    public void record(Object message, String type) {
//        String output = "";
//        try {
//            // 创建一个新的Throwable对象来捕获堆栈跟踪
//            Throwable throwable = new Throwable();
//            // 填充inStackTrace，然后获取堆栈跟踪
//            StackTraceElement[] stackTraceElements = throwable.getStackTrace();
//
//            // 遍历堆栈跟踪，找到调用B的方法
//            for (StackTraceElement element : stackTraceElements) {
//                // 跳过record方法本身和main方法
////                if (!element.getMethodName().equals("record") && !element.getMethodName().equals("main")) {
//                if (!element.getMethodName().equals("record")) {
//                    // 输出找到的方法所在的类和方法名
//                    String className = element.getClassName();
//                    String methodName = element.getMethodName();
//                    int lineNumber = element.getLineNumber();
//                    output = "method:" + className + "." + methodName + "(" + (className.substring(className.lastIndexOf(".")+1)) + ".java:" + lineNumber +")";
//                    break;
//                }
//            }
//            String in1 = type + " "+prefix() +" "+ output +" " + message;
//            format(in1);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    public void record(Object message, String type) {
        String output = "";
        try {
            // 创建一个新的Throwable对象来捕获堆栈跟踪
            Throwable throwable = new Throwable();
            // 获取堆栈跟踪
            StackTraceElement[] stackTraceElements = throwable.getStackTrace();

            // 遍历堆栈跟踪，找到调用链中B之前的任意方法
            boolean found = false;
            for (StackTraceElement element : stackTraceElements) {
                if (element.getMethodName().equals("record")) {
                    // 找到B方法后，下一个方法就是调用B的A
                    if (names.contains(element.getMethodName())) {
                        found = true;
                        continue;
                    }
                    // 如果已经找到B，那么下一个就是调用B的方法
                    if (found) {
                        String className = element.getClassName();
                        String methodName = element.getMethodName();
                        int lineNumber = element.getLineNumber();
                        output = "method:" + className + "." + methodName + "(" + (className.substring(className.lastIndexOf(".") + 1)) + ".java:" + lineNumber + ")";
                        break;
                    }
                }
            }
            String in1 = type + " "+prefix() +" "+ output +" " + message;
            format(in1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
