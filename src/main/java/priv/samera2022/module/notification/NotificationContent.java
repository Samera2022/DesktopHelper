package priv.samera2022.module.notification;

import priv.samera2022.module.FontStyle;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;

public class NotificationContent {
    //警示！应当区分NotificationContent的内容与Notification的内容！
    private String content;
    public NotificationContent(String content){
        this.content = content;
    }

    public void append(DefaultStyledDocument dsd,int index) throws BadLocationException {
        String copy = content;
        int colorHead = copy.indexOf("[");
        if (colorHead!=-1) {
            String preColorContent = copy.substring(0, colorHead);
            if (!preColorContent.isEmpty()) {
                dsd.insertString(index, preColorContent, FontStyle.plainStyle);
                copy = copy.substring(colorHead);
            }
            while (!copy.isEmpty()) {
                int colorEnd = copy.indexOf("]");
                Style style = FontStyle.blackStyle;
                String colorType = copy.substring(1, colorEnd);
//                System.out.println(colorType);
                switch (colorType) {
                    //也许可以用style.getName()然后遍历。
                    case "blue":
                    case "BLUE":
                        style = FontStyle.blueStyle;
                        break;
                    case "dark_red":
                    case "DARK_RED":
                        style = FontStyle.darkRedStyle;
                        break;
                    case "yellow":
                    case "YELLOW":
                        style = FontStyle.yellowStyle;
                        break;
                    case "green":
                    case "GREEN":
                        style = FontStyle.greenStyle;
                        break;
                    case "grey":
                    case "GREY":
                        style = FontStyle.greyStyle;
                        break;
                    case "black":
                    case "BLACK":
                        style = FontStyle.blackStyle;
                        break;
                    default:
                        for (int i = 0; i < FontStyle.colorStyles.length; i++) {
                            Style element = FontStyle.colorStyles[i];
                            if (colorType.equals(element.getName())) {
                                style = element;
                            } else if (i == FontStyle.colorStyles.length - 1) {
                                style = FontStyle.blackStyle;
                                System.out.println("Color Not Found.");
                                break;
                            }
                        }
                        break;
                }
                copy = copy.substring(colorEnd);
                int nextColorStart = copy.indexOf("[");
                String content = copy.substring(1, nextColorStart != -1 ? nextColorStart : copy.length());
                dsd.insertString(index+preColorContent.length(), content, style);
                if (nextColorStart != -1) copy = copy.substring(nextColorStart);
                else copy = "";
            }
        } else {
            dsd.insertString(index,content,FontStyle.blackStyle);
        }
    }//该方法体可能存在问题，需要检查！
    public String contentToString() {
        String output = "";
        String copy = content;
        while (!copy.isEmpty()){
            int colorHead = copy.indexOf("[");
            int colorEnd = copy.indexOf("]");
            if (colorHead!=-1){
                String temp = copy.substring(0,colorHead);
                output += temp;
                copy = copy.substring(colorEnd+1);
            } else {
                output += copy;
                break;
            }
        }
        return output;
    }
    //contentToString返回的是纯文字信息，不带修饰。

    public String getContent() {
        return content;
    }
    //getContent返回的是所有信息，包括修饰
}
