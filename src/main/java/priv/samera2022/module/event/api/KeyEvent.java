package priv.samera2022.module.event.api;

public class KeyEvent extends Event{
    private java.awt.event.KeyEvent keyEvent;
    public KeyEvent(java.awt.event.KeyEvent keyEvent){
        this.keyEvent = keyEvent;
    }
    public java.awt.event.KeyEvent getKeyEvent() {
        return keyEvent;
    }
}
