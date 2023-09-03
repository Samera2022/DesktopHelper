package priv.samera2022.module.commands.registry;

import priv.samera2022.module.FontStyle;
import priv.samera2022.module.Mixture;
import priv.samera2022.module.annotation.Command;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static priv.samera2022.module.mainFrame.dsdFileContent;
import static priv.samera2022.module.mainFrame.dsdInput;

public class CommandHeads {
    //commands的0是指令头，依次往下分
    @Command(name = "print")
    public static void print(Mixture<Boolean,ArrayList<String>> mixture){
        boolean delete = mixture.getKey();
        ArrayList<String> commands = mixture.getValue();
        String content = (String) commands.get(1);
        output(new Mixture[]{new Mixture<>(content, FontStyle.blackStyle)}, delete);
//        System.out.println(commands.get(0));
    }

    private static void output(Mixture<String, Style>[] mixtures, boolean delete) {
        try {
            _timePrefix(dsdFileContent);
            for (Mixture<String, Style> message : mixtures) {
                dsdFileContent.insertString(dsdFileContent.getLength(), message.getKey(), message.getValue());
                //如果他正在输入命令的话
            }
            if (delete) dsdInput.remove(0,dsdInput.getLength());
        } catch (BadLocationException e){
            //ignore it.
        }
    }

    private static String getTime() { return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()); }
    public static void _timePrefix(DefaultStyledDocument dsdFileContent) throws BadLocationException {
        String time = "[" + getTime() + "]";
        if (dsdFileContent.getLength() != 0)
            dsdFileContent.insertString(dsdFileContent.getLength(), "\n", FontStyle.plainStyle);
        //如果不是第一行那就换行
        dsdFileContent.insertString(dsdFileContent.getLength(), time + " ", FontStyle.greyStyle);
    }
}
