package priv.samera2022.module.gadgets.quiz;

import priv.samera2022.module.Mixture;
import priv.samera2022.module.file.FileHandler;
import priv.samera2022.module.mainFrame;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//注：所有方法（除了与窗体直接交互的方法）都使用File作输入参数，
//因为用File较之用String quizName而言在不同方法中就不用重复声明了
public class Quiz {
    private static final String NAME_1 = "Tips: ";
    private static final String NAME_2 = "Answer: ";

    public static void main(String[] args) {
//        input("test");
        examine("test",false,true);
//        examine("a");
//        File f = new File(FileHandler.FOLDER_PATH+FileHandler.QUIZ_PATH+"test"+".txt");
//        input("test");
//        write(getQuestions(), f);
//        read(f).forEach(content -> System.out.println(NAME_1 + content.getTips() + "\n" + NAME_2 + content.getAnswer()));
    }

//examine模块

    public static String examine(String quizName, boolean isBreak, boolean useSimilarity) {
        File file = new File(FileHandler.FOLDER_PATH + FileHandler.QUIZ_PATH + quizName + ".qiz");
        if (!file.exists()) {
            return "该测试不存在！";
        } else {
            ArrayList<Question> questions = handleDuplicates(file);
            ArrayList<Mixture<Question,Integer>> mixtures = new ArrayList<>(); //mixtures和questions的长度不等价！！！mixtures的元素会在后面的代码中被逐渐删除，因此长度会在后面被更新，而questions这个ArrayList则是恒定的，不会在后面被改变。
            questions.forEach(e->mixtures.add(new Mixture<>(e,0)));
            int time = 1;
            for (; ; ) {
                if (mixtures.size()==0){
                    String content;
                    if (time!=0) content = "所有问题已被移除！";
                    else content = "该测试为空！";
                    mainFrame.logger.info(content);
                    JOptionPane.showConfirmDialog(null,content,"提示",JOptionPane.YES_NO_OPTION);
                    return content;
                }
                int random = randomInt(1, mixtures.size());
                Mixture<Question,Integer> mixture = mixtures.get(random - 1);
                if (mixture.getValue()>=5){
                    mixtures.remove(random - 1);
                    mainFrame.logger.info("该问题分值已达到设计最大值5！已从问题列表中移除该问题。");
                    continue;
                }
                mainFrame.logger.info("该问题目前分值为"+mixture.getValue());
                Question question = mixture.getKey();
                String answer = JOptionPane.showInputDialog(null, question.getTips(), "第" + time + "次", JOptionPane.QUESTION_MESSAGE);        //输入对话框
                if (answer == null) {
                    if (time != 1) {
                        JOptionPane.showMessageDialog(null, "答案是" + question.getAnswer() + "！你这次"+(isBreak?"答对了":"作答了") + time + "次。", "回答结束", JOptionPane.WARNING_MESSAGE);    //消息对话框
                        mixtures.set(random-1, new Mixture<>(question,mixture.getValue() - 1));
                    }
                    if (isBreak) break;
                } else {
                    boolean plainFalse = !answer.equals(question.getAnswer());
                    boolean similarFalse = isSimilar(answer,question.getAnswer());

                    //similarity方法有问题
                    useSimilarity = false;

                    boolean outcome;
                    if (useSimilarity) outcome = similarFalse;
                    else outcome = plainFalse;
                    if (outcome) {
                        if (time != 1) {
                            JOptionPane.showMessageDialog(null, "回答错了！答案应该是" + question.getAnswer() + "！你这次答对了" + time + "次，下次继续努力吧！", "回答错误", JOptionPane.WARNING_MESSAGE);    //消息对话框
                        } else
                            JOptionPane.showMessageDialog(null, "回答错了！答案应该是" + question.getAnswer() + "！你这次第一次就答错了，下次继续努力吧！", "回答错误", JOptionPane.WARNING_MESSAGE);    //消息对话框
                        mixtures.set(random-1, new Mixture<>(question,mixture.getValue() - 1));
                        if (isBreak) break;
                    } else
                        mixtures.set(random-1, new Mixture<>(question,mixture.getValue() + 1));
                }
                if ((!isBreak)&&answer.equals("exit")) break;
                time++;
            }
            return String.valueOf(time);
        }
    }

    private static boolean random(float probability) {
        if (probability < 0 || probability > 1) {
            throw new IllegalArgumentException("Probability must be between 0 and 1");
        }
        Random random = new Random();
        return random.nextDouble() < probability;
    }

    //考虑是否存在重复的问题？
    private static ArrayList<Question> read(File quiz) {
        ArrayList<Question> questions = new ArrayList<>();
        String content = FileHandler.read(quiz.getPath());
        ArrayList<String> list = new ArrayList<>(Arrays.asList(content.split("#\\$")));
        list.remove(0);
        list.forEach(element -> {
            element = element.replace("\n", "");
            String tips = element.substring(element.indexOf(NAME_1) + NAME_1.length(), element.indexOf(NAME_2));
            String answer = element.substring(element.indexOf(NAME_2) + NAME_2.length());
            questions.add(new Question(tips, answer));
        });
        return questions;
    }

