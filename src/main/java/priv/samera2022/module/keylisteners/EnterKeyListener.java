package priv.samera2022.module.keylisteners;

import priv.samera2022.module.event.EventBus;
import priv.samera2022.module.event.events.EnterTypedKeyEvent;

import javax.swing.text.BadLocationException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static priv.samera2022.module.mainFrame.dsdInput;

public class EnterKeyListener implements KeyListener {
    private static boolean isCommand;
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER){
            EventBus.register(new EnterTypedKeyEvent(e));
            isCommand = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        try {
            if (e.getKeyCode() == KeyEvent.VK_ENTER && isCommand) {
                dsdInput.remove(0, dsdInput.getLength());
                isCommand = false;
            }
        } catch (BadLocationException badLocationException) {
            //ignore it
        }
    }
}
