package priv.samera2022.test;

import javax.swing.*;
import javax.swing.text.*;

/*
      该类是真正实现超长单词都能自动换行的JTextPane 的子类
      Java7以下版本的JTextPane 本身都能实现自动换行，对
      超长单词都能有效，但从Java7 开始读超长单词就不能自动
      换行，导致JTextPane 的实际宽度变大，使得滚动条出现。
      下面的方法是对这个 bug 的较好修复。
      Created by dolphin on 15-2-3

*/
public class JIMSendTextPane extends JTextPane {
    // 内部类
    // 以下内部类全都⽤于实现⾃动强制折⾏
    private class WarpEditorKit extends StyledEditorKit {
        private ViewFactory defaultFactory = new WarpColumnFactory();

        @Override
        public ViewFactory getViewFactory() {
            return defaultFactory;
        }
    }

    private class WarpColumnFactory implements ViewFactory {
        public View create(Element elem) {
            String kind = elem.getName();
            if (kind != null) {
                if (kind.equals(AbstractDocument.ContentElementName)) {
                    return new WarpLabelView(elem);
                } else if (kind.equals(AbstractDocument.ParagraphElementName)) {
                    return new ParagraphView(elem);
                } else if (kind.equals(AbstractDocument.SectionElementName)) {
                    return new BoxView(elem, View.Y_AXIS);
                } else if (kind.equals(StyleConstants.ComponentElementName)) {
                    return new ComponentView(elem);
                } else if (kind.equals(StyleConstants.IconElementName)) {
                    return new IconView(elem);
                }
            }
            // default to text display
            return new LabelView(elem);
        }
    }

    private class WarpLabelView extends LabelView {
        public WarpLabelView(Element elem) {
            super(elem);
        }

        @Override
        public float getMinimumSpan(int axis) {
            switch (axis) {
                case View.X_AXIS:
                    return 0;
                case View.Y_AXIS:
                    return super.getMinimumSpan(axis);
                default:
                    throw new IllegalArgumentException("Invalid axis: " + axis);
            }
        }
    }

    // 本类
    // 构造函数
    public JIMSendTextPane() {
        super();
        this.setEditorKit(new WarpEditorKit());
    }
}
