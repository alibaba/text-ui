package com.taobao.text.ui.example;

import static com.taobao.text.ui.Element.label;
import static com.taobao.text.ui.Element.row;

import org.junit.Test;

import com.taobao.text.ui.BorderStyle;
import com.taobao.text.ui.Overflow;
import com.taobao.text.ui.TableElement;
import com.taobao.text.util.RenderUtil;


public class TableExample {

    @Test
    public void test1(){
        // 设置两列的比例是1:1，如果不设置的话，列宽是自动按元素最长的处理。
        //设置table的外部边框，默认是没有外边框
        //还有内部的分隔线，默认内部没有分隔线
        //设置了header
        TableElement tableElement = new TableElement(1, 1)
                .border(BorderStyle.DASHED)
                .separator(BorderStyle.DASHED).header(label("name"), label("age"));

        
        //设置cell里的元素超出了处理方式，Overflow.HIDDEN 表示隐藏
        // Overflow.WRAP表示会向外面排出去，即当输出宽度有限时，右边的列可能会显示不出，被挤掉了
        tableElement.overflow( Overflow.HIDDEN);

        for(int i = 0 ; i < 100 ; ++i){
            tableElement.
            add(row().
                add(label("student" + i)).
                add(label("name" + i)));
        }
        
        //默认输出宽度是80
        System.err.println(RenderUtil.render(tableElement));
    }
}