    //由该方法输出最终ArrayList<Question>
    private static ArrayList<Question> handleDuplicates(File quiz){
        ArrayList<Question> questions = new ArrayList<>(read(quiz));
        ArrayList<String> NAME_1s = new ArrayList<>();
        questions.forEach(content -> NAME_1s.add(content.getTips()));
        Map<String,List<Integer>> map = findDuplicatesWithIndexes(NAME_1s);
        for (String key : map.keySet()){
            List<Integer> loc = map.get(key);
            for (int i : loc){
                Question question = new Question(questions.get(i).getTips()+(i+1),questions.get(i).getAnswer());
                questions.remove(i);
                    if (i <= questions.size() - 1) questions.add(i, question);
                    else questions.add(question);
            }
            //注：在回答窗口中出现的问题后缀，是指在该测试中该问题所出现的序列。
            //例：在test测试中，问题后面出现后缀2，那么就是指这个问题在test.txt中排第二个。
        }
        return questions;
    }

    public static  <T> Map<T, List<Integer>> findDuplicatesWithIndexes(List<T> elems) {
        return IntStream.range(0, elems.size())
                .boxed()
                .collect(Collectors.groupingBy(elems::get))
                .entrySet().stream()
                .filter(e -> e.getValue().size() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    //生成[min,max]内的随机整数
    private static int randomInt(int min, int max) {
        return new Random().nextInt(max) % (max - min + 1) + min;
    }

//input代码模块

    public static void input(String quizName) {
        File file = new File(FileHandler.FOLDER_PATH + FileHandler.QUIZ_PATH + quizName + ".qiz");
        preAction(file);
        write(getQuestions(), file);
    }

    //其实preAction是可以合并到write里面的，但是出于操作习惯而言，往往一般都是先问存不存在，再决定是否输入了的。所以preAction应该提到最前面，比input还要更前面一些。
    @SuppressWarnings("all")
    private static void preAction(File quiz) {
        try {
            if (quiz.exists()) {
                if (JOptionPane.showConfirmDialog(null, "文件已存在！是否覆盖该文件？若选择否，则在原有文件上附加内容。", "异常警示", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    quiz.delete();
                    quiz.createNewFile();
                }
            } else quiz.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            mainFrame.ExceptionHandler(e);
        }
    }

    private static ArrayList<Question> getQuestions() {
        ArrayList<Question> questions = new ArrayList<>();
        int i = 1;
        for (; ; ) {
            String tips = JOptionPane.showInputDialog(null, "请输入提示", "第" + i + "次输入", JOptionPane.QUESTION_MESSAGE);
//            if (tips == null) break;
            if (tips.equals("exit") || tips.length() == 0) break;
            String answer = JOptionPane.showInputDialog(null, "请输入答案", "第" + i + "次输入", JOptionPane.QUESTION_MESSAGE);
//            if (answer == null) break;
            if (answer.equals("exit") || answer.length() == 0) break;
            questions.add(new Question(tips, answer));
            i++;
        }
        return questions;
    }

    private static void write(ArrayList<Question> questions, File quiz) {
        questions.forEach(content ->
                        FileHandler.write(quiz.getPath(), "#$\n"
                                + NAME_1 + content.getTips() + "\n" + NAME_2 + content.getAnswer() + "\n", true)
                //最后会多出来一个换行\n
        );
    }

    private static double calculateSimilarity(String str1, String str2) {
        if (str1 == null || str2 == null) {
            throw new IllegalArgumentException("Strings must not be null");
        }

        // 转换字符串为小写，以忽略大小写差异
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        // 确保较短的字符串是str1，以提高效率
        if (str1.length() > str2.length()) {
            String temp = str1;
            str1 = str2;
            str2 = temp;
        }

        int matches = 0;
        for (int i = 0; i < str1.length(); i++) {
            if (str2.contains(String.valueOf(str1.charAt(i)))) {
                matches++;
                // 从str2中移除已匹配的字符，以避免重复计数
                str2 = str2.replaceFirst(String.valueOf(str1.charAt(i)), "");
            }
        }

        // 计算相似度：相同字符的数量除以str1的长度
        double similarity = (double) matches / str1.length();
        return similarity * 100; // 转换为百分比
    }

    //方法有问题！！
    public static boolean isSimilar(String str1, String str2) {
        double similarity = calculateSimilarity(str1, str2);
        return similarity > 80;
    }

}

class Question {

    private final String tips;
    private final String answer;

    public Question(String tips, String answer) {
        this.tips = tips;
        this.answer = answer;
    }

    public String getTips() {
        return tips;
    }

    public String getAnswer() {
        return answer;
    }
}
