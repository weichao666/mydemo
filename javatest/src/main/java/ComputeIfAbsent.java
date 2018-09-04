import java.util.HashMap;
import java.util.Map;

//computeIfPresent和computeIfAbsent
//这两个方法正好相反，前者是在key存在时，才会用新的value替换oldValue
//当传入的key存在，并且传入的value为null时，前者会remove（key），把传入的key对应的映射关系移除；而后者不论何时都不会remove()；
//前者只有在key存在，并且传入的value不为空的时候，返回值是value，其他情况都是返回null；
// 后者只有在key不存在，并且传入的value不为null的时候才会返回value，其他情况都返回null；
public class ComputeIfAbsent {
    static Map<String, String> stringMap = new HashMap<>();
    public static void main(String[] args) {
        for (int i = 1; i < 6; i++) {
            //putIfAbsent不需要我们做额外的存在性检查
            stringMap.putIfAbsent(String.valueOf(i), "value" + i);
        }
        stringMap.forEach((k, val) -> System.out.println(val));
        //使用场景：从map中获取所需要的value，若其为null，就用准备好的值即Function的返回值
        Object result1 = stringMap.computeIfAbsent("1", k -> "bam");
        Object result2 = stringMap.computeIfAbsent("6", k -> "newaddvalue6");
        System.out.println();
        for (Map.Entry n : stringMap.entrySet()) {
            System.out.println(n);
        }
        System.out.println();

        //使用场景：更新map中存在的且其value值不为null的键值对的值
        //*存在且value不为null：1.BiFunction返回值为null，删除该节点；2.BiFunction返回值不为null，作为新value，返回其值
        //*不存在或其value为null，返回null
        stringMap.computeIfPresent("6", (k, val) -> val + k);
        System.out.println("6=" + stringMap.get("6"));
        stringMap.computeIfPresent("6", (k, val) -> null);
        System.out.println(stringMap.containsKey("6"));

    }
}
