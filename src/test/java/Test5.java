import priv.samera2022.module.Logger;

public class Test5 {

    public static final Logger logger = new Logger();

    public static void D(){
        logger.debug("test");
    }
    public static void main(String[] args) {
        D();
    }

    public static void main2(String[] args) {
        A();
    }
    public static void A(){
        B();
    }
    public static void B(){
        C();
    }
    public static void C() {
        try {
            // 创建一个新的Throwable对象来捕获堆栈跟踪
            Throwable throwable = new Throwable();
            // 获取堆栈跟踪
            StackTraceElement[] stackTraceElements = throwable.getStackTrace();

            // 遍历堆栈跟踪，找到调用链中B之前的任意方法
            boolean foundB = false;
            for (StackTraceElement element : stackTraceElements) {
                // 找到B方法后，下一个方法就是调用B的A
                if (element.getMethodName().equals("B")) {
                    foundB = true;
                    continue;
                }
                // 如果已经找到B，那么下一个就是调用B的方法
                if (foundB) {
                    System.out.println("B was called from class: " + element.getClassName());
                    System.out.println("B was called from method: " + element.getMethodName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
