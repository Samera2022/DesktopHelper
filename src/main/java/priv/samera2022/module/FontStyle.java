package priv.samera2022.module;

import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import java.awt.*;

public class FontStyle {
    public static final StyleContext sc = new StyleContext();
    public static final Style blueStyle = sc.addStyle("BLUE", null);
    public static final Style plainStyle = sc.addStyle("BLACK",null);
    public static final Style yellowStyle = sc.addStyle("YELLOW",null);
    public static final Style darkRedStyle = sc.addStyle("RED",null);
    public static Style MDStyle = sc.addStyle("MD",null);
//    public static final Style MDHeadingStyle_1 = sc.addStyle("HEADING_1",null);
//    public static final Style MDHeadingStyle_2 = sc.addStyle("HEADING_2",null);
//    public static final Style MDHeadingStyle_3 = sc.addStyle("HEADING_3",null);
//    public static final Style MDHeadingStyle_4 = sc.addStyle("HEADING_4",null);
//    public static final Style MDHeadingStyle_5 = sc.addStyle("HEADING_5",null);
//    public static final Style MDHeadingStyle_6 = sc.addStyle("HEADING_6",null);
    static {

        register(blueStyle,Color.BLUE,true, 14);
        register(darkRedStyle,new Color(139,0,0),true,14);
        register(plainStyle,Color.BLACK,false,14);
        register(yellowStyle,Color.YELLOW,false,14);
        register(MDStyle,Color.BLACK,true,14);

//        register(MDHeadingStyle_1,Color.BLACK,true,28);
//        register(MDHeadingStyle_2,Color.BLACK,true,26);
//        register(MDHeadingStyle_3,Color.BLACK,true,24);
//        register(MDHeadingStyle_4,Color.BLACK,true,22);
//        register(MDHeadingStyle_5,Color.BLACK,true,20);
//        register(MDHeadingStyle_6,Color.BLACK,true,18);

    }

    private static void register(Style target, Color color, boolean isBold, int size){
        target.addAttribute(StyleConstants.Foreground, color);
        target.addAttribute(StyleConstants.FontSize, size);
        target.addAttribute(StyleConstants.Bold, isBold);
        //bold粗体
    }

    public static void changeSize(Style style, int size){
        style.removeAttribute(StyleConstants.FontSize);
        style.addAttribute(StyleConstants.FontSize,size);
    }

    /*  采用方法来修改标题字体大小的方式已被废弃

    public static Style MDHeading(int level){

    }

     */
}
