package priv.samera2022.module.event;

import priv.samera2022.module.commands.CommandHandler;
import priv.samera2022.module.event.api.Event;
import priv.samera2022.module.event.events.EnterTypedKeyEvent;

import javax.swing.text.JTextComponent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class EventBus {
    public EventBus(){

    }
    public static void register(Event event){
        if (event instanceof EnterTypedKeyEvent){
            KeyEvent keyEvent = ((EnterTypedKeyEvent) event).getKeyEvent();
            String content = ((JTextComponent)keyEvent.getSource()).getText();
            ArrayList<String> commands = split(content);
            CommandHandler.handleCommands(commands);
        }
    }

    public static ArrayList<String> split(String content) {
        String copy = content;
        ArrayList<String> commands = new ArrayList<>();
        while (copy.length()>0){
                if ((copy.indexOf("\"") > copy.indexOf(" ")&&copy.contains(" ")&&copy.contains("\""))||(copy.contains(" ")&&!copy.contains("\""))) {
                    commands.add(copy.substring(0, copy.indexOf(" ")));
                    copy = copy.substring(copy.indexOf(" ") + 1);
                } else if ((copy.indexOf("\"") < copy.indexOf(" ")&&copy.contains(" ")&&copy.contains("\""))||(copy.contains("\"")&&!copy.contains(" "))){
                    copy = copy.substring(copy.indexOf("\"") + 1);
                    commands.add("\"" + copy.substring(0, copy.indexOf("\"")) + "\"");
                    if (copy.charAt(copy.length()-1)=='\"') {
                        copy = "";
                    } else {
                        copy = copy.substring(copy.indexOf("\"")+2);
                    }
                } else if (!copy.contains(" ")&&!copy.contains("\"")) {
                    commands.add(copy);
                    copy = "";
                }
        }
        return commands;
    }
}
