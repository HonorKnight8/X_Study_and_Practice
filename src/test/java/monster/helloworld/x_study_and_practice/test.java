package monster.helloworld.x_study_and_practice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import monster.helloworld.x_study_and_practice.domain_survey.Constant;
import monster.helloworld.x_study_and_practice.domain_survey.DataFileBean;
import monster.helloworld.x_study_and_practice.domain_survey.DataFileDao;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class test {

    @Test
    public void test6() throws JsonProcessingException {
        TreeMap<String, Object> treeMap = DataFileDao.downloadData(2);
//        System.out.println(treeMap);

        ObjectMapper om = new ObjectMapper();

        // map转json
        String jsonStr  = om.writeValueAsString(treeMap);
        System.out.println(jsonStr);
    }

    @Test
    public void test5() {
//        TreeMap treeMap = DataFileDao.readData();
//        System.out.println(treeMap);
//        System.out.println(DataFileBean.getInstance().getDataFile().getPath());

        DataFileDao.getData();

        TreeMap<String, Object> treeMap = DataFileDao.readJsonFileToMap();
        System.out.println(treeMap.firstKey());
        System.out.println(treeMap.lastKey());
        System.out.println(treeMap.keySet());

    }

    @Test
    public void test4() {

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMM");
        String str = dateTimeFormatter.format(now);
        System.out.println(str);
        for (int i = 12; i > 0; i--) {
            System.out.println(dateTimeFormatter.format(now.plusMonths(-i)));
        }


    }

    @Test
    public void test3() throws FileNotFoundException {
        System.out.println(ResourceUtils.getURL("classpath:").getPath());
        System.out.println("------");

//        System.out.println(ResourceUtils.CLASSPATH_URL_PREFIX);
//        System.out.println(ResourceUtils.FILE_URL_PREFIX);
//        System.out.println(ResourceUtils.JAR_FILE_EXTENSION);
//        System.out.println(ResourceUtils.JAR_URL_PREFIX);
//        System.out.println(ResourceUtils.JAR_URL_SEPARATOR);
//        System.out.println(ResourceUtils.URL_PROTOCOL_FILE);
//        System.out.println(ResourceUtils.URL_PROTOCOL_JAR);
//        System.out.println(ResourceUtils.URL_PROTOCOL_VFS);
//        System.out.println(ResourceUtils.URL_PROTOCOL_VFSFILE);
//        System.out.println(ResourceUtils.URL_PROTOCOL_VFSZIP);
//        System.out.println(ResourceUtils.URL_PROTOCOL_WAR);
//        System.out.println(ResourceUtils.URL_PROTOCOL_WSJAR);
//        System.out.println(ResourceUtils.URL_PROTOCOL_ZIP);
//        System.out.println(ResourceUtils.WAR_URL_PREFIX);
//        System.out.println(ResourceUtils.WAR_URL_SEPARATOR);
//        System.out.println("------");

        System.out.println(System.getProperty("java.io.tmpdir"));
        System.out.println(DataFileBean.getInstance().getDataFile().getParentFile());
        System.out.println(DataFileBean.getInstance().getDataFile().getParentFile().exists());

        System.out.println(DataFileBean.getInstance().getDataFile().getPath());
        System.out.println(DataFileBean.getInstance().getDataFile().exists());
    }

    @Test
    public void test1() {
        HttpURLConnection urlConnection = null;
        BufferedReader br = null;

        try {
            // 1.使用参数指定的字符串来构造对象
            URL url = new URL("http://10.10.10.20/test.html");
//                // 2.获取相关信息并打印出来
//                System.out.println("获取到的协议名称是：" + url.getProtocol());
//                System.out.println("获取到的主机名称是：" + url.getHost());
//                System.out.println("获取到的端口号是：" + url.getPort());
            // 3.建立连接并读取相关信息打印出来
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            br = new BufferedReader(new InputStreamReader(inputStream));
            String str = null;
            while ((str = br.readLine()) != null) {
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 断开连接
            urlConnection.disconnect();
        }

    }

    @Test
    public void test2() throws IOException {
        StringBuilder tableSB = new StringBuilder();

        ArrayList<String[]> trList = new ArrayList<>();

        Document doc = Jsoup.connect("http://10.10.10.20/test.html").get();
        System.out.println(doc.title());
//        for (Element allElement : doc.getAllElements()) {
//            System.out.println(allElement);
//        }

//        for (Element table : doc.getElementsMatchingOwnText("table")) {
//            System.out.println(table);
//        }

//        for (Element table : doc.getElementsContainingText("table")) {
//            System.out.println(table);
//        }

        Element table = doc.select("table").last();
        // System.out.println(element);
        Element tbody = table.select("tbody").first();
        Elements trs = tbody.select("tr");


        for (Element tr : trs) {
            Elements tds = tr.select("td");
            int i = 0;
            String[] tdsArray = new String[4];
            for (Element td : tds) {
                // text()方法局势返回调用标签元素中的文本内容，返回的是String类型
//                tableSB.append(td.text() + "|");
                tdsArray[i] = td.text();
                i++;
            }
//            tableSB.append("\n");
            if (tdsArray[0] != null) {
                trList.add(tdsArray);
            }

        }
//        System.out.println(tableSB);
//        System.out.println(trList);
        ObjectMapper om = new ObjectMapper();
        String listJson = null;

        // map转json
        listJson = om.writeValueAsString(trList);
        System.out.println(listJson);

        System.out.println(trList.get(0)[0]);

    }

}
