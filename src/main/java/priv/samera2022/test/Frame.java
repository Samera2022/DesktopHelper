package priv.samera2022.test;

import priv.samera2022.module.Info;

import javax.swing.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Frame extends JFrame {
//    static String path;
    public static void frame(){
        JFrame frame = new JFrame();
        frame.setBounds(150, 150, 300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
//        frame.setBackground(new Color(0, 0, 0, 0));
        JTextArea jta = new JTextArea();
        DropTargetAdapter kgd = new DropTargetAdapter() {
            public void drop(DropTargetDropEvent dtde) {
                try {
                    Transferable tf = dtde.getTransferable();
                    if (tf.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                        dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
                        List lt = (List) tf.getTransferData(DataFlavor.javaFileListFlavor);
                        ArrayList<String> pics = new ArrayList<>();
                        for (Object o : lt) {
                            File f = (File) o;
                            String picPath = f.getAbsolutePath();
                            System.out.println(picPath);
                            if (Info.picSuffix.stream().anyMatch(e->e.equals(picPath.substring(picPath.indexOf('.'))))&&(!pics.contains(picPath))){
                                pics.add(picPath);
                                System.out.println("Add " + picPath);
                            }
//                            path = (jta.getText() + "\n" + f.getAbsolutePath());
                        }

//                        ArrayList<String> arrayList = new ArrayList<>();
//                        String[] arr = path.split("\n");
//                        Collections.addAll(arrayList, arr);
//                        arrayList.stream().filter(e->e.contains(".png"));
                        dtde.dropComplete(true);
                    } else {
                        dtde.rejectDrop();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        new DropTarget(jta, DnDConstants.ACTION_COPY_OR_MOVE, kgd);
        jta.setBounds(30, 30, 250, 250);
//        frame.add(jta);
        frame.setUndecorated(true);
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        frame();
    }
}