package priv.samera2022.module.font;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.util.Objects;
import java.util.stream.Collectors;

@SuppressWarnings("all")
public class GeneralFont {

    private String fontFamily;
    private int fontSize;
    private boolean isBold;
    private boolean isItalic;
    private boolean isUnderline;
    private boolean isStrikeThrough;
    private boolean isSuperscript;
    private boolean isSubscript;
    private Color foreground;
    private Color background;
    private Component componentAttribute;
    private Icon iconAttribute;
    private Object modelAttribute;
    private int spaceAbove;
    private int spaceBelow;
    private int firstLineIndent;
    private int leftIndent;
    private int rightIndent;
    private float lineSpacing;
    private TabSet tabSet;

    // FontFamily 字体效果
    public String getFontFamily() { return fontFamily; }
    public void setFontFamily(String fontFamily) { this.fontFamily = fontFamily; }

    // FontSize 字体大小
    public int getFontSize() { return fontSize; }
    public void setFontSize(int fontSize) { this.fontSize = fontSize; }

    // Bold 字体加粗
    public boolean isBold() { return isBold; }
    public void setBold(boolean bold) { isBold = bold; }

    // Italic 斜体
    public boolean isItalic() { return isItalic; }
    public void setItalic(boolean italic) { isItalic = italic; }

    // Underline 下划线
    public boolean isUnderline() { return isUnderline; }
    public void setUnderline(boolean underline) { isUnderline = underline; }

    // StrikeThrough 删除线
    public boolean isStrikeThrough() { return isStrikeThrough; }
    public void setStrikeThrough(boolean strikeThrough) { isStrikeThrough = strikeThrough; }

    // Superscript 上标
    public boolean isSuperscript() { return isSuperscript; }
    public void setSuperscript(boolean superscript) { isSuperscript = superscript; }

    // Subscript 下标
    public boolean isSubscript() { return isSubscript; }
    public void setSubscript(boolean subscript) { isSubscript = subscript; }

    // Foreground 文本颜色
    public Color getForeground() { return foreground; }
    public void setForeground(Color foreground) { this.foreground = foreground; }

    // Background 背景颜色
    public Color getBackground() { return background; }
    public void setBackground(Color background) { this.background = background; }

    // ComponentAttribute 插入组件
    public Component getComponentAttribute() { return componentAttribute; }
    public void setComponentAttribute(Component componentAttribute) { this.componentAttribute = componentAttribute; }

    // IconAttribute 插入小图标
    public Icon getIconAttribute() { return iconAttribute; }
    public void setIconAttribute(Icon iconAttribute) { this.iconAttribute = iconAttribute; }

    // ModelAttribute 特殊模型属性
    public Object getModelAttribute() { return modelAttribute; }
    public void setModelAttribute(Object modelAttribute) { this.modelAttribute = modelAttribute; }

    // SpaceAbove 段落上方较空
    public int getSpaceAbove() { return spaceAbove; }
    public void setSpaceAbove(int spaceAbove) { this.spaceAbove = spaceAbove; }

    // SpaceBelow 段落下方较空
    public int getSpaceBelow() { return spaceBelow; }
    public void setSpaceBelow(int spaceBelow) { this.spaceBelow = spaceBelow; }

    // FirstLineIndent 首行缩进
    public int getFirstLineIndent() { return firstLineIndent; }
    public void setFirstLineIndent(int firstLineIndent) { this.firstLineIndent = firstLineIndent; }

    // LeftIndent 段落左缩进
    public int getLeftIndent() { return leftIndent; }
    public void setLeftIndent(int leftIndent) { this.leftIndent = leftIndent; }

    // RightIndent 段落右缩进
    public int getRightIndent() { return rightIndent; }
    public void setRightIndent(int rightIndent) { this.rightIndent = rightIndent; }

    // LineSpacing 行间距
    public float getLineSpacing() { return lineSpacing; }
    public void setLineSpacing(float lineSpacing) { this.lineSpacing = lineSpacing; }

    // TabSet 制表符
    public TabSet getTabSet() { return tabSet; }
    public void setTabSet(TabSet tabSet) { this.tabSet = tabSet; }

    @Override
    public String toString(){
        String output = "";
/*      java.util.List<Object> list = selectNonNullElement(Arrays.asList(
                fontFamily,fontSize,isBold,isItalic,isUnderline,isStrikeThrough,isSuperscript,isSubscript,foreground,background,
                componentAttribute,iconAttribute,modelAttribute,spaceAbove,spaceBelow,firstLineIndent,leftIndent,rightIndent,
                lineSpacing,tabSet));  */
        if (fontFamily != null && !fontFamily.isEmpty()) output += "&FF(" + fontFamily + ")";
        if (fontSize > 0)  output += "&FS" + fontSize;
        if (isBold) output += "&XA";
        if (isItalic) output += "&XB";
        if (isUnderline) output += "&XC";
        if (isStrikeThrough) output += "&XD";
        if (isSuperscript) output += "&XE";
        if (isSubscript) output += "&XF";
        if (foreground != null) output += "&CF" + FontHandler.colorToString(foreground);
        if (background != null) output += "&CB" + FontHandler.colorToString(background);
        if (componentAttribute != null) { }//ignored
        if (iconAttribute != null) { }//ignored
        if (modelAttribute != null) { }//ignored
        if (spaceAbove > 0) output += "&SA" + spaceAbove;
        if (spaceBelow > 0) output += "&SB" + spaceBelow;
        if (firstLineIndent > 0) output += "&FL" + firstLineIndent;
        if (leftIndent > 0) output += "&LI" + leftIndent;
        if (rightIndent > 0) output += "&RI" + rightIndent;
        if (lineSpacing > 0) output += "&LS" + lineSpacing;
        if (tabSet != null) { }//ignored
        if (output.indexOf("&")!=output.lastIndexOf("&")) output = "["+output+"]";
        return output;
    }

    public Style asStyle(){
        StyleContext sc = new StyleContext();
        Style style = sc.addStyle("Style",null);
        if (fontFamily != null && !fontFamily.isEmpty()) StyleConstants.setFontFamily(style, fontFamily);
        if (fontSize > 0) StyleConstants.setFontSize(style, fontSize);
        if (isBold) StyleConstants.setBold(style, true);
        if (isItalic) StyleConstants.setItalic(style, true);
        if (isUnderline) StyleConstants.setUnderline(style, true);
        if (isStrikeThrough) StyleConstants.setStrikeThrough(style, true);
        if (isSuperscript) StyleConstants.setSuperscript(style, true);
        if (isSubscript) StyleConstants.setSubscript(style, true);
        if (foreground != null) StyleConstants.setForeground(style, foreground);
        if (background != null) StyleConstants.setBackground(style, background);
        if (componentAttribute != null) StyleConstants.setComponent(style, componentAttribute);
        if (iconAttribute != null) StyleConstants.setIcon(style, iconAttribute);
        if (spaceAbove > 0) StyleConstants.setSpaceAbove(style, spaceAbove);
        if (spaceBelow > 0) StyleConstants.setSpaceBelow(style, spaceBelow);
        if (firstLineIndent > 0) StyleConstants.setFirstLineIndent(style, firstLineIndent);
        if (leftIndent > 0) StyleConstants.setLeftIndent(style, leftIndent);
        if (rightIndent > 0) StyleConstants.setRightIndent(style, rightIndent);
        if (lineSpacing > 0) StyleConstants.setLineSpacing(style, lineSpacing);
        if (tabSet != null) StyleConstants.setTabSet(style, tabSet);
        return style;
    }

    private java.util.List<Object> selectNonNullElement(java.util.List<Object> list){
        return list.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }
}
