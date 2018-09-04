package servicecomb.springmvcserverc.maptest;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class TestMap {
    private static Map<String, Object> map1 = new HashMap<>();
    private static Map<String, Object> map2 = new Hashtable<>();
    private static Map<String, Object> map3 = new ConcurrentHashMap<>();
    private static Map<String, Object> map4 = Collections.synchronizedMap(new HashMap<>());

    private static Map<String, Object> map = map4;

    @Test
    public void testmap() {
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
