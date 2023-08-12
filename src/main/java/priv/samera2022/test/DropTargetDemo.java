//package priv.samera2022;
//
//import javax.imageio.ImageIO;
//import javax.swing.*;
//import javax.swing.text.DefaultStyledDocument;
//import java.awt.*;
//import java.awt.datatransfer.DataFlavor;
//import java.awt.datatransfer.Transferable;
//import java.awt.dnd.DnDConstants;
//import java.awt.dnd.DropTarget;
//import java.awt.dnd.DropTargetAdapter;
//import java.awt.dnd.DropTargetDropEvent;
//import java.awt.image.BufferedImage;
//import java.io.*;
//import java.nio.ByteBuffer;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//class TextFieldWithImage extends JTextField {
//    BufferedImage img;
//    TexturePaint texture;
//
//    public TextFieldWithImage() {
//        super();
//    }
//
//    public TextFieldWithImage(File file) throws IOException {
//        super();
//        img = ImageIO.read(file);
//        Rectangle rect = new Rectangle(0, 0, img.getWidth(null), img.getHeight(null));
//        texture = new TexturePaint(img, rect);
//        setOpaque(false);
//    }
//
//    public void setImage(File file) throws IOException {
//        img = ImageIO.read(file);
//        Rectangle rect = new Rectangle(0, 0, img.getWidth(null), img.getHeight(null));
//        texture = new TexturePaint(img, rect);
//    }
//
//    private void removeImage(){
//
//    }
//
//    @Override
//    // 重写父类方法，参考JDK可知，当Swing组件的paint方法被调用时，paintComponent、paintBorder、
//    // paintChildren这三个方法也会被按顺序调用，
//    public void paintComponent(Graphics g) {
//        Graphics2D g2 = (Graphics2D) g;
//        g2.setPaint(texture);
//        g.fillRect(0, 0, getWidth(), getHeight());
//        super.paintComponent(g);
//    }
//}
//
//// 注意这里没有实现DropTargetListener接口
//public class DropTargetDemo {
//    //    private static final long serialVersionUID = -8621029892661060165L;
//    private static DefaultStyledDocument dsd = new DefaultStyledDocument(Info.sc);
//    private static StringBuilder sb = new StringBuilder();
////    private static TextFieldWithImage ta = new TextFieldWithImage(); // 显示文件内容区域
//    private static List<ByteBuffer> buflist = new ArrayList<>(); // 在内存中保存文件
//    private static JTextPane ta = new JTextPane(dsd); // 显示文件内容区域
//
//
//
//    public static void DropTargetDemo() {
//
//        JTextField field1 = new JTextField();
//
//        JFrame frame = new JFrame();
//        frame.setSize(400, 300);
//        frame.setLocation(500, 300);
//        frame.setTitle("FileDrag");
//
//        new DropTarget(ta, DnDConstants.ACTION_COPY_OR_MOVE, readDoc);
//        ta.setEditable(false);
//
//        frame.setLayout(new BorderLayout());
//        ta.setEditable(false);
//        frame.add(ta, BorderLayout.CENTER);
//        frame.enableInputMethods(false);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setVisible(true);
//    }
//
//    //  增加一个DropTargetAdapter 对象
//    private static DropTargetAdapter readDoc = new DropTargetAdapter() {
//        // 里面内容没有变
//        @Override
//        public void drop(DropTargetDropEvent dtde) {
//            try {
//                if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) // 如果拖入的文件格式受支持
//                {
//                    dtde.acceptDrop(DnDConstants.ACTION_MOVE); // 接收拖拽来的数据
//                    @SuppressWarnings("unchecked")
//                    List<File> list = (List<File>) (dtde.getTransferable()
//                            .getTransferData(DataFlavor.javaFileListFlavor));
//                    for (File file : list) {
//                        sb.append(file.getAbsolutePath() + "\r\n");
//                        dtde.dropComplete(true); // 指示拖拽操作已完成
//                        FileInputStream fis = new FileInputStream(file.getAbsolutePath());
//                        byte[] buf = new byte[1024];
//                        int len = 0;
//                        ByteBuffer bb = ByteBuffer.allocate((int) file.length());
//// 向内存中写数据
//                        while ((len = fis.read(buf)) > -1) {
//                            bb.put(buf, 0, len);
//                        }
//                        buflist.add(bb);
//                        fis.close();
//// 在控件中显示文件内容，注意文件编码问题
//                        String name = file.getName();
//                        String suffix = name.substring(name.indexOf('.'));
//                        if (Info.textSuffix.contains(suffix)) {
//                            InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(bb.array()), "gbk");
//                            Scanner sc = new Scanner(isr);
//                            while (sc.hasNext()) {
//                                String t = sc.nextLine();
//                                dsd.insertString(start, s + " ", style);
////                                if (t.contains(": ")) t = Color.BLUE + t.substring(0,t.indexOf(": ")) + Color.BLACK + t.substring(t.indexOf(": "));
//                                sb.append(t).append("\n");
//                                System.out.println(t);
//                            }
//                            sc.close();
//                            isr.close();
//                            ta.setDocument(dsd);
//                        } else if (Info.picSuffix.contains(suffix)) {
////                            ta.setImage(file);
//                        }
//                    }
//                } else {
//                    dtde.rejectDrop();// 否则拒绝拖拽来的数据
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    };
//    private static DropTargetAdapter readPic = new DropTargetAdapter() {
//        public void drop(DropTargetDropEvent dtde) {
//            try {
//                Transferable tf = dtde.getTransferable();
//                if (tf.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
//                    dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
//                    List lt = (List) tf.getTransferData(DataFlavor.javaFileListFlavor);
//                    ArrayList<String> pics = new ArrayList<>();
//                    for (Object o : lt) {
//                        File f = (File) o;
//                        String picPath = f.getAbsolutePath();
//                        System.out.println(picPath);
//                        if (Info.picSuffix.stream().anyMatch(e -> e.equals(picPath.substring(picPath.indexOf('.')))) && (!pics.contains(picPath))) {
//                            pics.add(picPath);
//                            System.out.println("Add " + picPath);
//                        }
//                    }
//                    dtde.dropComplete(true);
//                } else {
//                    dtde.rejectDrop();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    };
//
//
//
//    public static void main(String[] args) {
//        DropTargetDemo();
//    }
//}
