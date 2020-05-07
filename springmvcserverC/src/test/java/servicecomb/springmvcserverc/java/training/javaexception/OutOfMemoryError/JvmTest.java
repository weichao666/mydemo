package servicecomb.springmvcserverc.java.training.javaexception.OutOfMemoryError;

import java.util.ArrayList;
import java.util.List;

//堆溢出(OutOfMemoryError:java heap space)
//heap space表示堆空间，堆中主要存储的是对象。如果不断的new对象则会导致堆中的空间溢出
//可以通过 -Xmx4096M 调整堆的总大小
public class JvmTest {
    public static void main(String[] args) {
        List<String> aList = new ArrayList<>();
        try {
            while (true) {
                aList.add("abcde");
            }
        } catch (Throwable e) {
            System.out.println(aList.size());
            e.printStackTrace();
        }
    }
}
