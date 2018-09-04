import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

//ConcurrentHashMap从类的命名就能看出，它必然是个HashMap。而Collections.synchronizedMap()可以接收任意Map实例，实现Map的同步
//map2由于是个TreeMap，最后打印的结果有按照Key值排序的，而map3显然没法保证结果的有序
public class Maptest2 {
    private static void writeMap(Map<String, Object> map) {
        for (int i = 0; i < 10; i++) {
            map.put("key" + i, "value" + i);
        }
    }
    private static void printMap(Map<String, Object> map) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println("key: " + entry.getKey() + ", value: " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        Map<String, Object> map1 = new HashMap<>();
        writeMap(map1);
        printMap(map1);

        System.out.println();

        Map<String, Object> map2 = Collections.synchronizedMap(new TreeMap<>());
        writeMap(map2);
        printMap(map2);

        System.out.println();

        Map<String, Object> map3 = new ConcurrentHashMap<>();
        writeMap(map3);
        printMap(map3);
    }
}
