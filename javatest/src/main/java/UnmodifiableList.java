import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//unmodifiableList()方法用于返回指定列表的不可修改视图
public class UnmodifiableList {
    public static void main(String [] args) {
        List<Character> list = new ArrayList<>();

        list.add('X');
        list.add('Y');

        System.out.println("Initial list: " + list);

        //make the list unmodifiable
        List<Character> immutablelist = Collections.unmodifiableList(list);

        //try to modify the list
        immutablelist.add('Z');
    }
}
