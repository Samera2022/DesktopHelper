package priv.samera2022.module.font;

import javax.swing.text.Style;
import java.awt.*;

public class FontHandler {
    /**
     * @param input input是一个格式为[&xxx&xxx&xxx]的字符串
     * */
    public static Style resolveAttribute(String input) {
        GeneralFont gf = new GeneralFont();
        String[] arr = input.split("&");
        if (arr.length > 1) {
            //arr[0]是[
            String in1 = arr[arr.length - 1];
            arr[arr.length - 1] = in1.substring(0, in1.length() - 1);
            //去除最后一项的方括号
            for (int i = 1; i < arr.length; i++) {
                String para = arr[i];
                if (para.contains("FF"))
                    gf.setFontFamily(para.substring(3, para.length() - 1));
                if (para.contains("FS"))
                    gf.setFontSize(Integer.parseInt(para.substring(2)));
                if (para.contains("XA"))
                    gf.setBold(true);
                if (para.contains("XB"))
                    gf.setItalic(true);
                if (para.contains("XC"))
                    gf.setUnderline(true);
                if (para.contains("XD"))
                    gf.setStrikeThrough(true);
                if (para.contains("XE"))
                    gf.setSuperscript(true);
                if (para.contains("XF"))
                    gf.setSubscript(true);
                if (para.contains("CF"))
                    gf.setForeground(stringToColor(para.substring(2)));
                if (para.contains("CB"))
                    gf.setBackground(stringToColor(para.substring(2)));
                if (para.contains("SA"))
                    gf.setSpaceAbove(Integer.parseInt(para.substring(2)));
                if (para.contains("SB"))
                    gf.setSpaceBelow(Integer.parseInt(para.substring(2)));
                if (para.contains("FL"))
                    gf.setFirstLineIndent(Integer.parseInt(para.substring(2)));
                if (para.contains("LI"))
                    gf.setLeftIndent(Integer.parseInt(para.substring(2)));
                if (para.contains("RI"))
                    gf.setRightIndent(Integer.parseInt(para.substring(2)));
                if (para.contains("LS"))
                    gf.setLineSpacing(Integer.parseInt(para.substring(2)));
            }
            return gf.asStyle();
        }
        else return FontStyle.blackStyle;
    }

    public static String colorToString(Color color){
        return "&C("+(color.toString().substring(15,color.toString().length()-1).chars().filter(c -> Character.isDigit(c)||c==',').collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString())+")";
    }
    public static Color stringToColor(String color){
        String in1 = color.substring(3,color.length()-1);
        String[] arr = in1.split(",");
        int[] paras = new int[arr.length];
        for (int i = 0; i<arr.length; i++){
            paras[i] = Integer.parseInt(arr[i]);
        }
        return new Color(paras[0],paras[1],paras[2]);
    }
    public static String sizeToString(int size){
        return "&S" + size;
    }
    public static int stringToSize(String size) {
        return Integer.parseInt(size.substring(3));
    }
}
