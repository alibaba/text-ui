
### 输出Table的例子

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
    public void test1(){
        //header定义
        String[] fields = {"name", "age"};
        
        // 设置两列的比例是1:1，如果不设置的话，列宽是自动按元素最长的处理。
        //设置table的外部边框，默认是没有外边框
        //还有内部的分隔线，默认内部没有分隔线
        TableElement tableElement = new TableElement(1, 1)
                .border(BorderStyle.DASHED)
                .separator(BorderStyle.DASHED);
        //设置header
        tableElement.row(true, fields);
        
        //设置cell里的元素超出了处理方式，Overflow.HIDDEN 表示隐藏
        // Overflow.WRAP表示会向外面排出去，即当输出宽度有限时，右边的列可能会显示不出，被挤掉了
        tableElement.overflow( Overflow.HIDDEN);

        for(int i = 0 ; i < 10 ; ++i){
            tableElement.
            add(row().
                add(label("student" + i)).
                add(label("name" + i)));
        }
        
        //默认输出宽度是80
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

更多的例子请参考test下面的example。
