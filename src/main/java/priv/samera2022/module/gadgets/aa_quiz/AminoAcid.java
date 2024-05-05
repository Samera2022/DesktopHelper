package priv.samera2022.module.gadgets.aa_quiz;

import java.util.ArrayList;
import java.util.Arrays;

public enum AminoAcid {
    A("A","Ala","丙氨酸","Alanine",6.00),
    R("R","Arg","精氨酸","Arginine",10.76),
    N("N","Asn","天冬酰胺","Asparagine",5.41),
    D("D","Asp","天冬氨酸","Aspartic Acid",1.88),
    C("C","Cys","半胱氨酸","Cysteine",5.07),
    E("E","Glu","谷氨酸","Glutamic Acid",3.22),
    Q("Q","Gln","谷氨酰胺","Glutamine",5.65),
    G("G","Gly","甘氨酸","Glycine",5.97),
    H("H","His","组氨酸","Histidine",7.60),
    I("I","Ile","异亮氨酸","Isoleucine",6.04),
    L("L","Leu","亮氨酸","Leucine",5.98),
    K("K","Lys","赖氨酸","Lysine",9.74),
    M("M","Met","甲硫氨酸","Methionine",5.74),
    F("F","Phe","苯丙氨酸","Phenylalanine",5.48),
    P("P","Pro","脯氨酸","Proline",6.30),
    S("S","Ser","丝氨酸","Serine",5.68),
    T("T","Thr","苏氨酸","Threonine",5.60),
    W("W","Trp","色氨酸","Tryptophan",5.89),
    Y("Y","Tyr","酪氨酸","Tyrosine",5.66),
    V("V","Val","缬氨酸","Valine",5.96);
    public static final ArrayList<AminoAcid> amino_acids = new ArrayList<>();

    static {
        amino_acids.addAll(Arrays.asList(A,R,N,D,C,E,Q,G,H,I,L,K,M,F,P,S,T,W,Y,V));
    }

    AminoAcid(String singleName, String tribleName, String chName, String enName, double pI){
        this.singleName = singleName;
        this.tribleName = tribleName;
        this.chName = chName;
        this.enName = enName;
        this.pI = pI;
    };
    private final String singleName;
    private final String tribleName;
    private final String chName;
    private final String enName;
    private final double pI;
    public String getSingleName() {
        return singleName;
    }
    public String getTribleName() {
        return tribleName;
    }
    public String getChName() {
        return chName;
    }
    public String getEnName() {
        return enName;
    }
    public double getpI() {
        return pI;
    }
}
