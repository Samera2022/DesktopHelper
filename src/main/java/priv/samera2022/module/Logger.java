package priv.samera2022.module;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

public class Logger {
    private String content = "";
    public Logger(){}

    public static final String DEBUG = "[DEBUG]";
    public static final String INFO = "[INFO]";
    public static final String WARN = "[WARN]";
    public static final String ERROR = "[ERROR]";
    public static final String FATAL = "[FATAL]";
    private static final List<String> names = Arrays.asList("debug","info","warn","error","fatal");

    public void debug(Object message){ record(message,"[DEBUG]");}
    public void info(Object message){ record(message,"[INFO]");}
    public void warn(Object message){ record(message,"[WARN]");}
    public void error(Object message){ record(message,"[ERROR]");}
    public void fatal(Object message){ record(message,"[FATAL]");}

    public String prefix() { return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS").format(System.currentTimeMillis()); }


    @Override
    public String toString(){ return content; }

    private void format(String in1){
        System.out.println(in1);
        content = content + in1 + "\n";
    }

    public void record(Object message, Object... args) {
        String output;
        Object para;
        if (args.length!=0) para = args[0];
        else para = null;
        try {
            // 创建一个新的Throwable对象来捕获堆栈跟踪
            Throwable throwable = new Throwable();
            // 获取堆栈跟踪
            StackTraceElement[] stackTraceElements = throwable.getStackTrace();

            // 遍历堆栈跟踪，找到调用链中record之前的任意方法
            StackTraceElement result = null;
            for (int i = 0; i< stackTraceElements.length; i++) {
                StackTraceElement element = stackTraceElements[i];
                //普通调用路线：method->record
                //debug等调用路线：method->debug等->record
                if (element.getMethodName().equals("record")) {
                    StackTraceElement up1 = stackTraceElements[i+1];
                    if (!names.contains(up1.getMethodName())) result = up1;
                    else result = stackTraceElements[i+2];
                    //此处可以借着这个else，把debug等的名字获取过来全部UpperCase再加上括号的，但是想了想还是算了
                    //本来是这么想的，所以使用了para来传递，不然的话在format的时候对arg[0]用三元判断args[0]==null?"[RECORD]":args[0]就解决了
                    //后记：直接调用args[0]可能会抛数组越界，应该再加length的判断
                    if (para==null) para = "[RECORD]";
                    break;
                }
            }
            String className = result.getClassName();
            String methodName = result.getMethodName();
            int lineNumber = result.getLineNumber();
            output = "method:" + className + "." + methodName + "(" + (className.substring(className.lastIndexOf(".") + 1)) + ".java:" + lineNumber + ")";
            format(para + " "+prefix() +" "+ output +" " + message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
