public class Test6 {
    public static void main(String[] args) {
        String in1;
//        String className = "priv.samera2022.module.file.FileHandler";
        String className = "priv.samera2022.module.gadgets.gpt.request.Header";
        int third = className.indexOf('.',className.indexOf('.',className.indexOf('.')+1)+1);
        String in2 = className.substring(third+1);
        if (in2.split("\\.").length>3)
//            in1 = className.substring(third+1,className.indexOf('.',third+1))+"/.../"+className.substring(className.lastIndexOf('.',className.lastIndexOf('.')-1)+1);
            in1 = className.substring(third+1);
        else in1 = in2;
        in1 = ".../" + in1;
        System.out.println(in1);
    }
}
