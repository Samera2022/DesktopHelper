package priv.samera2022.module.gadgets.english_quiz;

import java.util.ArrayList;
import java.util.Arrays;

public enum Source {
    scream("scream","高声喊"),platform("platform","月台"),
    rating("rating","收视率"),automatic("automatic","无意识的"),
    channel("channel","渠道"),memorable("memorable","难忘的"),
    session("session","一段时间"),league("league","联赛"),
    backfire("backfire","事与愿违"),literally("literally","确实"),
    resistance("resistance","抵制"),arise("arise","出现"),
    ultimately("ultimately","最终"),grateful("grateful","感激"),
    examine("examine","检查"),warning("warning","先兆"),
    sustain("sustain","维持"),contrast("contrast","形成对比"),
    manner("manner","方式"),manners("manners","礼仪"),
    minority("minority","少数民族"),possession("possession","物品"),
    remark("remark","说"),occupy("occupy","占领"),
    motive("motive","动机"),assistance("assistance","帮助"),
    gravity("gravity","严重性"),purse("purse","财源"),
    coverage("coverage","报道"),expense("expense","费用"),
    exceptional("exceptional","杰出"),
    racial("racial","r?"),arise2("arise","a? from"),
    grateful2("grateful","g?"),absorb("absorb","a? a lot of information"),
    recognition("recognition","beyond r?"),muscular("muscular","m?"),
    episode("episode","e?"),unfold("unfold","u?"),
    statue("statue","s?"),bunch("bunch","b?"),
    delicacy("delicacy","d?"),intently("intently","i?"),
    accommodation("accommodation","a?"),load("load","l?"),
    surrounding("surrounding","the striking s? scenery"),
    applaud("applaud","a? sb."),motive2("motive","the m? of"),
    shelter("shelter","fund his dog s?"),exceptional2("exceptional","e? talent"),
    shortage("shortage","global s? of work force"),spring("spring","s? up"),
    withdraw("withdraw","w? into oneself"),racial2("racial","r? prejudice"),
    convinced("convinced","be c? that"),
    ambition("ambition","have no a? other than"),
    allow("allow","a? for"),contrary("contrary","c? to");
    public static final ArrayList<Source> sources = new ArrayList<>();
    
    static {
        sources.addAll(Arrays.asList(scream,platform,rating,automatic,channel,memorable,
                session,league,backfire,literally,resistance,arise,ultimately,grateful,
                examine,warning,sustain,contrast,manner,manners,minority,possession,remark,
                occupy,motive,assistance,gravity,purse,coverage,expense,exceptional,
                racial,arise2,grateful2,absorb,recognition,muscular,episode,unfold,
                statue,bunch,delicacy,intently,accommodation,load,surrounding,applaud,
                motive2,shelter,exceptional2,shortage,spring,withdraw,racial2,convinced,
                ambition,allow,contrary));
    }

    Source(String answer, String tips){
        this.answer = answer;
        this.tips = tips;
    }
    private final String answer;
    private final String tips;

    public String getTips() {
        return tips;
    }

    public String getAnswer() {
        return answer;
    }

}
