package com.taobao.text.ui.example;

import static com.taobao.text.ui.Element.label;
import static com.taobao.text.ui.Element.row;

import org.junit.Test;

import com.taobao.text.Color;
import com.taobao.text.Decoration;
import com.taobao.text.Style.Composite;
import com.taobao.text.ui.BorderStyle;
import com.taobao.text.ui.Overflow;
import com.taobao.text.ui.TableElement;
import com.taobao.text.util.RenderUtil;

public class TableExample {

    @Test
    public void test1() {
        // header定义
        String[] fields = { "name", "age", "type", "memo" };

        // 设置两列的比例是1:1，如果不设置的话，列宽是自动按元素最长的处理。
        // 设置table的外部边框，默认是没有外边框
        // 还有内部的分隔线，默认内部没有分隔线
        TableElement tableElement = new TableElement(1, 1, 1, 6).border(BorderStyle.DASHED).separator(BorderStyle.DASHED);

        // 设置单元格的左右边框间隔，默认是没有，看起来会有点挤，空间足够时，可以设置为1，看起来清爽
        tableElement.leftCellPadding(1).rightCellPadding(1);

        // 设置header
       // tableElement.row(true, fields);
        tableElement.row(true,  label("name", 12),  label("age", 3),  label("type", 4), label("memo", 10));
        

        // 设置cell里的元素超出了处理方式，Overflow.HIDDEN 表示隐藏
        // Overflow.WRAP表示会向外面排出去，即当输出宽度有限时，右边的列可能会显示不出，被挤掉了
        //tableElement.overflow(Overflow.HIDDEN);

        // 设置第一列输出字体蓝色，红色背景
        // 设置第二列字体加粗，加下划线
        for (int i = 0; i < 10; ++i) {
            tableElement.add(row().add(label("student" + i, 12).style(Composite.style(Color.blue).bg(Color.red)))
                    .add(label("" + i).style(Decoration.bold.underline()))
                    .add(label("1"))
                    .add(label("AAAAAAAAAAAAAAAAAAAAAAAAA AAAAAAAAAAAAAAAAAAAAAAAAAAAA")));
        }

        // 默认输出宽度是80
        System.err.println(RenderUtil.render(tableElement, 60));
    }
}
