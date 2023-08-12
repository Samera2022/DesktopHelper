package priv.samera2022.module;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.JTextComponent;
import javax.swing.text.Style;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DropTarget extends DropTargetAdapter {

    public static final int INPUT_ANALYZE = 1;
    public static final int FILE_CONTENT_ANALYZE = 2;

    private DefaultStyledDocument dsd;
    private List<ByteBuffer> buflist = new ArrayList<>(); // 在内存中保存文件
//    private JTextPane jtp; // 显示文件内容区域
//    private JTextArea jta;
    private JTextComponent jtc;
//    static {
//        jtp.setDocument(dsd);
//    }
    private int code;
    public DropTarget(int code, DefaultStyledDocument dsd, JTextComponent jtc) {
        this.code = code;
        this.dsd = dsd;
        this.jtc = jtc;
//        this.jtp = jtp;
//        this.jta = jta;
    }

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
                    switch (code) {
                        case INPUT_ANALYZE:
                            dsd.insertString(dsd.getLength(), file.getAbsolutePath(), FontStyle.plainStyle);
                            break;
                        case FILE_CONTENT_ANALYZE:
//                            sb.append(file.getAbsolutePath() + "\r\n");
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
                                InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(bb.array()), StandardCharsets.UTF_8);
                                Scanner sc = new Scanner(isr);
                                while (sc.hasNext()) {
                                    String t = sc.nextLine();
                                    Style style;
                                    switch (suffix) {
                                        case ".yml":
                                        case ".YML":
                                            if (t.contains(": ")) {
                                                line(t, ": ", FontStyle.yamlBlueStyle, FontStyle.plainStyle);
//                                            dsd.insertString(dsd.getLength(), t.substring(0, t.indexOf(": ")), FontStyle.blueStyle);
//                                            dsd.insertString(dsd.getLength(), t.substring(t.indexOf(": ")) + "\r\n", FontStyle.plainStyle);
                                            } else {
                                                dsd.insertString(dsd.getLength(), t + "\r\n", FontStyle.plainStyle);
//                                            dsd.insertString(dsd.getLength(), "\r\n", FontStyle.plainStyle);
                                            }
                                            break;
                                        case ".txt":
                                        case ".TXT":
                                            if (((t.contains("requires") || (t.contains("needs"))) && t.contains("Mod")) ||
                                                    Info.darkRedCode.stream().anyMatch(t::contains)) {
//                                            dsd.insertString(dsd.getLength(), t+"\r\n", FontStyle.darkRedStyle);
                                                style = FontStyle.darkRedStyle;
                                                //                                        potentialRisk.add(t);
                                            } else if (t.contains("Can't keep up! Did the system time change, or is the server overloaded?")) {
//                                            dsd.insertString(dsd.getLength(), t+"\r\n", FontStyle.yellowStyle);
                                                style = FontStyle.yellowStyle;
                                                //                                        potentialRisk.add(t);
                                            } else {
//                                            dsd.insertString(dsd.getLength(), t+"\r\n", FontStyle.plainStyle);
                                                style = FontStyle.plainStyle;
                                            }
                                            dsd.insertString(dsd.getLength(), t + "\r\n", style);
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
//                                    sb.append(t).append("\n");
                                    System.out.println(t);
                                }
                                sc.close();
                                isr.close();
                                jtc.setDocument(dsd);
//                                if (jtp!=null&&jta==null) jtp.setDocument(dsd);
//                                if (jtp==null&&jta!=null) jta.setDocument(dsd);
                            } else if (Info.picSuffix.contains(suffix)) {
//                            ta.setImage(file);
                            }
                            break;
                        default:
                            break;
                    }
                }
            } else {
                dtde.rejectDrop();// 否则拒绝拖拽来的数据
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void line(String string, String target, Style style, Style plain) throws BadLocationException {
        dsd.insertString(dsd.getLength(), string.substring(0, string.indexOf(target)), style);
        dsd.insertString(dsd.getLength(), string.substring(string.indexOf(target)) + "\r\n", plain);
    }

//    public JTextPane getJtp() {
//        return jtp;
//    }
}
