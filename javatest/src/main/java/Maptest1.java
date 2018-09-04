import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Maptest1 {
    private static Map<String, Object> map1 = new HashMap<>();
    private static Map<String, Object> map2 = new Hashtable<>();
    private static Map<String, Object> map3 = new ConcurrentHashMap<>();
    private static Map<String, Object> map4 = Collections.synchronizedMap(new HashMap<>());

    private static Map<String, Object> map = map3;
    //当类中对象map值取map1,map2,map4时，程序都抛出java.util.ConcurrentModificationException异常。当map=map3时，代码能正常运行
    //Collections.synchronizedMap()与ConcurrentHashMap主要区别是：
    // Collections.synchronizedMap()和Hashtable一样，实现上在调用map所有方法时，都对整个map进行同步，
    // 而ConcurrentHashMap的实现却更加精细，它对map中的所有桶加了锁。所以，只要要有一个线程访问map，其他线程就无法进入map，
    // 而如果一个线程在访问ConcurrentHashMap某个桶时，其他线程，仍然可以对map执行某些操作。
    // 这样，ConcurrentHashMap在性能以及安全性方面，明显比Collections.synchronizedMap()更加有优势。同时，同步操作精确控制到桶，
    // 所以，即使在遍历map时，其他线程试图对map进行数据修改，也不会抛出ConcurrentModificationException

    //下面的例子，即使map=map3时，最后打印的结果可以并没有100行。
    // 由于，不论Collections.synchronizedMap()还是ConcurrentHashMap对map同步的原子操作都是作用的map的方法上，map在读取与清空之间，线程间是不同步的。
    // 下面代码的不足在于，我们对这些同步的map过于信任，而忽略了混合操作带来的影响。
    // 正确的方法是，把map的读取和清空看成一个原子操作，给整个代码块加锁

    public  static void main(String[] args) {
        testmap1();
    }

    private static void testmap1() {
        new Thread(() -> {
            while (true) {
                if (map.size() > 0) {
                    for (Map.Entry<String, Object> entry : map.entrySet()) {
                        System.out.println(String.format("%s: %s", entry.getKey(), entry.getValue()));
                    }
                    map.clear();
                }
                try {
                    Thread.sleep((new Random().nextInt(10) + 1) * 1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 1; i <= 100; i++) {
                map.put("key" + i, "value" + i);
                try {
                    Thread.sleep((new Random().nextInt(10) + 1) * 1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
