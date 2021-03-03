package daily;

import java.util.*;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/8/27
 **/
public class LeetCode332 {
    public static void main(String[] args) {
        ArrayList<List<String>> tickets = new ArrayList<>();
        String s = "700000001001";
        int i = (s.charAt(s.length()-1)) % 4;
        //[["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
        ArrayList<String> ticket1 = new ArrayList<>(); ticket1.add("MUC"); ticket1.add("JFK"); tickets.add(ticket1);
        ArrayList<String> ticket2 = new ArrayList<>(); ticket2.add("JFK"); ticket2.add("MUC"); tickets.add(ticket2);
        ArrayList<String> ticket3 = new ArrayList<>(); ticket3.add("JFK"); ticket3.add("HJC"); tickets.add(ticket3);
//        ArrayList<String> ticket1 = new ArrayList<>(); ticket1.add("MUC"); ticket1.add("LHR"); tickets.add(ticket1);
//        ArrayList<String> ticket2 = new ArrayList<>(); ticket2.add("JFK"); ticket2.add("MUC");tickets.add(ticket2);
//        ArrayList<String> ticket3 = new ArrayList<>(); ticket3.add("SFO"); ticket3.add("SJC");tickets.add(ticket3);
//        ArrayList<String> ticket4 = new ArrayList<>(); ticket4.add("LHR"); ticket4.add("SFO");tickets.add(ticket4);
        List<String> itinerary = new LeetCode332().findItinerary(tickets);
    }

    public List<String> findItinerary(List<List<String>> tickets) {
        
        //查票结构，map<from,优先队列<to>>
        HashMap<String, List<String>> map = new HashMap<>(tickets.size());
        //计数
        HashMap<String,Integer> count = new HashMap<>(tickets.size()+1);
        for (List<String> ticket:tickets) {
            if (!map.containsKey(ticket.get(0))) {
                map.put(ticket.get(0),new ArrayList<>());
            }
            map.get(ticket.get(0)).add(ticket.get(1));
            count.put(ticket.get(0),1);
            count.put(ticket.get(1),1);
        }
        for (List<String> tos :
                map.values()) {
            Collections.sort(tos);
        }
        List<String> result = new ArrayList<String>(tickets.size() * 2 + 1);
        //从当前站开始遍历
        findNextStation(map,"JFK",count,result,0);
        return result;
    }

    private boolean findNextStation(HashMap<String, List<String>> map, String curSta, HashMap<String, Integer> count, List<String> result, int curIndex) {
        result.add(curSta);
        count.put(curSta,count.get(curSta) - 1);
        int index = curIndex + 1;
        //全走过了,结束
        if(!count.containsValue(1)) return true;

        List<String> nextStations = map.get(curSta);
        int i = 0;
        while (nextStations != null && i < nextStations.size()) {
            String nextSta = nextStations.get(i++);
            if (findNextStation(map, nextSta, count, result, index)) return true;
        }
        //此路不通，回溯
        count.put(curSta,count.get(curSta) + 1);
        result.remove(curIndex);
        return false;
    }

}
