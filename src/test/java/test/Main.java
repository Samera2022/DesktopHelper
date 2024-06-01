package test;

public class Main {
    public static void main(String[] args) {
        Logger logger = new Logger();
        logger.debug(Thread.currentThread(),"debug");
    }
}
