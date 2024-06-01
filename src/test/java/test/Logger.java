package test;

import java.text.SimpleDateFormat;

public class Logger {
    private String content = "";
    public Logger(){}

    public void debug(Thread thread,Object message){ String in1 = "[DEBUG]" + output(thread) + message; format(in1);}
    public void info(Thread thread,Object message){ String in1 = "[INFO]" + output(thread) + message; format(in1);}
    public void warn(Thread thread,Object message){ String in1 = "[WARN]" + output(thread) + message; format(in1);}
    public void error(Thread thread,Object message){ String in1 = "[ERROR]" + output(thread) + message; format(in1);}
    public void fatal(Thread thread,Object message){ String in1 = "[FATAL]" + output(thread) + message; format(in1);}

    public String prefix() { return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS").format(System.currentTimeMillis()); }


    @Override
    public String toString(){ return content; }

    private void format(String in1){
        System.out.println(in1);
        content = content + in1 + "\n";
    }
    private String output(Thread thread){ return " "+prefix() +" "+ current(thread) +" ";}
    private static String current(Thread thread){
        String output;
        // 获取当前线程的堆栈跟踪元素数组
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        // 获取调用error方法的堆栈跟踪元素（排除掉CustomLogger类内部的方法和getStackTrace方法）
        if (stackTraceElements.length > 2) {
            StackTraceElement caller = stackTraceElements[2];
            // 获取方法名、类名、行号和包名
            String methodName = caller.getMethodName();
            String className = caller.getClassName();
            int lineNumber = caller.getLineNumber();
            output = "method:" + className + "." + methodName + "(" + (className.substring(className.lastIndexOf(".")+1)) + ".java:" + lineNumber +")";
        } else {
            output = "ERROR IN GETTING DETAILED INFORMATION";
        }
        return output;
    }
}
