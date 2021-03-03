package daily;

/**
 * description 字符串模式匹配
 *
 * 示例：
 *   模式pattern：aabba （a、b组合成的字符串）
 *   字符串：dogdogcatcatdog
 *   结果：true
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/6/22
 **/
public class Leet {

    public static void main(String[] args) {
        //true
        boolean b = new Leet().patternMatching("aabba", "dogdogcatcatdog");
        boolean b2 = new Leet().patternMatching("aaaa", "dogdogdogdog");
        boolean b3 = new Leet().patternMatching("", "");
        boolean b4 = new Leet().patternMatching("a", "");

        //false
        boolean b5 = new Leet().patternMatching("ab", "");
        boolean b6 = new Leet().patternMatching("bbbaa", "xxxxxxy");
    }


    public boolean patternMatching(String pattern, String value) {
        if(pattern == null || value == null) {
            return false;
        }
        int numOfA = 0, numOfB = 0;
        for(int i = 0; i < pattern.length(); i++) {
            if(pattern.charAt(i) == 'a') {
                numOfA++;
                continue;
            }
            if(pattern.charAt(i) == 'b') {
                numOfB++;
                continue;
            }
        }
        int length = value.length();
        boolean isAMore = numOfA > numOfB;

        int max = numOfA + numOfB == 0 ? 0 : length / Math.max(numOfA, numOfB);
        outer:
        for(int i = 0; i <= max; i++) {
            int lengthOfA,lengthOfB;
            if(isAMore) {
                lengthOfA = i;
                lengthOfB = numOfB == 0 ? 0 : (length - lengthOfA * numOfA) / numOfB;
            } else {
                lengthOfB = i;
                lengthOfA = numOfA == 0 ? 0 : (length - lengthOfB * numOfB) / numOfA;
            }
            if(lengthOfA * numOfA + lengthOfB * numOfB != length) {
                continue;
            }

            //开始匹配
            int beginOfA = Math.max(pattern.indexOf('a') * lengthOfB, 0);
            int beginOfB = Math.max(pattern.indexOf('b') * lengthOfA, 0);
            String a = value.substring(beginOfA, beginOfA + lengthOfA);
            String b = value.substring(beginOfB, beginOfB + lengthOfB);
            if(numOfA != 0 && numOfB != 0 && a.equals(b)) {
                return false;
            }
            int index = 0;
            for(int j = 0; j < pattern.length(); j++) {
                if(pattern.charAt(j) == 'a') {
                    if(match(value,index,lengthOfA,a)) {
                        index += lengthOfA;
                        continue;
                    }
                    continue outer;
                }
                if(pattern.charAt(j) == 'b') {
                    if(match(value,index,lengthOfB,b)) {
                        index += lengthOfB;
                        continue;
                    }
                    continue outer;
                }
            }
            return true;
        }
        return false;
    }

    private boolean match(String s, int index, int lengthOfD, String d) {
        if(index + lengthOfD > s.length() || lengthOfD > d.length()) {
            return false;
        }
        for(int i = 0; i < lengthOfD; i++) {
            if(s.charAt(index + i) != d.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}
