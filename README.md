
text-ui
===

## Introduction

This code extract from crash shell: https://github.com/crashub/crash/tree/1.3.2/shell


### 输出Table的例子（自适应列宽，字体颜色等）

可以用maven查看运行效果：

```bash
mvn test -Dtest=com.taobao.text.ui.example.TableExample
```

```java
import static com.taobao.text.ui.Element.label;
import static com.taobao.text.ui.Element.row;

import org.junit.Test;

import com.taobao.text.ui.BorderStyle;
import com.taobao.text.ui.Overflow;
import com.taobao.text.ui.TableElement;
import com.taobao.text.util.RenderUtil;


public class TableExample {

    @Test
    public void test1() {
        // header定义
        String[] fields = { "name", "age" };

        // 设置两列的比例是1:1，如果不设置的话，列宽是自动按元素最长的处理。
        // 设置table的外部边框，默认是没有外边框
        // 还有内部的分隔线，默认内部没有分隔线
        TableElement tableElement = new TableElement(1, 1).border(BorderStyle.DASHED).separator(BorderStyle.DASHED);

        // 设置单元格的左右边框间隔，默认是没有，看起来会有点挤，空间足够时，可以设置为1，看起来清爽
        tableElement.leftCellPadding(1).rightCellPadding(1);

        // 设置header
        tableElement.row(true, fields);

        // 设置cell里的元素超出了处理方式，Overflow.HIDDEN 表示隐藏
        // Overflow.WRAP表示会向外面排出去，即当输出宽度有限时，右边的列可能会显示不出，被挤掉了
        tableElement.overflow(Overflow.HIDDEN);

        // 设置第一列输出字体蓝色，红色背景
        // 设置第二列字体加粗，加下划线
        for (int i = 0; i < 10; ++i) {
            tableElement.add(row().add(label("student" + i).style(Composite.style(Color.blue).bg(Color.red)))
                    .add(label("" + i).style(Decoration.bold.underline())));
        }

        // 默认输出宽度是80
        System.err.println(RenderUtil.render(tableElement));
    }
}
```

### 输出POJO List的例子

```java
public class POJOExample {

    public class Student {
        String name;
        int age;

        public Student(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    @Test
    public void test1(){
        
        List<Student> list = new ArrayList<Student>();
        
        for(int i = 0; i < 10; ++i){
            list.add(new Student("name" + i, 10 + i));
        }
        
        System.err.println(RenderUtil.render(list));
    }
}
```

## 设置Element的Style

Style有下面六种

```
Boolean bold        是否粗体
Boolean underline   是否有下划线
Boolean blink       是否闪烁
Color foreground    前景颜色
Color background    背景颜色
```

对于每一个Element都可以设置Style，比如一个Table里的一个小格子。

设置Style可以通过Composite来构造，比如

```java
//设置字体颜色为蓝色
Element.label("xxx").style(Composite.style(Color.blue));
```

也可以通过Decoration这个辅助类来设置，这个类定义了一些常见的Style。

```java
//设置为粗体，背景颜色是红色
Element.label("xxx").style(Decoration.bold.bg(Color.red));
```

## 语法高亮功能
支持对多种语法的语法高亮。默认是java语言，可以传递不同的语言参数。

```java
public class HighlightExample {

    public static void main(String[] args) {

        String code = "int a = 123; \nString s = \"sssss\";";

        System.out.println(LangRenderUtil.render(code, LangRenderUtil.java));
    }

}
```

需要增加依赖

```xml
		<dependency>
			<groupId>com.fifesoft</groupId>
			<artifactId>rsyntaxtextarea</artifactId>
			<version>2.5.8</version>
		</dependency>
```


更多的例子请参考test下面的example。