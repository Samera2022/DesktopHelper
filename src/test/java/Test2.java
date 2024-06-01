//import priv.samera2022.module.annotation.Command;
//import java.lang.reflect.Method;
//
//public class Test2 {
//    @Command(set = {"{\"value1\",\"value2\"}","{\"value3\",\"value4\"}"})
//    public static void test() {
//        // 获取当前方法的引用
//        Method currentMethod = getCalledMethod();
//        String[][] set = getMethodSet(currentMethod);
//        for (int i = 0; i < set.length; i++) {
//            String[] set2 = set[i];
//            System.out.println("i=" + i);
//            for (String s : set2) {
//                System.out.println(s);
//            }
//        }
//    }
//
//    private static Method getCalledMethod() {
//        // 通过当前线程的堆栈获取当前方法
//        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
//        for (StackTraceElement ste : stackTraceElements) {
//            if (ste.getClassName().equals(Test2.class.getName()) && ste.getMethodName().equals("test")) {
//                try {
//                    return Test2.class.getDeclaredMethod(ste.getMethodName());
//                } catch (NoSuchMethodException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return null;
//    }
//
//    public static void main(String[] args) {
//        try {
//            // 反射调用 test 方法
//            Test2.class.getMethod("test").invoke(null);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    // 工具方法，接收 Method 对象作为参数
//    private static String[][] getMethodSet(Method method) {
//        if (method != null && method.isAnnotationPresent(Command.class)) {
//            Command command = method.getAnnotation(Command.class);
//            String[] set = command.set();
//            String[][] output = new String[set.length][];
//            for (int i = 0; i < set.length; i++) {
//                String[] arr = convertStringToArray(set[i]);
//                output[i] = new String[arr.length];
//                System.arraycopy(arr, 0, output[i], 0, arr.length);
//            }
//            return output;
//        }
//        return null;
//    }
//
//    private static String[] convertStringToArray(String input) {
//        String cleanedInput = input.replaceAll("[{}]", "");
//        String[] parts = cleanedInput.split(",");
//        String[] result = new String[parts.length];
//        for (int i = 0; i < parts.length; i++) {
//            result[i] = parts[i].trim().replaceAll("\"", "");
//        }
//        return result;
//    }
//}