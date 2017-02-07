package com.taobao.text.ui.example;

import com.taobao.text.util.CharSlicer;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author wangtao 2017-02-07 11:17.
 */
public class CharSlicerStackOverflow {

    @Test
    public void testNonrecursiveSize() {
        StringBuilder sb = new StringBuilder();
        int lines = 10000;
        int maxLength = 0;
        for (int i = 0; i < lines; i++) {
            int length = (int)(Math.random() * 100);
            if (length > maxLength) maxLength = length;
            for (int j = 0; j < length; j++) {
                sb.append("a");
            }
            sb.append("\n");
        }
        CharSlicer charSlicer = new CharSlicer(sb.toString());
        Assert.assertEquals(charSlicer.size().getFirst().intValue(), maxLength);
        Assert.assertEquals(charSlicer.size().getSecond().intValue(), lines + 1);
    }
}
