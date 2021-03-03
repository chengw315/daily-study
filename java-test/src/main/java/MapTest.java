import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/12/8
 **/
public class MapTest {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.remove("as");
        String as = map.get("as");
        for (int i = 0; i < 26; i++) {
            String s = String.valueOf((char) ('a'+i));
            map.put(s,s);
        }
        Set<String> set = map.keySet();
        HashSet<String> set1 = new HashSet<>(16);
        set1.add("a");
        set1.add("b");
        set1.add("v");
        set.retainAll(set1);
    }
}
