package priv.samera2022.old_module;

import priv.samera2022.module.FontStyle;
import priv.samera2022.module.Info;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.*;
import java.io.*;
import java.nio.ByteBuffer;
import java.util.*;
import java.util.List;


public class DropTargetOld {
    protected static DefaultStyledDocument dsd = new DefaultStyledDocument(FontStyle.sc);
    protected static StringBuilder sb = new StringBuilder();
    protected static List<ByteBuffer> buflist = new ArrayList<>(); // 在内存中保存文件
    protected static JTextPane jtp = new JTextPane(dsd); // 显示文件内容区域

    protected static ArrayList<String> potentialRisk = new ArrayList<>();

    public static void DropTarget(JPanel totalPanel){
        new java.awt.dnd.DropTarget(jtp, DnDConstants.ACTION_COPY_OR_MOVE, readDoc);
        jtp.setEditable(false);
        ScrollPane sp = new ScrollPane();
        sp.add(jtp);
        sp.setBounds(1244,100,300,400);
        totalPanel.add(sp);
    }

    //  增加一个DropTargetAdapter 对象
    protected static DropTargetAdapter readDoc = new DropTargetAdapter() {
        // 里面内容没有变
        @Override
        public void drop(DropTargetDropEvent dtde) {
            try {
                if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) // 如果拖入的文件格式受支持
                {
                    dtde.acceptDrop(DnDConstants.ACTION_MOVE); // 接收拖拽来的数据
                    @SuppressWarnings("unchecked")
                    List<File> list = (List<File>) (dtde.getTransferable()
                            .getTransferData(DataFlavor.javaFileListFlavor));
                    for (File file : list) {
                        sb.append(file.getAbsolutePath() + "\r\n");
                        dtde.dropComplete(true); // 指示拖拽操作已完成
                        FileInputStream fis = new FileInputStream(file.getAbsolutePath());
                        byte[] buf = new byte[1024];
                        int len = 0;
                        ByteBuffer bb = ByteBuffer.allocate((int) file.length());
                        // 向内存中写数据
                        while ((len = fis.read(buf)) > -1) {
                            bb.put(buf, 0, len);
                        }
                        buflist.add(bb);
                        fis.close();
                        // 在控件中显示文件内容，注意文件编码问题
                        String name = file.getName();
                        String suffix = name.substring(name.indexOf('.'));
                        if (Info.textSuffix.contains(suffix)) {
//                            boolean ifCan = false;
                            InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(bb.array()), "gbk");
                            Scanner sc = new Scanner(isr);
                            while (sc.hasNext()) {
                                String t = sc.nextLine();
                                Style style;
                                switch (suffix) {
                                    case ".yml":
                                    case ".YML":
                                        if (t.contains(": ")) {
                                            line(t,": ",FontStyle.blueStyle,FontStyle.plainStyle);
//                                            dsd.insertString(dsd.getLength(), t.substring(0, t.indexOf(": ")), FontStyle.blueStyle);
//                                            dsd.insertString(dsd.getLength(), t.substring(t.indexOf(": ")) + "\r\n", FontStyle.plainStyle);
                                        } else {
                                            dsd.insertString(dsd.getLength(), t+"\r\n", FontStyle.plainStyle);
//                                            dsd.insertString(dsd.getLength(), "\r\n", FontStyle.plainStyle);
                                        }
                                        break;
                                    case ".txt":
                                    case ".TXT":
                                        if (((t.contains("requires")||(t.contains("needs")))&&t.contains("Mod"))||
                                                Info.darkRedCode.stream().anyMatch(t::contains)) {
//                                            dsd.insertString(dsd.getLength(), t+"\r\n", FontStyle.darkRedStyle);
                                            style = FontStyle.darkRedStyle;
                                            potentialRisk.add(t);
                                        } else if (t.contains("Can't keep up! Did the system time change, or is the server overloaded?")) {
//                                            dsd.insertString(dsd.getLength(), t+"\r\n", FontStyle.yellowStyle);
                                            style = FontStyle.yellowStyle;
                                            potentialRisk.add(t);
                                        } else {
//                                            dsd.insertString(dsd.getLength(), t+"\r\n", FontStyle.plainStyle);
                                            style = FontStyle.plainStyle;
                                        }
                                        dsd.insertString(dsd.getLength(), t+"\r\n", style);
                                        break;
/*
                                    case ".md":
                                    case ".MD":
                                        int totalTimes = 0;
                                        if (t.contains("#")){
                                            int times = t.split("#").length;
                                            //标题暂时不做更改
                                        } else if (t.contains("")){

                                        } else {
                                            dsd.insertString(dsd.getLength(),t,FontStyle.plainStyle);
                                        }
                                        break;
  */
                                    default:
                                        System.out.println("UNSPECIFIED FILE");
                                }
                                sb.append(t).append("\n");
                                System.out.println(t);
                            }
                            sc.close();
                            isr.close();
                            jtp.setDocument(dsd);
                        } else if (Info.picSuffix.contains(suffix)) {
//                            ta.setImage(file);
                        }
                    }
                } else {
                    dtde.rejectDrop();// 否则拒绝拖拽来的数据
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private static void line(String string, String target, Style style, Style plain) throws BadLocationException {
        dsd.insertString(dsd.getLength(),string.substring(0,string.indexOf(target)),style);
        dsd.insertString(dsd.getLength(),string.substring(string.indexOf(target))+"\r\n",plain);
    }
}
