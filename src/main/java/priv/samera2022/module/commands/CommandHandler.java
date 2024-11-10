package priv.samera2022.module.commands;

import priv.samera2022.module.FuzzyMatcher;
import priv.samera2022.module.config.ConfigHandler;
import priv.samera2022.module.font.FontStyle;
import priv.samera2022.module.annotation.Command;
import priv.samera2022.module.commands.registry.CommandHeads;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

import static priv.samera2022.module.commands.registry.CommandHeads._timePrefix;
import static priv.samera2022.module.mainFrame.dsdFileContent;

public class CommandHandler {
    private static final ArrayList<String> COMMAND_NAME = new ArrayList<>();
    private static final HashMap<String, Method> NAME_COMMAND_MAP = new HashMap<>();
    private static Style errorFont;

    static {
        Method[] methods = CommandHeads.class.getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Command.class)) {
                Command command = method.getAnnotation(Command.class);
                String name = command.name();
                if (name.equals("default")) name = method.getName();
                //这里可以用三元运算，但是没必要
                COMMAND_NAME.add(name);
                NAME_COMMAND_MAP.put(name, method);
            }
        }
        if (ConfigHandler.CONFIG.isDarkMode())
            errorFont = FontStyle.orangeStyle;
        else errorFont = FontStyle.blueStyle;
    }

    public static void handleCommands(ArrayList<String> commands) {
        try {
            String head = commands.get(0);//指令头

            String _string_switcher_1 = FuzzyMatcher.fuzzyMatch(head, COMMAND_NAME);//模糊匹配一下，看看是否能从COMMAND_NAME里面模糊匹配出一个
//            System.out.println(_string_switcher_1);
            boolean _boolean_switcher_1 = _string_switcher_1.isEmpty();//如果没匹配出这个玩意就是true，匹配出就是false
//            System.out.println(_boolean_switcher_1);
            String switcher_1 = _boolean_switcher_1 ? head:_string_switcher_1;//如果匹配出了就用匹配出来的，没匹配出就用原来的
//            System.out.println(switcher_1);
            if (!_boolean_switcher_1 && !_string_switcher_1.equals(head)) {//如果匹配出来并且匹配出来的东西跟原来的不一样
                //模糊匹配功能已启用！
                _fuzzyMatchInfo(dsdFileContent,switcher_1);
            }//模糊匹配+提示信息输出

//            Mixture<Boolean,ArrayList<String>> mixture = new Mixture<>(false,commands);
            Method method = NAME_COMMAND_MAP.get(switcher_1);
//            if (method.isAnnotationPresent(Command.class)) {
//                Command command = method.getAnnotation(Command.class);
//                boolean delete = command.delete();
//                mixture.setKey(delete);
//            }
            method.invoke(CommandHeads.class, commands);//方法调用
        } catch (IllegalAccessException | InvocationTargetException | BadLocationException e) {
            //ignore it.
        }
    }

    private static void _fuzzyMatchInfo(DefaultStyledDocument dsdFileContent, String command) throws BadLocationException {
        _timePrefix(dsdFileContent);
        dsdFileContent.insertString(dsdFileContent.getLength(), "\nCouldn't find that command! \nExecuting Command \"", errorFont);
        dsdFileContent.insertString(dsdFileContent.getLength(), command, FontStyle.darkRedStyle);
        dsdFileContent.insertString(dsdFileContent.getLength(), "\"!", errorFont);
//        dsdFileContent.insertString(dsdFileContent.getLength(), "\nCouldn't find that command! Guess you want to use command \"", FontStyle.blueStyle);
//        dsdFileContent.insertString(dsdFileContent.getLength(), command, FontStyle.darkRedStyle);
//        dsdFileContent.insertString(dsdFileContent.getLength(), "\"!", FontStyle.blueStyle);
//        dsdFileContent.insertString(dsdFileContent.getLength(), "\nExecuting Command \"", FontStyle.blueStyle);
//        dsdFileContent.insertString(dsdFileContent.getLength(), command, FontStyle.darkRedStyle);
//        dsdFileContent.insertString(dsdFileContent.getLength(), "\"!", FontStyle.blueStyle);
    }
}