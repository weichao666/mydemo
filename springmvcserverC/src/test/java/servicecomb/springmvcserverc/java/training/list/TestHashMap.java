package servicecomb.springmvcserverc.java.training.list;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

//hashSet使用的是hashMap的put方法，
// 而hashMap的put方法，使用hashCode()用key作为参数计算出hash值，然后进行比较，
// 如果相同，再通过equals()比较key值是否相同，如果相同，返回同一个对象
//有一个”国家”(Country)类，我们将要用Country对象作为key，它的首都的名字（String类型）作为value
public class TestHashMap {
    public static void main(String[] args) {
        Country india = new Country("India", 1000);
        Country japan = new Country("Japan", 10000);

        Country france = new Country("France", 2000);
        Country russia = new Country("Russia", 20000);

        Map<Country, String> countryMap = new HashMap<Country, String>();
        countryMap.put(india, "Deihi");
        countryMap.put(japan, "Tokyo");
        countryMap.put(france, "Paris");
        countryMap.put(russia, "Moscow");

        Iterator<Country> iterator = countryMap.keySet().iterator();

        while (iterator.hasNext()) {
            Country country = iterator.next();
            String capital = countryMap.get(country);
            System.out.println(country.getName() + "---" + capital);
        }
    }
}
