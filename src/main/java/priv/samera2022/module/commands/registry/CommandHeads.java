package priv.samera2022.module.commands.registry;

import priv.samera2022.module.*;
import priv.samera2022.module.annotation.Command;
import priv.samera2022.module.config.ConfigHandler;
import priv.samera2022.module.file.FileHandler;
import priv.samera2022.module.font.FontStyle;
import priv.samera2022.module.gadgets.aa_quiz.AminoAcidQuiz;
import priv.samera2022.module.gadgets.gpt.Connection;
import priv.samera2022.module.gadgets.gpt.response.Response;
import priv.samera2022.module.gadgets.mc.modpack.ModpackHandler;
import priv.samera2022.module.gadgets.mc.modpack.download.Concept;
import priv.samera2022.module.gadgets.mc.modpack.download.key.Download;
import priv.samera2022.module.gadgets.quiz.Quiz;
import priv.samera2022.module.notification.Notification;
import priv.samera2022.module.notification.NotificationContent;
import priv.samera2022.module.notification.NotificationFileHandler;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;

import java.awt.Toolkit;
import java.awt.ScrollPane;
import java.awt.Desktop;
import java.awt.dnd.DnDConstants;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

import static priv.samera2022.module.mainFrame.*;
import static priv.samera2022.module.mainFrame.dsdNotification;

public class CommandHeads {
    public static JFrame frame = new JFrame();

    static {
        int locX = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 320;
        int locY = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 100;
        mainFrame.logger.info("locX = " + locX);
        mainFrame.logger.info("locY = " + locY);
        frame.setBounds(310, 100, locX - 320, locY - 100);
        frame.setUndecorated(true);
        frame.enableInputMethods(false);
        JPanel totalPanel = new JPanel();
        totalPanel.setBounds(310, 100, locX - 320, locY - 100);
        JTextPane jtpFileContent = new JTextPane(dsdFileContent); // 显示文件内容区域
        jtpFileContent.setBounds(310, 100, locX - 320, locY - 100);
        DropTarget dtsFileContent = new DropTarget(DropTarget.FILE_CONTENT_ANALYZE, dsdFileContent, jtpFileContent);
        jtpFileContent.setEditable(false);
        ScrollPane sp2 = new ScrollPane();
        sp2.add(jtpFileContent);
        sp2.setBounds(310, 100, locX - 320, locY - 100);
        new java.awt.dnd.DropTarget(jtpFileContent, DnDConstants.ACTION_COPY_OR_MOVE, dtsFileContent);
        totalPanel.add(sp2);
        frame.add(totalPanel);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setVisible(false);
    }

    //commands的0是指令头，依次往下分
    @Command
    public static void print(ArrayList<String> commands) {
//        boolean delete = mixture.getKey();
//        ArrayList<String> commands = mixture.getValue();
        String content = commands.get(1);
        formatter(true, new Mixture<>(content, FontStyle.blackStyle));
//        System.out.println(commands.get(0));
    }

    //失败！存在问题！
    @Command
    public static void store(ArrayList<String> commands) {
//        boolean delete = mixture.getKey();
//        ArrayList<String> commands = mixture.getValue();
        String location = commands.get(1);
        mainFrame.logger.info(location);
        FileHandler.write(FileHandler.FOLDER_PATH + FileHandler.STORAGE_NAME, location + "\n", true);

        formatter(true, new Mixture<>("Store Location Successfully!", FontStyle.greenStyle));
    }

    @Command
    public static void clear(ArrayList<String> commands) {
        try {
            dsdFileContent.remove(0, dsdFileContent.getLength());
        } catch (BadLocationException e) {
            e.printStackTrace();
            mainFrame.ExceptionHandler(e);
        }
    }

    @Command
    public static void exit(ArrayList<String> commands) {
        System.exit(0);
    }

