package backup;

import java.text.SimpleDateFormat;

public class Logger_old {
    private String content = "";
    public Logger_old(){}

    public static final String DEBUG = "[DEBUG]";
    public static final String INFO = "[INFO]";
    public static final String WARN = "[WARN]";
    public static final String ERROR = "[ERROR]";
    public static final String FATAL = "[FATAL]";

//    public void debug(Object message){ String in1 = "[DEBUG]" + output() + message; format(in1);}
//    public void info(Object message){ String in1 = "[INFO]" + output() + message; format(in1);}
//    public void warn(Object message){ String in1 = "[WARN]" + output() + message; format(in1);}
//    public void error(Object message){ String in1 = "[ERROR]" + output() + message; format(in1);}
//    public void fatal(Object message){ String in1 = "[FATAL]" + output() + message; format(in1);}

    public String prefix() { return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS").format(System.currentTimeMillis()); }


    @Override
    public String toString(){ return content; }

    private void format(String in1){
        System.out.println(in1);
        content = content + in1 + "\n";
    }
//    private String output(){ return " "+prefix() +" "+ current() +" ";}

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

//    public void record(Object message, String type) {
//        String output = "";
//        // 获取当前线程的堆栈跟踪
//        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
//        // 遍历堆栈跟踪来找到调用的方法
//        for (StackTraceElement ste : stackTraceElements) {
//            if (ste.getMethodName().equals("record")) {
//                // 继续查找调用的方法
//                continue;
//            }
//            // 打印调用的方法的信息
//            System.out.println("调用类: " + ste.getClassName());
//            System.out.println("调用方法: " + ste.getMethodName());
//            String className = ste.getClassName();
//            String methodName = ste.getMethodName();
//            int lineNumber = ste.getLineNumber();
//            output = "method:" + className + "." + methodName + "(" + (className.substring(className.lastIndexOf(".")+1)) + ".java:" + lineNumber +")";
//            break; // 找到第一个调用者即可
//        }
//        String in1 = type + " "+prefix() +" "+ output +" " + message;
//        format(in1);
//    }
//
//    //另一种查找调用的方法
//    private static String current(){
//        String output;
//        // 获取当前线程的堆栈跟踪元素数组
//        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
//        // 获取调用error方法的堆栈跟踪元素（排除掉CustomLogger类内部的方法和getStackTrace方法）
//        if (stackTraceElements.length > 2) {
//            StackTraceElement caller = stackTraceElements[2];
//            // 获取方法名、类名、行号和包名
//            String methodName = caller.getMethodName();
//            String className = caller.getClassName();
//            int lineNumber = caller.getLineNumber();
//            output = "method:" + className + "." + methodName + "(" + (className.substring(className.lastIndexOf(".")+1)) + ".java:" + lineNumber +")";
//        } else {
//            output = "ERROR IN GETTING DETAILED INFORMATION";
//        }
//        return output;
//    }
}
