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
    private static final String version_0_0_3_1 =
            " - [Released] - [0.0.3.1] - 2023-08-26 09:58\n" +
            "## [Added]\n" +
            " - 增加模糊匹配机制！简化指令输入的时间，现在你只需要大概打出指令前几个字母就可以调用！\n" +
            " - 增加gadgets包（小工具包），可以向其中加入各种各样的小功能小工具！目前已加入ChemistryQuiz轨道表达式小测试！\n" +
            "## [Descriptions]\n" +
            " - 即将进行大规模底层逻辑重写，将会把该工程推送至v0.0.3.1分支保存!\n" +
            " - 工程将会由Listener+switch的结构转为总线注册总线监听结构！\n";
    private static final String version_pre_0_0_4 =
            " - [Released] - [pre0.0.4] - 2023-09-03 16:48\n" +
            "## [Changed]&&[Descriptions]\n" +
            " - 总线更改完成！目前总线仅搭载print指令，故本版本尚为测试版本。总线更改完成后，可以在CommandHeads中使用Command注解，" +
            "同时填入必要的name参数，选填delete与hasTextOutput参数即可完成指令的注册。故Info中已移除ArrayList<String> COMMANDS，" +
            "待Notification与其二级指令完成迁移后同时也会删除ArrayList<String> NOTIFICATION_COMMANDS。\n" +
            " - 总线信息传递大致如下：EnterKeyListener.java监听到输入框输入后调用EventBus.java的register方法，" +
            "register方法将命令综合处理完成后传入CommandHandler.java中的handleCommand方法，handleCommand方法先对其进行模糊匹配，" +
            "模糊匹配完成后将直接调用位于CommandHeads的该方法。\n" +
            " - 对0.0.3.1更新日期做出修正：模糊匹配机制于2023-08-26 09:58推出，而ChemistryQuiz则更早完成，但加入时间不详（晚于模糊匹配机制）。" +
            "故v0.0.3.1沿用模糊匹配机制的完成时间，即2023-08-26 09:58。\n" +
            "## [Warns]\n" +
            " - 潜在的危险：在EnterKeyListener.java中，为实现按下回车键后光标仍能恢复到第一行最前面的位置" +
            "（因为EnterKeyListener.java的keyPressed所能实现的功能皆在enter引发换行之前，所以只能在keyReleased进行调正）" +
            "采用了isCommand进行判断进而删除dsd前面的内容。而使用isCommand未经过严谨的考量，可能存在一些意想不到的问题。";
    private static final String version_0_0_4 =
            " - [Released] - [0.0.4] - 2024-01-27 19:20\n" +
            "## [Changed]&&[Description]\n" +
            "# Structure\n" +
            " - 注册指令变更。注册指令不再使用output(new Mixture[]{Mixture<>(String, Style)...}, Boolean);语句，" +
            "转而使用formatter(Boolean,Mixture<>(String, Style)...);语句。相较于原先语句该语句能够较好的结合数组和泛型，借助可变参数方法将" +
            "抽象的数组cast为泛型数组调用output进行输出，再利用SafeVarargs注解消除编译器对formatter方法的警告。简而言之，就是利用formatter方法" +
            "对output方法进行了一定程度的封装。\n" +
            " - 指令参数变更。指令参数由原先的Mixture<Boolean,ArrayList<String>>改为ArrayList<String>，毕竟原先这个类型确实长得比较离谱......" +
            "直接粗暴地把delete(Boolean)和指令本体结合起来，确实不太好。而且delete原本是Command注解里面的参数，再在方法的参数里面出现就太冗杂了。" +
            "或许我可以在output或者formatter的方法里面直接反射获取目标方法的注解，然后得到delete参数？这样就不需要在方法多个if分支里面重复写false或者true了，" +
            "其实本来也可以直接在方法体里写一个新的boolean delete声明，但是这样的话再调用delete的时候编译器老是会提示，就让人看得比较烦。\n" +
            " - 总线升级！已省去Command注解中的delete()和hasTextOutput()的使用，转为在CommandHeads相应指令的方法体中直接调用formatter()方法填入相应参数。\n" +
            " - 修正fuzzyMatcher方法，更正了该方法的逻辑。\n" +
            "# Commands\n" +
            " - 修正delete指令。\n" +
            "## [Added]&&[Description]\n" +
            " - 增加quiz指令。目前支持的二级指令参数有:create,start,delete,list。用法详见Usage。\n" +
            " - 增加frame指令。目前支持的二级指令参数有:broaden,close,dispose。用法详见Usage。\n" +
            " - 增加openai指令（需要联网）。目前仅支持单一对话询问，存在阻塞风险！\n" +
            "注：阻塞风险是指在执行该指令后，整个程序会陷入阻塞状态，等待请求结果。由于程序的逻辑特点，这个问题几乎无法避免。\n" +
            "## [Usage]\n" +
            "# quiz指令\n" +
            " - 概述：quiz指令是一种用于测试(examine)的指令，可以用于新建、追加、删除测试，或者列出本机上已有的全部测试内容。" +
            "为简化叙述，下文中<quizName>即为你所希望操作的测试的名称，\n" +
            " - \"quiz create <quizName>\" 执行该指令后将会在本机新建一个名为<quizName>的测试。" +
            "若该测试已存在，则会询问是否覆盖该测试。选Yes会复写原有测试，选No会在原有测试上追加，关闭窗体则会取消新建操作。\n" +
            " - \"quiz start <quizName>\" 执行该指令后将会尝试在本机查找名为<quizName>的测试并启动。\n" +
            " - \"quiz delete <quizName>\" 执行该指令后将会删除本机上名为<quizName>的测试，并且无法找回。\n" +
            " - \"quiz list\" 执行该指令后将会列出本机上可用的所有测试。\n" +
            "# frame指令\n" +
            " - 概述：frame指令是用于操作拓展窗体行为的指令。其中拓展窗体并不是主程序所对应的mainFrame，而是用于辅助显示的拓展大屏幕。" +
            "其中，这个大屏幕与主程序共享dsdFileContent(DefaultStyledDocument)，基本相当于与主程序共享负责文件读取与操作输出的中间组件。\n" +
            " - \"frame broaden\" 执行该指令后将会让拓展窗体变得可见。\n" +
            " - \"frame close\" 执行该指令后将会让拓展窗体变得不可见。但是通常情况下可以使用dispose来代替。\n" +
            " - \"frame dispose\" 执行该指令后将会关闭拓展窗体，并释放一部分资源。\n" +
            "# openai指令\n" +
            " - \"openai <question>\" 执行该指令后将会联网请求openai模型<question>的内容，并等待回复。目前仅支持单一对话询问。";
    private static final String warns =
            "警示: " +
            "-后续指令注册时，应在Info中向COMMANDS加入该指令以进行模糊匹配！" +
            "假若该指令还有二级指令，则应效仿notification在二级switch前再进行一次模糊判定，且向Info中如NOTIFICATION_COMMANDS一样加入二级指令列表\n" +
            "-messages的isCommand判断方法是无法同时输出多个时间的！即所有的messages里面的Mixture的输出共用一个标记时间。" +
            "提示: " +
            "-在各个Listener中，你其实有很多变量都是可以调用的。很多时候如果这个方法用这个变量不行，可以试试用另外一个方法调用另外一个变量来解决。" +
            "比如说在模糊判断输出Couldn't find that command! Guess...的时候，用message会导致时间和内容吞并，但是用dsdFileContent附加时间就可以解决。";
    public static void main(String[] args) {
        System.out.println(version_0_0_4);
    }
}
