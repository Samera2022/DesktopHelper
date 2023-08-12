//package priv.samera2022;
//
//import IOS_SHOGUN_Component.*;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.awt.geom.RoundRectangle2D;
//import java.awt.image.BufferedImage;
//
////IOS_SHOGUN
//public class Test2 {
//    IOS_SHOGUN_Toolkit SHOGUN;
//
//    /**
//     * <h1>DebugGraphics(调试窗口)</h1>
//     * <h4 style="font-family:'微软雅黑 Light';border-color:rgba(115,115,115,0.8);border-style: solid;border-radius:10px;">3
//     * 接口使用方式 DebugGraphics();</h4>
//     *
//     * @return JPanel
//     */
//    @NotNull
//    public <V> Vector_Resource4 DebugGraphics(V FrameName, Rectangle StorageSize, BufferedImage Icon, Boolean Temple) {
//        JFrame HomeOfWindowsJFrameViewVisual = new JFrame();
//        HomeOfWindowsJFrameViewVisual.setLayout(null);
//        HomeOfWindowsJFrameViewVisual.setTitle((String) FrameName);
//        HomeOfWindowsJFrameViewVisual.setIconImage(Icon);
//        HomeOfWindowsJFrameViewVisual.getDefaultCloseOperation();
//        HomeOfWindowsJFrameViewVisual.setBounds(StorageSize.x, StorageSize.y, StorageSize.width, StorageSize.height + 50);
//        HomeOfWindowsJFrameViewVisual.setUndecorated(true);
//        HomeOfWindowsJFrameViewVisual.setBackground(new Color(0, 0, 0, 0));
//        HomeOfWindowsJFrameViewVisual.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        Integer[] StorageIOS_SHOGUN_Debug = new Integer[]{StorageSize.width};
//        JPanel LoadCaptureDataOfResourceMirrorDisplayed = new JPanel() {
//            @Override
//            public void paint(Graphics OBTAIN_SWING_GUI) {
//                super.paint(OBTAIN_SWING_GUI);
//                Graphics2D TurnLabelComponentTo2DGraphics = (Graphics2D) OBTAIN_SWING_GUI;
//                Color PrintfGraphics = new Color(31, 31, 31);
//                TurnLabelComponentTo2DGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//                TurnLabelComponentTo2DGraphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
//                TurnLabelComponentTo2DGraphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
//                OBTAIN_SWING_GUI.setColor(PrintfGraphics);
//                OBTAIN_SWING_GUI.fillRoundRect(0, 0, StorageSize.width, StorageSize.height, 20, 20);
//                TurnLabelComponentTo2DGraphics.setColor(new Color(250, 250, 250));
//                Paint Storage2DGraphicsPaint = ((Graphics2D) OBTAIN_SWING_GUI).getPaint();
//                if (Temple) {
//                    GradientPaint CREATE_COLOR_GRAPHICS = new GradientPaint(0, 35, new Color(198, 79, 190), StorageSize.width, 0, new Color(232, 164, 75));
//                    ((Graphics2D) OBTAIN_SWING_GUI).setPaint(CREATE_COLOR_GRAPHICS);
//                }
//                TurnLabelComponentTo2DGraphics.fillRoundRect(0, 0, StorageIOS_SHOGUN_Debug[0], 35, 10, 10);
//                ((Graphics2D) OBTAIN_SWING_GUI).setPaint(Storage2DGraphicsPaint);
//                TurnLabelComponentTo2DGraphics.setFont(new Font("雅黑", Font.PLAIN, 12));
//                FontMetrics OBTAIN_User_Font = Toolkit.getDefaultToolkit().getFontMetrics(new Font("雅黑", Font.PLAIN, 12));
//                int Obtain_Toolkit_Class_Width = SwingUtilities.computeStringWidth(OBTAIN_User_Font, (String) FrameName);
//                TurnLabelComponentTo2DGraphics.setColor(new Color(72, 70, 70));
//                TurnLabelComponentTo2DGraphics.drawString((String) FrameName, (StorageSize.width) / 2 - Obtain_Toolkit_Class_Width / 2, 38 - OBTAIN_User_Font.getHeight());
//                TurnLabelComponentTo2DGraphics.drawImage(SHOGUN.CircleImage(SHOGUN.IMAGE_CustomScaledIOS(Icon, 25, 25), new Point(25, 25)), 5, 35 / 2 - 25 / 2, 25, 25, null);
//                super.paintChildren(OBTAIN_SWING_GUI);
//            }
//
//            @Override
//            public void setBounds(int x, int y, int width, int height) {
//                super.setBounds(0, 0, width, height + 50);
//            }
//
//            @Override
//            public void setBounds(Rectangle r) {
//                this.setBounds(0, 0, r.width, r.height + 50);
//            }
//        };
//        LoadCaptureDataOfResourceMirrorDisplayed.setLayout(null);
//        LoadCaptureDataOfResourceMirrorDisplayed.setBackground(new Color(0, 0, 0, 0));
//        LoadCaptureDataOfResourceMirrorDisplayed.setBounds(StorageSize);
//        LoadCaptureDataOfResourceMirrorDisplayed.setVisible(true);
//        HomeOfWindowsJFrameViewVisual.add(LoadCaptureDataOfResourceMirrorDisplayed);
//        Integer[] Toolkit_SHOGUN = new Integer[]{50};
//        Integer[] Toolkit_SHOGUN_AGAIN = new Integer[]{0, 0, 0};
//        JButton StorageLoadDataResource = new JButton() {
//            @Override
//            public void paint(Graphics OBTAIN_SWING_GUI) {
//                super.paint(OBTAIN_SWING_GUI);
//                Graphics2D TurnLabelComponentTo2DGraphics = (Graphics2D) OBTAIN_SWING_GUI;
//                TurnLabelComponentTo2DGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//                TurnLabelComponentTo2DGraphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
//                TurnLabelComponentTo2DGraphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
//                TurnLabelComponentTo2DGraphics.setColor(new Color(Toolkit_SHOGUN_AGAIN[0], Toolkit_SHOGUN_AGAIN[1], Toolkit_SHOGUN_AGAIN[2]));
//                OBTAIN_SWING_GUI.fillOval(0, 0, getSize().width - 1, getSize().height - 1);
//                TurnLabelComponentTo2DGraphics.setColor(new Color(Toolkit_SHOGUN[0], 14, 14));
//                OBTAIN_SWING_GUI.fillOval(getSize().width / 4, getSize().height / 4, getSize().width / 2, getSize().height / 2);
//                super.paintChildren(OBTAIN_SWING_GUI);
//            }
//
//            @Override
//            public void setBounds(int x, int y, int width, int height) {
//                super.setBounds(StorageSize.width - 35, 35 / 2 - 25 / 2, 25, 25);
//            }
//
//            @Override
//            public void setOpaque(boolean isOpaque) {
//                super.setOpaque(false);
//            }
//
//            @Override
//            public void setContentAreaFilled(boolean b) {
//                super.setContentAreaFilled(false);
//            }
//
//            @Override
//            public void setBorderPainted(boolean b) {
//                super.setBorderPainted(false);
//            }
//        };
//        IOS_SHOGUN_Toolkit IOS_SHOGUN_GRAPHICS_2D = SHOGUN;
//        Boolean[] ControlProgressInfo = new Boolean[]{false, false, false};
//        StorageLoadDataResource.setBorderPainted(false);
//        StorageLoadDataResource.setContentAreaFilled(false);
//        StorageLoadDataResource.setOpaque(false);
//        StorageLoadDataResource.setBounds(0, 0, 0, 0);
//        LoadCaptureDataOfResourceMirrorDisplayed.add(StorageLoadDataResource);
//        JPanel LoadCaptureDataOfResourceMirrorDisplayed1 = new JPanel() {
//            @Override
//            public void paint(Graphics OBTAIN_SWING_GUI) {
//                super.paint(OBTAIN_SWING_GUI);
//                Graphics2D TurnLabelComponentTo2DGraphics = (Graphics2D) OBTAIN_SWING_GUI;
//                Color PrintfGraphics = new Color(31, 31, 31);
//                TurnLabelComponentTo2DGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//                TurnLabelComponentTo2DGraphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
//                TurnLabelComponentTo2DGraphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
//                OBTAIN_SWING_GUI.setColor(PrintfGraphics);
//                OBTAIN_SWING_GUI.fillRoundRect(0, 0, getSize().width, getSize().height, 20, 20);
//                TurnLabelComponentTo2DGraphics.setComposite(AlphaComposite.Src);
//                TurnLabelComponentTo2DGraphics.fill(new RoundRectangle2D.Float(0, 0, getSize().width, getSize().height, 20, 20));
//                TurnLabelComponentTo2DGraphics.setComposite(AlphaComposite.SrcAtop);
//                TurnLabelComponentTo2DGraphics.setComposite(AlphaComposite.SrcAtop);
//                LoadCaptureDataOfResourceMirrorDisplayed.updateUI();
//                super.paintChildren(OBTAIN_SWING_GUI);
//            }
//
//            @Override
//            public void setBounds(int x, int y, int width, int height) {
//                super.setBounds(3, 36, width, height);
//            }
//
//            @Override
//            public void setBounds(Rectangle r) {
//                this.setBounds(0, 36, r.width - 6, r.height - 40);
//            }
//        };
//        LoadCaptureDataOfResourceMirrorDisplayed1.setLayout(null);
//        LoadCaptureDataOfResourceMirrorDisplayed1.setBackground(new Color(0, 0, 0, 0));
//        LoadCaptureDataOfResourceMirrorDisplayed1.setBounds(StorageSize);
//        LoadCaptureDataOfResourceMirrorDisplayed1.setVisible(true);
//        LoadCaptureDataOfResourceMirrorDisplayed.add(LoadCaptureDataOfResourceMirrorDisplayed1);
//        HomeOfWindowsJFrameViewVisual.setVisible(true);
//        StorageLoadDataResource.addActionListener(e -> {
//            if (!ControlProgressInfo[1]) {
//                ControlProgressInfo[1] = true;
//                IOS_SHOGUN_GRAPHICS_2D.Animation_CVShader(255, FPS_DATA -> FPS_DATA.CV_SourceAnimation(Toolkit_SHOGUN),
//                        AnimationExecutor -> StorageLoadDataResource.updateUI(),
//                        new CachedTask<>() {
//                            @Override
//                            public void run() {
//                                IOS_SHOGUN_GRAPHICS_2D.Animation_CVShader(0, FPS_DATA2 -> FPS_DATA2.CV_SourceAnimation(StorageIOS_SHOGUN_Debug),
//                                        AnimationExecutor2 -> LoadCaptureDataOfResourceMirrorDisplayed.updateUI(), new CachedTask<>() {
//                                            @Override
//                                            public void run() {
//                                                System.exit(0);
//                                            }
//                                        });
//                            }
//                        });
//            }
//        });
//        StorageLoadDataResource.addMouseListener(new MouseAdapter() {
//            public @Override
//            void mouseEntered(MouseEvent EnteredTime) {
//                if (!ControlProgressInfo[0]) {
//                    ControlProgressInfo[0] = true;
//                    SHOGUN.Animation_CVShader(255, FPS_3 -> {
//                        FPS_3.CV_SourceAnimation(Toolkit_SHOGUN_AGAIN);
//                        FPS_3.AnimationLoop(true);
//                    }, AnimationExecutor3 -> StorageLoadDataResource.updateUI(), new CachedTask<>() {
//                        @Override
//                        public void run() {
//                            ControlProgressInfo[0] = false;
//                        }
//                    });
//                }
//            }
//        });
//        LoadCaptureDataOfResourceMirrorDisplayed.updateUI();
//        LoadCaptureDataOfResourceMirrorDisplayed1.updateUI();
//        this.DisplayVector4Graphics(HomeOfWindowsJFrameViewVisual, new Size(StorageSize.width, 35));
//        return new Vector_Resource4(LoadCaptureDataOfResourceMirrorDisplayed1);
//    }
//
//    @NotNull
//    private void DisplayVector4Graphics(JFrame StorageLoadCapture, Size StorageWindowsSize) {
//        Point[] StorageUserPressedOfPosition = new Point[]{new Point(0, 0)};
//        Boolean[] JudgeUserPressedWhiteReleased = new Boolean[]{false};
//        StorageLoadCapture.addMouseListener(new MouseAdapter() {
//            public void mousePressed(MouseEvent StorageUserPressedOfPositionLocation) {
//                if ((StorageUserPressedOfPositionLocation.getX() >= 0 && StorageUserPressedOfPositionLocation.getX() <= StorageWindowsSize.width) && (StorageUserPressedOfPositionLocation.getY() >= 0 && StorageUserPressedOfPositionLocation.getY() <= StorageWindowsSize.height)) {
//                    StorageUserPressedOfPosition[0].y = StorageUserPressedOfPositionLocation.getY();
//                    StorageUserPressedOfPosition[0].x = StorageUserPressedOfPositionLocation.getX();
//                    JudgeUserPressedWhiteReleased[0] = true;
//                }
//            }
//
//            public void mouseReleased(MouseEvent StorageUserPressedOfPositionLocation) {
//                JudgeUserPressedWhiteReleased[0] = false;
//            }
//        });
//        StorageLoadCapture.addMouseMotionListener(new MouseAdapter() {
//            public void mouseDragged(MouseEvent MouseDraggedTimeCreateTriggerGoods) {
//                if (JudgeUserPressedWhiteReleased[0]) {
//                    java.awt.Point WindowsOfPointLocationPositionStorage = StorageLoadCapture.getLocation();
//                    StorageLoadCapture.setLocation(WindowsOfPointLocationPositionStorage.x + MouseDraggedTimeCreateTriggerGoods.getX() - StorageUserPressedOfPosition[0].x, WindowsOfPointLocationPositionStorage.y + MouseDraggedTimeCreateTriggerGoods.getY() - StorageUserPressedOfPosition[0].y);
//                }
//            }
//        });
//    }
//
//    public void setToolkit(IOS_SHOGUN_Toolkit SHOGUN) {
//        this.SHOGUN = SHOGUN;
//    }
//
//}