    @Command
    public static void delete(ArrayList<String> commands) {
//        boolean delete = mixture.getKey();
        String filePath = commands.get(1);
        Path path = Paths.get(filePath);
        if (Files.exists(path.resolve(new File(filePath).getName()))) {
            Desktop.getDesktop().moveToTrash(new File(filePath));
            formatter(true, new Mixture<>("Operation inspecting... Please Wait.", FontStyle.blackStyle));
            Thread thread = new Thread() {
                @Override
                public void run() {
                    super.run();
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            thread.start();
            if (!Files.exists(path.resolve(new File(filePath).getName())))
                formatter(true, new Mixture<>("Delete Successfully!", FontStyle.greenStyle));
            else
                formatter(true, new Mixture<>("Delete UnSuccessfully!", FontStyle.darkRedStyle));
        } else
            formatter(true, new Mixture<>("File(Folder) Not Found!", FontStyle.darkRedStyle));
    }

    @Command
//    @Command(set = {"broaden","close","dispose"})
    public static void frame(ArrayList<String> commands) {
//        boolean delete = mixture.getKey();
//        ArrayList<String> commands = mixture.getValue();
        String rawSubCommand = commands.get(1);
        String subCommand = FuzzyMatcher.fuzzyMatch(rawSubCommand, Arrays.asList("broaden", "close", "dispose"));
        //常见异常：在switch case里面写了指令但是却在fuzzyMatch的数组里忘记加上这个指令
        switch (subCommand) {
            case "broaden":
                clearInput();
                frame.setVisible(true);
                break;
            case "close":
                frame.setVisible(false);
                break;
            case "dispose":
                frame.dispose();
                formatter(true, new Mixture<>("窗体已清除", FontStyle.blackStyle));
                break;
            default:
                formatter(true, new Mixture<>("尽管这条消息肯定不会出现在控制台上，下面的break也没有实际用途，但是还是写上了。", FontStyle.blackStyle));
                break;
        }
    }

    @SuppressWarnings("unused")
    private static int count(String str, String target) {
        return (str.length() - str.replaceAll(target, "").length()) / target.length();
    }

    @Command
    public static void AminoAcidQuiz(ArrayList<String> commands){
        String rawSubCommand = commands.get(1);
        String subCommand = FuzzyMatcher.fuzzyMatch(rawSubCommand, Arrays.asList("plain", "structure"));
        String rawSubCommand2 = commands.get(2);
        String subCommand2 = FuzzyMatcher.fuzzyMatch(rawSubCommand2, Arrays.asList("stop", "continue"));
        boolean isBreak = subCommand2.equals("stop");
        int time = 0;
        switch (subCommand) {
            case "plain":
                time = AminoAcidQuiz.plainQuiz(isBreak);
                break;
            case "structure":
                time = AminoAcidQuiz.structureQuiz(isBreak);
                break;
            default:
                break;
        }
        formatter(true, new Mixture<>("答题结束！这次一共"+(isBreak?"答对":"作答") + time + "次，下次继续努力吧！", FontStyle.greenStyle));
    }

    @Command
    public static void label(ArrayList<String> commands){
        String rawSubCommand = commands.get(1);

    }

    @Command
    public static void quiz(ArrayList<String> commands) {
//        boolean delete = mixture.getKey();
//        ArrayList<String> commands = mixture.getValue();
        String rawSubCommand = commands.get(1);
        String subCommand = FuzzyMatcher.fuzzyMatch(rawSubCommand, Arrays.asList("create", "start", "delete", "list"));
        //常见异常：在switch case里面写了指令但是却在fuzzyMatch的数组里忘记加上这个指令
        switch (subCommand) {
            case "create":
                Quiz.input(commands.get(2));
                if (new File(FileHandler.FOLDER_PATH + FileHandler.QUIZ_PATH + commands.get(2) + ".txt").exists())
                    formatter(true, new Mixture<>("创建成功!", FontStyle.greenStyle));
                else formatter(true, new Mixture<>("创建失败!", FontStyle.darkRedStyle));
                break;
            case "start":
                String rawSubCommand2 = commands.get(3);
                String subCommand2 = FuzzyMatcher.fuzzyMatch(rawSubCommand2, Arrays.asList("stop", "continue"));
                String rawSubCommand3 = commands.get(4);
                String subCommand3 = FuzzyMatcher.fuzzyMatch(rawSubCommand3, Arrays.asList("true", "false"));
                String result = Quiz.examine(commands.get(2),subCommand2.equals("stop"),subCommand3.equals("true"));
                switch (subCommand2) {
                    case "stop":
                        boolean digit = false;
                        for (char c : result.toCharArray()) {
                            if (Character.isDigit(c)) {
                                digit = true;
                                break;
                            }
                        }
                        if (digit) formatter(true, new Mixture<>("这次一共答对了" + result + "次，下次继续努力吧！", FontStyle.greenStyle));
                        else formatter(true, new Mixture<>(result, FontStyle.darkRedStyle));
                        //该种情况是由于反馈了“测试不存在”所导致的。应当考虑整合到start就进行判断
                        break;
                    case "continue":
                        formatter(true, new Mixture<>("答题结束！这次一共作答" + result + "次，下次继续努力吧！", FontStyle.greenStyle));
                        break;
                    default:
                        break;
                }
                break;
            case "delete":
                File file1 = new File(FileHandler.FOLDER_PATH + FileHandler.QUIZ_PATH + commands.get(2) + ".txt");
                if (JOptionPane.showConfirmDialog(null, "是否确定删除该测试？删除后该测试将无法被恢复！", "警告", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    if (file1.exists()) {
                        if (file1.delete()) formatter(true, new Mixture<>("已成功删除该测试", FontStyle.greenStyle));
                        else formatter(true, new Mixture<>("删除该测试失败!", FontStyle.darkRedStyle));
                    } else formatter(true, new Mixture<>("该测试不存在!", FontStyle.darkRedStyle));
                }
                break;
            case "list":
                Path directory = Paths.get(FileHandler.FOLDER_PATH + FileHandler.QUIZ_PATH);
                ArrayList<String> names = new ArrayList<>();
                try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(directory)) {
                    for (Path path : directoryStream) {
                        String name = Paths.get(path.getFileName().toString()).toString();
                        String[] parts = name.split("\\.");
                        if (parts.length > 0) {
                            name = parts[0]; // 获取不包含扩展名的文件名
                        }
                        names.add(name);
                        mainFrame.logger.info("文件名："+name);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    mainFrame.ExceptionHandler(e);
                }
                if (names.size() != 0) {
                    StringBuilder output = new StringBuilder();
                    for (String content : names) {
                        //在不能用lambda表达式的时候，for循环也不失是一种好选择
                        output.append(content).append(", ");
                    }
                    output = new StringBuilder(output.substring(0, output.lastIndexOf(", ")));
                    formatter(true, new Mixture<>("找到以下测试: \n", FontStyle.blackStyle), new Mixture<>(output.toString(), FontStyle.blueStyle));
                } else formatter(true, new Mixture<>("暂无测试", FontStyle.blackStyle));
                break;
            default:
                formatter(true, new Mixture<>("尽管这条消息肯定不会出现在控制台上，下面的break也没有实际用途，但是还是写上了。", FontStyle.blackStyle));
                break;
        }
    }

    @Command
    public static void openai(ArrayList<String> commands) {
        formatter(true, new Mixture<>("请等待，正在请求中......", FontStyle.blackStyle));
        String content = commands.get(1);
        clearInput();
        Response response = Connection.question(content);
        formatter(true, new Mixture<>("CompletionTokens: " + response.getUsage().getCompletionTokens()
                + "        " + "Model: " + response.getModel() + "        " + "Content: \n" +
                response.getChoices().getMessage().getContent(), FontStyle.blackStyle));
    }

    @Command
    public static void notification(ArrayList<String> commands){
        try {
            String command_2 = commands.get(1);
            String _string_switcher_2 = FuzzyMatcher.fuzzyMatch(command_2, Arrays.asList("add", "remove", "finished", "unfinished"));
            //notification <command_2>
            switch (_string_switcher_2) {
                case "add": {
                    String rawContent = commands.get(2);
                        String rContent = rawContent.substring(rawContent.indexOf("}") + 1);
                        mainFrame.logger.info("提示内容："+rContent);
                        String head = rawContent.substring(rawContent.indexOf("{") + 1, rawContent.indexOf("}"));
                        String[] splits = head.split(",");

                        String startDate;
                        boolean isFinished;
                        String endDate;
                        Notification n;

                        switch (splits.length) {
                            case 1:
                                endDate = splits[0];
                                n = new Notification(new NotificationContent(rContent), false, getTime(), endDate);
                                break;
                            case 3:
                                isFinished = Boolean.parseBoolean(splits[0]);
                                startDate = splits[1];
                                endDate = splits[2];
                                n = new Notification(new NotificationContent(rContent), isFinished, startDate, endDate);
                                break;
                            default:
                                mainFrame.logger.warn("No Matching Result!");
                                n = null;
                                break;
                        }
                        if (n != null) {
                            NotificationFileHandler.notifications.add(n);
                            //index是预备序号的意思
                            //下列逻辑是为了判断是否存在预备序号
                            if (commands.size()>3) {
                                String content = dsdNotification.getText(0,dsdNotification.getLength());
                                int index = Integer.parseInt(commands.get(3));
                                NotificationFileHandler.insertString(n.toString(),index);
                                if (NotificationFileHandler.notifications.size() < index) {
                                    formatter(true, new Mixture<>("Index Out Of Bounds!", FontStyle.darkRedStyle));
                                } else {
                                    NotificationFileHandler.notifications.add(index - 1, n);
                                    int length = 0;
                                    for (int i = 0; i < index - 1; i++) {
                                        Notification nLoop = NotificationFileHandler.notifications.get(i);
                                        length += nLoop.getContent().contentToString().length() + 3 + 1;
                                    }
                                    int lengthOfN;//字面意思，为换行所预留的长度
                                    if (index != 1) {
                                        dsdNotification.insertString(length, "\n", FontStyle.blackStyle);
                                        lengthOfN = 1;
                                        length -= 1;//不知道为什么，但是这么加就能用了。
                                    } else
                                        lengthOfN = 0;
                                    if (n.isFinished()) dsdNotification.insertString(length + lengthOfN, "[√]", FontStyle.greenStyle);
                                    else dsdNotification.insertString(length + lengthOfN, "[×]", FontStyle.darkRedStyle);
                                    n.getContent().append(dsdNotification, length + lengthOfN + 3);
                                    if (index == 1)
                                        dsdNotification.insertString(n.getContent().contentToString().length() + 3, "\n", FontStyle.blackStyle);
                                }
                            } else {
                                NotificationFileHandler.append(n.toString());
                                if (n.isFinished())
                                    dsdNotification.insertString(dsdNotification.getLength(), "[√]", FontStyle.greenStyle);
                                else
                                    dsdNotification.insertString(dsdNotification.getLength(), "[×]", FontStyle.darkRedStyle);
                                n.getContent().append(dsdNotification, dsdNotification.getLength());
                                dsdNotification.insertString(dsdNotification.getLength(), "\n", FontStyle.blackStyle);
                                //startDate和endDate暂未加入使用
                            }
                            formatter(true, new Mixture<>("Notification added Successfully!", FontStyle.greenStyle));
                        } else {
                            formatter(true, new Mixture<>("Error In Notification Pattern!", FontStyle.darkRedStyle));
                        }
                    break;
                }
                case "remove": {
                    int indexForHuman = Integer.parseInt(commands.get(2));
                    int index = indexForHuman - 1;
                    if (NotificationFileHandler.notifications.size() < index) {
                        formatter(true, new Mixture<>("Index Out Of Bounds!", FontStyle.darkRedStyle));
                    } else {
                        Notification backNotification = NotificationFileHandler.notifications.get(index);
                        if (index!=0) {
                            int length = 0;
                            //应当考虑index为最后一个的情况
                            for (int i = 0; i < index + 1; i++) {
                                Notification notification = NotificationFileHandler.notifications.get(i);
                                length += notification.getContent().contentToString().length() + 3;
                            }
                            NotificationFileHandler.notifications.remove(index);
                            dsdNotification.remove(length, backNotification.getContent().contentToString().length() + 3 + 1);
                            //+3是用于消除[×]和[√]，+1是为了消除换行
                        }
                        dsdNotification.remove(0, backNotification.getContent().contentToString().length() + 3 + 1);
                        NotificationFileHandler.notifications.remove(index);
                        FileHandler.deleteLine(FileHandler.FOLDER_PATH + FileHandler.NOTIFICATION_NAME, indexForHuman);
                        formatter(true, new Mixture<>("Notification Removed!", FontStyle.greenStyle));
                    }
                    break;
                }
                case "unfinished":
                    _finish(false, Integer.parseInt(commands.get(2)), dsdNotification);
                    break;
                case "finished":
                    _finish(true, Integer.parseInt(commands.get(2)), dsdNotification);
                    break;
                default:
                    formatter(true, new Mixture<>("\nYou might want to execute commands:\nnotification add \"{heads}<message>\" <index>\nnotification remove <index>\n", FontStyle.darkRedStyle));
                    break;
            }
    } catch (BadLocationException e){
            e.printStackTrace();;
        }
    }

    private static void _finish(boolean finishingMode, int indexForHuman, DefaultStyledDocument dsdNotification) {
        try {
            int index = indexForHuman - 1;
            if (NotificationFileHandler.notifications.size() < index) {
                formatter(true, new Mixture<>("Index Out Of Bounds!", FontStyle.darkRedStyle));
            } else {
                Notification previousNotification = NotificationFileHandler.notifications.get(index);
                previousNotification.setFinished(finishingMode);
                NotificationFileHandler.notifications.set(index, previousNotification);
                int length = 0;
                for (int i = 0; i < index; i++) {
                    Notification notification = NotificationFileHandler.notifications.get(i);
                    length += notification.getContent().contentToString().length() + 3;
                }
                dsdNotification.replace(length, 3, finishingMode ? "[√]" : "[×]", finishingMode ? FontStyle.greenStyle : FontStyle.darkRedStyle);
                formatter(true, new Mixture<>(finishingMode ? "Notification Task Completed!" : "Notification Task Uncompleted!", finishingMode ? FontStyle.greenStyle : FontStyle.darkRedStyle));
                __finish(index, previousNotification);
            }
        } catch (BadLocationException e) {
            //ignore it.
            e.printStackTrace();
        }
    }

    private static void __finish(int lineIndex, Notification subsequentNotification) {
        try {
            File f = new File(FileHandler.FOLDER_PATH + FileHandler.NOTIFICATION_NAME);
            if (f.isFile() && f.exists()) {
                InputStreamReader isr = new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);
                String content;
                ArrayList<String> allContent = new ArrayList<>();
                while ((content = br.readLine()) != null) {
                    allContent.add(content);
                }
                allContent.set(lineIndex, subsequentNotification.toString());
                br.close();
                isr.close();
                FileWriter fw = new FileWriter(f, StandardCharsets.UTF_8, false);
                BufferedWriter bw = new BufferedWriter(fw);
                StringBuilder processedContent = new StringBuilder();
                for (int i = 0; i < allContent.size(); i++) {
                    String line = allContent.get(i);
                    processedContent.append(line).append(i == allContent.size() - 1 ? "" : "\n");
                }
                bw.write(processedContent.toString());
                bw.close();
                fw.close();
            }
        } catch (IOException e) {
            //ignore it.
        }
    }

    @Command
    public static void config(ArrayList<String> commands) {
        String rawSubCommand = commands.get(1);
        String subCommand = FuzzyMatcher.fuzzyMatch(rawSubCommand, Arrays.asList("reload"));
        switch (subCommand) {
            case "reload":
                priv.samera2022.module.config.ConfigHandler.reload();
                break;
            default:
                formatter(true, new Mixture<>("尽管这条消息肯定不会出现在控制台上，下面的break也没有实际用途，但是还是写上了。", FontStyle.blackStyle));
                break;
        }
    }

    @Command
    public static void analyze(ArrayList<String> commands) {
        try {
            String path = commands.get(1);
            String content = FileHandler.read(path);
            File file = new File(path);
            FileInputStream fis = new FileInputStream(file.getAbsolutePath());
            byte[] buf = new byte[1024];
            int len;
            ByteBuffer bb = ByteBuffer.allocate((int) file.length());
            // 向内存中写数据
            while ((len = fis.read(buf)) > -1) {
                bb.put(buf, 0, len);
            }
            fis.close();
            // 在控件中显示文件内容，注意文件编码问题
            InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(bb.array()), StandardCharsets.UTF_8);
            Scanner sc = new Scanner(isr);
            int line = 0;
            while (sc.hasNext()) {
                line++;
                String t = sc.nextLine();
                if (t.indexOf("Thread: ") == 0) {
                    switch (t) {
                        case "Thread: Render Thread":
                            formatter(true, new Mixture<>("渲染进程(Render Thread)报错！\n", FontStyle.darkRedStyle));
                            if (content.contains("Optifine"))
                                formatter(true, new Mixture<>("Optifine已安装！\n", FontStyle.darkRedStyle));
                            else
                                formatter(true, new Mixture<>("Optifine未安装。\n", FontStyle.blackStyle));
                            break;
                        case "Thread: Client thread":
                            formatter(true, new Mixture<>("客户端进程(Client Thread)报错！\n", FontStyle.blackStyle));
                            break;
                    }
                }
                if (t.contains("LE") || t.contains("LCHIJE")) {
                    String[] arr = t.split("\\|");
                    formatter(true, new Mixture<>("\n异常可能点：" +
                                    "\n模组注册名: " + arr[2] + "\n模组名称: " + arr[4], FontStyle.darkRedStyle),
                            new Mixture<>("\n分析指示：可能该模组出现异常或存在模组互相冲突\n" +
                                    "报错切片如下：\n" + t + "\n" +
                                    "该异常位于第" + line + "行", FontStyle.blackStyle));
                } else if (t.contains("unable to find valid certification path to requested target")||t.contains("PKIX path building failed")) {
                    formatter(true, new Mixture<>("\n异常可能点：Java出现异常。",FontStyle.darkRedStyle), new Mixture<>(
                            "可能原因（仅供参考）：这个问题的根本原因是你安装JDK时，Java\\jar 1.8.0_141\\lib\\ext\\里面缺少了一个安全凭证jssecacerts证书文件，通过运行下面类可以生成证书，将生成的证书放在Java\\jar 1.8.0_141\\lib\\ext\\这个目录下，重启编译器就可以解决。\n" +
                            "可能的解决办法：" +
                            "1. (实践可行)重新安装Java" +
                            "2. 生成安全证书并放入jre相应路径下，参照网页https://blog.csdn.net/weixin_44519124/article/details/119909354",FontStyle.blackStyle));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Command
    public static void downloadModpacks(ArrayList<String> commands) {
        String rawSubCommand = commands.get(1);
        String subCommand = FuzzyMatcher.fuzzyMatch(rawSubCommand, Arrays.asList("key","browser"));
        if (ConfigHandler.CONFIG.getCf_api_key()!=null) {
            ModpackHandler.handle(commands.get(2),commands.get(3),subCommand);
            switch (subCommand) {
                case "key":
                    //zip在前，目录在后
                    File[] filesN = new File(commands.get(3)+"\\necessary\\").listFiles();
                    File[] filesO = new File(commands.get(3)+"\\optional\\").listFiles();
                    int optionalMods = filesO==null ? 0 : filesO.length;
                    if (filesN != null && filesN.length > 0) {
                        formatter(true,new Mixture<>("检验中，存在下载内容。",FontStyle.blackStyle));
                        int mods = priv.samera2022.module.gadgets.mc.modpack.download.browser.Download.getNum(commands.get(2));
                        if (filesN.length+optionalMods==mods) {
                            formatter(true,new Mixture<>("模组全部下载完成，共"+mods+"个！",FontStyle.greenStyle));
                        } else {
                            formatter(true,new Mixture<>("模组下载未完成，应有"+mods+"个，实有"+(filesN.length+optionalMods)+"个！",FontStyle.greenStyle));
                            //TODO 缺失文件输出本地检查以后再写
                        }
                    } else {
                        formatter(true,new Mixture<>("不存在下载内容，下载失败！",FontStyle.darkRedStyle));
                    }
                    if (Download.ERROR_LIST.size()!=0) {
                        formatter(true,new Mixture<>("下载未完成，失败文件列表如下所示：",FontStyle.darkRedStyle));
                        for (HashMap<String,Object> map : Download.ERROR_LIST){
                            formatter(true, new Mixture<>("\nmodId: "+map.get(Concept.PROJECT_ID)+"\nfileId: "+map.get(Concept.FILE_ID)+"\nrequired: "+map.get(Concept.REQUIRED)+"\n--------",FontStyle.blackStyle));
                        }
                    }
                    break;
                case "browser":
//                    priv.samera2022.module.gadgets.mc.modpack.download.browser.Download.downloadModpacks(commands.get(2));
                    break;
                default:
                    break;
            }
        } else priv.samera2022.module.gadgets.mc.modpack.download.browser.Download.downloadModpacks(commands.get(2));
    }

    @Command
    public static void cookie(){

    }

    private static String[] convertStringToArray(String input) {
        String cleanedInput = input.replaceAll("[{}]", "");
        String[] parts = cleanedInput.split(",");
        String[] result = new String[parts.length];
        for (int i = 0; i < parts.length; i++)
            result[i] = parts[i].trim().replaceAll("\"", "");
        return result;
    }

    /**
     * @author Samera2022
     * 该方法用于清空input栏内的指令信息。
     * */
    private static void clearInput() {
        try {
            dsdInput.remove(0, dsdInput.getLength());
            dsdInput.insertString(0, mainFrame.inputAsst, FontStyle.plainStyle);
        } catch (BadLocationException e) {
            e.printStackTrace();
            mainFrame.ExceptionHandler(e);
        }
    }

    /**
     * @author Samera2022
     * 使用formatter的目的是为了更方便地输出,举例如下。
     * 这是一个来自print的标准输出方案：
     * output(new Mixture[]{new Mixture<>(content, FontStyle.blackStyle)}, delete);
     * 这种输出方案不仅会让编译器提示Unchecked assignment: 'priv.samera2022.module.Mixture[]' to 'priv.samera2022.module.Mixture<java.lang.String,javax.swing.text.Style>[]'，
     * 而且还无法使用@SafeVarargs注解来消除，因为该方法参数是固定的，不是可变参数方法。
     * 而使用formatter的方案如下：
     * formatter(delete,new Mixture<>(content, FontStyle.blackStyle));
     * 不仅不需要新建数组，而且可以利用可变参数的函数在调用的过程中就产生一个抽象的数组来输出，
     * 而这个数组在可变参数的函数中被套上了Mixture<String,Style>的泛型，就可以使用@SafeVarargs提示已经除去了堆污染和泛型错误。
     * 简单地来解释，就是 多个抽象元素(具体元素也可以，只不过cast了也一样)->调用可变参数函数->(在可变参数函数中)形成数组，并在这个时候对数组加上泛型cast->警告用@SafeVarargs消除
     */

    @SafeVarargs
    public static void formatter(boolean delete, Mixture<String, Style>... objects) {
        output(objects, delete);
    }

    private static void output(Mixture<String, Style>[] mixtures, boolean delete) {
        try {
            _timePrefix(dsdFileContent);
            for (Mixture<String, Style> message : mixtures) {
                dsdFileContent.insertString(dsdFileContent.getLength(), message.getKey(), message.getValue());
                //如果他正在输入命令的话
            }
            if (delete) dsdInput.remove(0, dsdInput.getLength());
        } catch (BadLocationException e) {
            //ignore it.
        }
    }

    private static String getTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
    }

    public static void _timePrefix(DefaultStyledDocument dsdFileContent) throws BadLocationException {
        String time = "[" + getTime() + "]";
        if (dsdFileContent.getLength() != 0)
            dsdFileContent.insertString(dsdFileContent.getLength(), "\n", FontStyle.plainStyle);
        //如果不是第一行那就换行
        dsdFileContent.insertString(dsdFileContent.getLength(), time + " ", FontStyle.greyStyle);
    }
}
