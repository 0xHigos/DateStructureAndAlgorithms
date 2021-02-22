package greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Greedy {
    public static void main(String[] args) {
        HashMap<String, HashSet<String>> broadcasts = new HashMap<>();
        HashSet<String> h1 = new HashSet<>();
        h1.add("北京");
        h1.add("上海");
        h1.add("天津");

        HashSet<String> h2 = new HashSet<>();
        h2.add("广州");
        h2.add("北京");
        h2.add("深圳");

        HashSet<String> h3 = new HashSet<>();
        h3.add("成都");
        h3.add("上海");
        h3.add("天津");

        HashSet<String> h4 = new HashSet<>();
        h4.add("上海");
        h4.add("天津");
        h4.add("大连");

        HashSet<String> h5 = new HashSet<>();
        h5.add("杭州");
        h5.add("大连");
        h5.add("重庆");

        broadcasts.put("k1", h1);
        broadcasts.put("k2", h2);
        broadcasts.put("k3", h3);
        broadcasts.put("k4", h4);
        broadcasts.put("k5", h5);

        HashSet<String> allAreas = new HashSet<String>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");
        allAreas.add("重庆");

        ArrayList<String> selects = new ArrayList<>();
        HashSet<String> tempSet = new HashSet<>();
        String maxKey ;
        while (allAreas.size() > 0) {
            maxKey = null;
            for (String key : broadcasts.keySet()) {
                tempSet.clear();
                HashSet<String> set = broadcasts.get(key);
                tempSet.addAll(set);
                tempSet.retainAll(allAreas);
                if(maxKey != null){
                    set = broadcasts.get(maxKey);
                    set.retainAll(allAreas);
                }
                if (tempSet.size() > 0 &&
                        (maxKey == null || tempSet.size() > set.size())) {
                    maxKey = key;
                }
            }
            if (maxKey != null) {
                selects.add(maxKey);
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }

        System.out.println("得到的结果为："+selects);
    }
}
