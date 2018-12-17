import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Maptest3 {
  public static void main(String[] args) {
    Map<String, String> map = new HashMap<>();
    map.put("1", "1");
    map.put("2", "2");

    map.putIfAbsent("1", "11");
    map.putIfAbsent("3", "3");
//    Map<String, String> map2 = new HashMap<>();
//    map2.put("1", "11");
//    map2.put("3", "3");
//    map.putAll(map2);
    System.out.println("map:" + map);
  }
}
