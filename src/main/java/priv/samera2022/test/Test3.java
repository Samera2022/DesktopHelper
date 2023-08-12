//package priv.samera2022;
//
//import IOS_SHOGUN_Component.IOS_SHOGUN_Toolkit;
//import IOS_SHOGUN_Component.SequenceCachedPool;
//
//import java.awt.*;
//import java.util.concurrent.LinkedBlockingDeque;
//import java.util.concurrent.TimeUnit;
//
//public class Test3 {
//    public static void main(String[] X) {
//        SequenceCachedPool Core=new SequenceCachedPool(2,3,100, TimeUnit.MILLISECONDS,
//                new LinkedBlockingDeque<>(10));
//        IOS_SHOGUN_Toolkit SHOGUN=new IOS_SHOGUN_Toolkit();
//        SHOGUN.Core(Core);
//        Test2 Test=new Test2();
//        Test.setToolkit(SHOGUN);
//        Test.DebugGraphics("调式窗口名称",new Rectangle(400,100,500,500),
//                SHOGUN.GraphicsReplace(),true);
//    }
//}
