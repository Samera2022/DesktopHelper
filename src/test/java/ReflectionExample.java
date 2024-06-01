public class ReflectionExample {

    // 这个方法可以是任何调用B的方法，我们不知道它的名称
    public static void someMethod() {
        record();
    }

    // record方法尝试获取调用它的任意方法的信息
    public static void record() {
        String output = "";
        try {
            // 创建一个新的Throwable对象来捕获堆栈跟踪
            Throwable throwable = new Throwable();
            // 填充inStackTrace，然后获取堆栈跟踪
            StackTraceElement[] stackTraceElements = throwable.getStackTrace();

            // 遍历堆栈跟踪，找到调用B的方法
            for (StackTraceElement element : stackTraceElements) {
                // 跳过record方法本身和main方法
                if (!element.getMethodName().equals("record") && !element.getMethodName().equals("main")) {
                    // 输出找到的方法所在的类和方法名
                    System.out.println("Called from class: " + element.getClassName());
                    System.out.println("Called from method: " + element.getMethodName());
                    String className = element.getClassName();
                    String methodName = element.getMethodName();
                    int lineNumber = element.getLineNumber();
                    output = "method:" + className + "." + methodName + "(" + (className.substring(className.lastIndexOf(".")+1)) + ".java:" + lineNumber +")";
                    System.out.println(output);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        someMethod(); // 这将触发B方法，并在B方法中打印调用someMethod的信息
    }
}