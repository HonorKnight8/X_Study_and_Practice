package monster.helloworld.x_study_and_practice.domain_survey;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Spider {

    public static ArrayList<String[]> htmlTableToList(String url){
        ArrayList<String[]> trList = new ArrayList<>();

        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        System.out.println(doc.title());

        Element table = doc.select("table").last();
        // System.out.println(table);
        Element tbody = table.select("tbody").first();
        Elements trs = tbody.select("tr");

        for (Element tr : trs) {
            Elements tds = tr.select("td");
            int i = 0;
            String[] tdsArray = new String[4];
            for (Element td : tds) {
                // text()方法就是返回调用标签元素中的文本内容，返回的是String类型
                tdsArray[i] = td.text();
                i++;
            }
            if(tdsArray[0] != null){
                trList.add(tdsArray);
            }

        }
        return trList;
    }
}
