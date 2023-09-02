package priv.samera2022.module.gadgets.chemistry_quiz;

import java.util.ArrayList;
import java.util.Arrays;

public enum Element {
    K("K","钾","[Ar]4s1"),
    Ca("Ca","钙","[Ar]4s2"),
    Sc("Sc","钪","[Ar]3d1 4s2"),
    Ti("Ti","钛","[Ar]3d2 4s2"),
    V("V","钒","[Ar]3d3 4s2"),
    Cr("Cr","铬","[Ar]3d5 4s1"),
    Mn("Mn","锰","[Ar]3d5 4s2"),
    Fe("Fe","铁","[Ar]3d6 4s2"),
    Co("Co","钴","[Ar]3d7 4s2"),
    Ni("Ni","镍","[Ar]3d8 4s2"),
    Cu("Cu","铜","[Ar]3d10 4s1"),
    Zn("Zn","锌","[Ar]3d10 4s2"),
    Ga("Ga","镓","[Ar]4s2 4p1"),
    Ge("Ge","锗","[Ar]4s2 4p2"),
    As("As","砷","[Ar]4s2 4p3"),
    Se("Se","硒","[Ar]4s2 4p4"),
    Br("Br","溴","[Ar]4s2 4p5"),
    Kr("Kr","氪","[Ar]4s2 4d6");
    public static final ArrayList<Element> elements = new ArrayList<>();

    static {
        elements.addAll(Arrays.asList(K,Ca,Sc,Ti,V,Cr,Mn,Fe,Co,Ni,Cu,Zn,Ga,Ge,As,Se,Br,Kr));
    }

    Element(String elName,String chName, String CEE){
        this.elName = elName;
        this.chName = chName;
        this.CEE = CEE;
    };
    private final String elName;
    private final String chName;
    private final String CEE;
    public String getElName() {
        return elName;
    }
    public String getChName() {
        return chName;
    }
    public String getCEE() {
        return CEE;
    }
}
