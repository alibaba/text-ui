package com.taobao.text.util;

import java.io.IOException;
import java.util.Iterator;

import com.taobao.text.Format;
import com.taobao.text.LineReader;
import com.taobao.text.LineRenderer;
import com.taobao.text.RenderAppendable;
import com.taobao.text.Renderer;
import com.taobao.text.ScreenBuffer;
import com.taobao.text.ScreenContext;
import com.taobao.text.Screenable;
import com.taobao.text.Style;
import com.taobao.text.ui.Element;

/**
 * 
 * @author duanling 2015年11月9日 下午11:56:03
 *
 */
public class RenderUtil {
    public static final int defaultWidth = 80;
    /**
     * 实际上这个参数通常不起作用
     */
    public static final int defaultHeight = 80;
    
    /**
     * 把Element 渲染为String，默认width是80
     * 
     * @param element
     * @param width
     * @return
     */
    static public String render(final Element element) {
        return render(element, defaultWidth, defaultHeight);
    }
    
    /**
     * 把Element 渲染为String
     * 
     * @param element
     * @param width
     * @return
     */
    static public String render(final Element element, final int width) {
        return render(element, width, defaultHeight);
    }

    /**
     * 把Element 渲染为String
     * 
     * @param element
     * @param width
     * @param height
     * @return
     */
    static public String render(final Element element, final int width, final int height) {
        LineReader renderer = element.renderer().reader(width);
        return render(renderer, width, height);
    }

    static public String render(final LineReader renderer, final int width, final int height) {
        StringBuilder result = new StringBuilder(2048);
        while (renderer.hasLine()) {
            final ScreenBuffer buffer = new ScreenBuffer();
            renderer.renderLine(new RenderAppendable(new ScreenContext() {
                public int getWidth() {
                    return width;
                }

                public int getHeight() {
                    return height;
                }

                public Screenable append(CharSequence s) throws IOException {
                    buffer.append(s);
                    return this;
                }

                public Appendable append(char c) throws IOException {
                    buffer.append(c);
                    return this;
                }

                public Appendable append(CharSequence csq, int start, int end) throws IOException {
                    buffer.append(csq, start, end);
                    return this;
                }

                public Screenable append(Style style) throws IOException {
                    buffer.append(style);
                    return this;
                }

                public Screenable cls() throws IOException {
                    buffer.cls();
                    return this;
                }

                public void flush() throws IOException {
                    buffer.flush();
                }
            }));
            StringBuilder sb = new StringBuilder();
            try {
                buffer.format(Format.ANSI, sb);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            result.append(sb.toString()).append('\n');
        }
        return result.toString();
    }

}
