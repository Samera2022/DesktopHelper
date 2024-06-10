package priv.samera2022.module;

public class UpdateInfo {
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
    private static final String version_0_0_4_1 =
            " - [Released] - [0.0.4.1] - 2024-02-08 18:44\n" +
            "## [Changed]\n" +
            " - Command注解已被简化，仅留下name()属性。\n" +
            " - 完善openai指令，将认证KEY等配置分离至config/gpt/default.json。\n" +
            "## [Added]\n" +
            " - 添加日志处理系统Logger。\n" +
            " - 添加配置Config，以config/config.cfg的格式输出。目前支持的内容有：是否将执行的指令再次输出至输出框(command_output)\n" +
            " - 添加exit指令。\n" +
            " - 添加clear指令。\n" +
            " - 添加config指令。目前支持的二级指令参数有:reload。用法见Usage。\n" +
            "## [Usage]\n" +
            "# config指令\n" +
            " - \"config reload\" 执行该指令后将会重载config.cfg。";
    private static final String version_0_0_4_2 =
            " - [Released] - [0.0.4.2] - 2024-02-20 13:22\n" +
            "## [Changed]\n" +
            " - 完善了mainFrame.ExceptionHandler()方法，但并未进行相关测试。\n" +
            " - web包更名为gpt包，原web包下HttpURLConnection.java更名为Connection.java\n" +
            "## [Added]\n" +
//            " - 增加config/ConfigHandler.java对于过时配置文件的逻辑，选择动态添加新的键值对并将值设为Updated。\n" + //没啥用，因为在read的时候还是要设置属性的
            " - 增加analyze指令。\n" +
            " - 增加downloadModpack指令。\n" +
            "## [Usage]\n" +
            "# downloadModpack指令\n" +
            " - \"downloadModpack key <zipPath> <targetPath>\" 执行该指令后将会从zipPath所指向的文件进行模组补全下载，保存至targetPath。同时将配置等文件夹解压至targetPath中。" +
            "请注意：key下载需要提供curseforge_api_key,即cf_api_key。配置在config.cfg中填入。\n"+
            " - \"downloadModpack browser <zipPath>\" 执行该指令后将会从zipPath所指向的文件进行浏览器模组补全下载，该操作不需要提供curseforge_api_key。目前没有设置配置等文件的解压，" +
            "因为涉及不同浏览器的保存地址，所以暂时不做相关处理。\n" +
            "# analyze指令\n" +
            " - \"analyze <filePath>\" 执行该指令后将会读取报错日志，并从已设置的报错内容特征进行检索。目前仅支持一小部分报错分析，更多分析请等待后续版本。\n" +
            "## [To-do]\n" +
            " - 考虑添加依赖缺失报错的模组下载处理逻辑。\n" +
            " - 或许应当考虑将关于mc的指令全部放在一级指令mc下面？\n" +
            "## [Warns]\n" +
            " - Logger的线程提示有问题，需要解决！";
    //TODO CommandHeads.java downloadModpacks()缺失文件输出以后再写
    //TODO config/ConfigHandler.java对于过时配置文件的逻辑并未加入，详见本String内被注释的行
    //TODO Config的压缩和解压指令尚未制作完全！举例：执行压缩指令将会直接得到压缩完的DesktopHelper文件夹，执行解压指令将会压缩的DesktopHelper文件夹解压到本机的DesktopHelper路径进行覆盖/增添。
    private static final String version_0_0_4_3 =
            " - [Released] - [0.0.4.3] - 2024-05-05 15:00\n" +
            "## [Changed]\n" +
            " - 修改了quiz的一些逻辑，使其更加符合使用需要。\n" +
            "## [Added]\n" +
            " - 增加AminoAcidQuiz指令。\n" +
            " - quiz start <quizName> 指令增加四级，五级指令。\n" +
            "## [Usage]\n" +
            "# quiz start <quizName>指令\n" +
            " - \"quiz start <quizName> <isBreak> <useSimilarity>\" 该指令新增<isBreak>和<useSimilarity>参数。" +
            "\n其中，isBreak参数可选stop或continue，分别对应回答错误即停止和回答错误仍继续。\nuseSimilarity旨在应对具有小范围开放性的填空，" +
            "使用一定的相似算法来判断所填入的答案与预设答案是否相关。\n" +
            "# AminoAcidQuiz指令\n" +
            " - \"AminoAcidQuiz plain <isBreak>\" 执行该指令后将会启动AminoAcidQuiz普通测试。\n"+
            " - \"AminoAcidQuiz structure <isBreak>\" 执行该指令后将会启动AminoAcidQuiz结构式测试。\n" +
            "## [To-do]\n" +
            " - 现在有支持显示图片的窗体了，之后或许可以考虑将其整合进Quiz中，实现循环滚大题的操作。\n" +
            " - Quiz之后应当考虑出题算法，使答对次数多的题目减少出现，答对次数少或出现次数少的题目增多出现。\n" +
            " - Quiz应当考虑多道题目共用一道大题干的情况，等到多道题目轮流答题完成再滚下一个大题或小题。目前思路如下：\n" +
            "显示一道大题题干，再显示一个输入窗体。键入第一小题的答案后，出现第二小题的输入窗体......直到所有小题答题结束，" +
            "再统一对答案进行评价打分。\n" +
            " - label指令加入待办列表。\n" +
            "## [Warns]\n" +
            " - \"quiz start <quizName> <isBreak> <useSimilarity>\" 指令中的<useSimilarity>默认改为false，因为相似算法仍然一定存在问题。\n" +
            " - \"AminoAcidQuiz structure <isBreak>\" 指令依赖于.../quizzes/resources/amino_acids/下的图片来完成。";
    public static final String version_0_0_5 =
            " - [Released] - [0.0.5] - 2024-05-26 21:50\n" +
            "## [Changed]\n" +
            " - 修正了Logger的相关逻辑，使之可以正常使用。\n" +
            "## [Added]\n" +
            " - 添加了原先的notification指令，并设法使其兼容新版代码。\n" +
            " - 添加并重写了原先的颜色系统，使之升级为字体系统，目前其支持的组件仍为notification系统。\n" +
            "## [Usage]\n" +
            "# notification指令\n" +
            " - \"notification add <content> <index>\" 其中，index为可填项，content为必填项。" +
            "content的格式要求为{<isFinished>,<startDate>,<endDate>}<content>或{<endDate>}<content> " +
            "执行该指令后将会将指定提示插入目标行数（如果填写了index）或直接添加到末尾一行（如果未填写index）。\n" +
            " - \"notification remove <index>\" 执行该指令后将会将指定目标行数的提示移除。\n" +
            " - \"notification finished <index>\" 执行该指令后将会把目标行数的提示切换至完成状态。\n" +
            " - \"notification unfinished <index>\" 执行该指令后将会把目标行数的提示切换至未完成状态。\n" +
            "# 字体系统\n" +
            " - 字体系统目前适用于notification系统。使用格式为[&<para>&<para>...]，比如：[&XA&XB]\n" +
            " - 目前支持绝大多数Font的参数，已支持的参数对应表如下所示：\n" +
            "[&FF(fontFamily)]调整字体  [&FS<fontSize>]调整字号  [&XA]粗体  [&XB]斜体  [&XC]下划线  [&XD]删除线  " +
            "[&XE]上标  [&XF]下标  [&CF]字体颜色  [&CB]背景颜色  [&SA]段落上方留空大小  [&SB]段落下方留空大小  " +
            "[&FL]首行缩进  [&LI]段落左缩进  [&RI]段落右缩进  [&LS]行间距\n" +
            " - 特别地，对于[&CF]和[&CB]两个与颜色相关的参数，使用时应当传入色彩信息[&CF(<R>,<G>,<B>)]，比如：[&CF(0,0,0)]。";
    //TODO 修正存在错误的AminoAcidQuiz get(3)
    public static String version_0_0_5_1 =
            " - [Released] - [0.0.5.1] - 2024-06-10 21:03\n" +
            "## [Added]\n" +
            " - 添加了version指令。\n" +
            " - 添加高考倒计时功能。\n" +
            "## [Usage]\n" +
            "# version指令\n" +
            " - \"version list\" 列出该程序的所有历史版号。\n" +
            " - \"version show\" 从历史版号中按序号读取更新日志信息。\n" +
            "## [Warns]\n" +
            " - 由于倒计时的加入，Panel的大小和位置大多都发生了改变。如要查询历史版本，则需回到v0.0.5版本。\n" +
            " - 剩余未开发的功能与bug将会被封存，直至下一次重新激活。\n";
    //TODO 剩余功能计划已写在UpdateInfo.java的TODO注释行（部分）和纸上（部分）保存！
    private static final String warns =
            "警示: " +
            "-后续指令注册时，应在Info中向COMMANDS加入该指令以进行模糊匹配！" +
            "假若该指令还有二级指令，则应效仿notification在二级switch前再进行一次模糊判定，且向Info中如NOTIFICATION_COMMANDS一样加入二级指令列表\n" +
            "-messages的isCommand判断方法是无法同时输出多个时间的！即所有的messages里面的Mixture的输出共用一个标记时间。" +
            "提示: " +
            "-在各个Listener中，你其实有很多变量都是可以调用的。很多时候如果这个方法用这个变量不行，可以试试用另外一个方法调用另外一个变量来解决。" +
            "比如说在模糊判断输出Couldn't find that command! Guess...的时候，用message会导致时间和内容吞并，但是用dsdFileContent附加时间就可以解决。";
    public static final String[] UpdateInfo = new String[]{version_0_0_1, version_0_0_2, version_0_0_3, version_0_0_3_1, version_pre_0_0_4,
            version_0_0_4,version_0_0_4_1,version_0_0_4_2,version_0_0_4_3,version_0_0_5,version_0_0_5_1};
    public static void main(String[] args) {
        System.out.println(version_0_0_5_1);
    }
}
