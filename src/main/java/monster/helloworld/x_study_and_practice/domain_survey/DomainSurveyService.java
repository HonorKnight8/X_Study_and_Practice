package monster.helloworld.x_study_and_practice.domain_survey;

import java.util.*;

public class DomainSurveyService {

    public static LinkedHashMap<String, Object> clipData(TreeMap<String, Object> treeMap) {
//        System.out.println(treeMap);
        // 修剪 Map ，去掉不用的数据，转为方便前端使用的结构
        LinkedHashMap<String, Object> returnMap = new LinkedHashMap<>();
        returnMap.put("xAxis", treeMap.keySet()); // 数据的月份可以直接获取
//        System.out.println(returnMap);

        LinkedList<String> legendList = new LinkedList<>(); // 用于存放域名（图例，key）

        Iterator<Map.Entry<String, Object>> entries = treeMap.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, Object> entry = entries.next();
            String key = entry.getKey();
//            System.out.println(entry.getValue().getClass());
            ArrayList arrayList = (ArrayList) entry.getValue();
//            System.out.println(arrayList.size());

            Iterator iterator = arrayList.iterator();
            iterator.next();

            for (int i = 1; i <= 10; i++) {
                ArrayList next = (ArrayList) iterator.next();
//                System.out.println(next);
//                System.out.println(next.getClass());
                if (!legendList.contains(next.get(0))) {
                    legendList.add((String) next.get(0));
                }
            }
        }
        returnMap.put("legend", legendList);
//        System.out.println(returnMap);


        LinkedHashMap<String, ArrayList> dataMap = new LinkedHashMap<>();        //用于存放各域名数据的treeMap，"com" => [100, 102, ....]
        for (String legend : legendList) {
            dataMap.put(legend, new ArrayList<Integer>());
        }
//        System.out.println(dataTreeMap.size());
//        System.exit(99);

        entries = null;
        entries = treeMap.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, Object> entry = entries.next();

            ArrayList arrayList = (ArrayList) entry.getValue();

            Iterator iterator = arrayList.iterator();
            iterator.next();

            for (int i = 1; i <= 10; i++) {
                ArrayList next = (ArrayList) iterator.next();

                ArrayList<Integer> dataList = dataMap.get((String) next.get(0));
                dataList.add(Integer.valueOf(((String) next.get(2)).replaceAll(",", "")));
                dataMap.put((String) next.get(0), dataList);
            }
        }
//        System.out.println(dataMap);
//{
// cn=[3487976, 3127264, 3681774, 3340467, 2961545, 3165031, 3137429, 2918876, 2592278, 2467883, 2930001, 3312708],
// com=[26420296, 25984882, 28765128, 30612165, 30110523, 31845919, 31487525, 29353394, 27753261, 26947459, 29068049, 29654911],
// de=[3452824, 3444705, 3450578, 3420299, 3397209, 3377446, 3341197, 3344049, 3337772, 3330805, 3340564, 3337937],
// jp=[979547, 982798, 974986, 981275, 985992, 979273, 967386, 976331, 972739, 954961, 956833, 957819],
// net=[2608257, 2569412, 2606602, 2811374, 2751620, 2773411, 2730883, 2714037, 2629430, 2579017, 2634270, 2594168],
// nl=[1745664, 1733637, 1723428, 1726758, 1728325, 1701288, 1707046, 1706556, 1683826, 1675580, 1682604, 1688919],
// org=[2353667, 2350607, 2470536, 2563659, 2442757, 2450574, 2427763, 2427474, 2359808, 2367850, 2349704, 2343834],
// pl=[1468441, 1467106, 1467959, 1448542, 1459453, 1459499, 1456153, 1454823, 1455924, 1435127, 1457167, 1439388],
// ru=[2120257, 2138138, 2157392, 2133643, 2124843, 2114806, 2113160, 2133827, 2135776, 2124620, 2298064, 2276418],
// uk=[1203674, 1204786, 1205215, 1216818, 1210180, 1210489, 1190150, 1187390, 1167678, 1166558, 1168945, 1158117]
// }

        returnMap.put("data",dataMap);
//        System.out.println(returnMap);

        return returnMap;
    }
}
