package backup;

import java.text.SimpleDateFormat;

public class Logger {
    private String content = "";

    public Logger(){}

    public static final String DEBUG = "[DEBUG]";
    public static final String INFO = "[INFO]";
    public static final String WARN = "[WARN]";
    public static final String ERROR = "[ERROR]";
    public static final String FATAL = "[FATAL]";

    public String prefix() { return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS").format(System.currentTimeMillis()); }


    @Override
    public String toString(){ return content; }

    private void format(String in1){
        System.out.println(in1);
        content = content + in1 + "\n";
    }

    // record方法尝试获取调用它的任意方法的信息
    public void record(Object message, String type) {
        String output = "";
        try {
            // 创建一个新的Throwable对象来捕获堆栈跟踪
            Throwable throwable = new Throwable();
            // 填充inStackTrace，然后获取堆栈跟踪
            StackTraceElement[] stackTraceElements = throwable.getStackTrace();

            // 遍历堆栈跟踪，找到调用B的方法
            for (StackTraceElement element : stackTraceElements) {
                // 跳过record方法本身和main方法
//                if (!element.getMethodName().equals("record") && !element.getMethodName().equals("main")) {
                if (!element.getMethodName().equals("record")) {
                    // 输出找到的方法所在的类和方法名
                    String className = element.getClassName();
                    String methodName = element.getMethodName();
                    int lineNumber = element.getLineNumber();
                    output = "method:" + className + "." + methodName + "(" + (className.substring(className.lastIndexOf(".")+1)) + ".java:" + lineNumber +")";
                    break;
                }
            }
            String in1 = type + " "+prefix() +" "+ output +" " + message;
            format(in1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
