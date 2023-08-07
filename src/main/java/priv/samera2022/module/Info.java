package priv.samera2022.module;

import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Info {
    public static final ArrayList<String> picSuffix = new ArrayList<>();
    public static final ArrayList<String> textSuffix = new ArrayList<>();

    public static final ArrayList<String> darkRedCode = new ArrayList<>();

    static {
        picSuffix.addAll(Arrays.asList(".png",".jpg",".jpeg"));
        textSuffix.addAll(Arrays.asList(".txt",".log",".yml",".properties",".md"));

        darkRedCode.addAll(Arrays.asList("java.lang.IllegalArgumentException: Name and ID cannot both be blank",
                "joptsimple.MultipleArgumentsForOptionException: Found multiple arguments for option gameDir, but you asked for only one",
                "[main/ERROR] [ModDirector[CORE]]: Failed to install mod",
                "java.lang.UnsatisfiedLinkError: Failed to dynamically load library",
                "java.io.IOException: MD5 not matching, name:",
                "[LWJGL] Failed to load a library. Possible solutions:",
                "joptsimple.MissingRequiredOptionsException: Missing required option(s)"));
        /*
        离线玩家改正版玩家，或用正版玩家皮肤+名字
        需要选一个用户名启动
        缺了一个mod
        缺运行库了，要么没下载完成要么显卡驱动有问题 或者只是单纯的缺
        游戏文件缺失
        显卡驱动有问题，一般是 集成显卡在win10下运行 因缺失该驱动 所导致的。
        推测应该与第二个解决方法类似
         */
    }
}
