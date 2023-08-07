package priv.samera2022;

import java.util.ArrayList;

public class Utils {
    public static boolean anyMatch(ArrayList<Object> target, ArrayList<Object> compared){
        return target.stream().anyMatch(compared::contains);
    }
    public static boolean allMatch(ArrayList<Object> target, ArrayList<Object> compared){
        return compared.containsAll(target);
    }
}
