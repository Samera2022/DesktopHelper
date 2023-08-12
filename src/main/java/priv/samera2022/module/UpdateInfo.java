package priv.samera2022.module;

public class UpdateInfo {
    public static String[] UpdateInfo;
    private static final String version_0_0_1 =
            " - [Unreleased] - [0.0.1] - [Unknown]\n" +
            "## [Added]\n" +
            " - 加入文件内容显示系统！可拖拽显示以txt，yml结尾的文件内容。其中txt是报错日志且包含报错日志重点信息，那么该行将会被高亮显示。\n" +
            "## [Warns]\n" +
            " - 该识别系统存在缺陷，文件名如果过长则将不会进行识别。\n" +
            "## [Todos]\n" +
            " - 增加MD语法的显示，目前由于MD语法比较复杂没有完成。";
    private static final String version_0_0_2 =
            " - [Unreleased] - [0.0.2] - 2023-08-07 15:58\n" +
            "## [Added]\n" +
            " - 加入输入栏！目前支持拖拽文件进输入栏以快捷键入文件的绝对路径，或者手动敲入路径。具体功能未实现，仅实现其窗体\n" +
            "## [Changed]\n" +
            " - 重写拖拽逻辑，将声明DropTargetAdapter匿名内部类更改为DropTarget继承DropTargetAdapter父类，" +
            "进而进行switch code从而选定栏目类型，以区分输入栏与文件内容显示系统。\n" +
            " - 修改了大量的bug，终于支持让输入栏与文件内容显示系统正常工作。\n" +
            "## [Warns]\n" +
            " - 在进行开发时，如果要对控件内的文字进行修改，应当做的操作是修改该控件内的DefaultStyledDocument，" +
            "即dsd.xxxxxx。而不应该是修改Document，即xxx.setDocument(xx)，修改Document的行为是错误的。";
    private static final String version_0_0_3 =
            " - [Released] - [0.0.3] - 2023-08-12 12:12\n" +
            "## [Added]\n" +
            " - 增加Notification栏目与notification系列指令！详情见Descriptions。\n" +
            " - 增加彩色系统！现在你可以用花花绿绿的颜色来妆点你的文字了！详情见Descriptions。\n" +
            "## [Descriptions]\n" +
            " - [Notification] Notification是一个新的栏目，用于显示你的Notifications。" +
            "Notification应当包含[(boolean)isFinished,(String)startDate]和(String)endDate。前二者为可选，后者为必选。" +
            "详细指令使用参见notification help指令。\n" +
            " - [彩色系统] 应当注意的是，目前颜色仍是固定的，有 黑 蓝 黄 深红 灰 绿可选。" +
            "后续将考虑增加自定义颜色系统，可选颜色在FontStyle类中。彩色系统的支持范围为Notification栏目。\n";
}
