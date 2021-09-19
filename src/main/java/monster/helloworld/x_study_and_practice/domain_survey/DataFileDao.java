package monster.helloworld.x_study_and_practice.domain_survey;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.TreeMap;

public class DataFileDao {
    private static File dataFile = DataFileBean.getInstance().getDataFile();

    private static ObjectMapper objMapper = new ObjectMapper();
    private static LocalDateTime now = LocalDateTime.now();
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMM");

    public static TreeMap<String, Object> getData(){

        TreeMap<String, Object> dataMap;

        if (!dataFile.exists()){
            // 文件不存在，爬取 12 个月数据
            dataMap = downloadData(12);
            // 缓存到本地数据文件
            writeMapToJsonFile(dataMap);
        }


        // 读取文件
        dataMap = readJsonFileToMap();

        String lastUpdatedMonth = dataMap.lastKey();
        int outdatedMonths = Integer.parseInt(dateTimeFormatter.format(now)) - Integer.parseInt(lastUpdatedMonth);
        if (outdatedMonths>1){
            // 判断最后更新月份，爬取缺失的月份数据
            dataMap.putAll(downloadData(outdatedMonths-1));
            // 缓存到本地数据文件
            writeMapToJsonFile(dataMap);
        }

        return dataMap;
    }

    public static TreeMap<String, Object> downloadData(int downloadMonths){
        TreeMap<String, Object> newDataMap = new TreeMap<>();

        for (int i = downloadMonths; i > 0; i--) {
//                System.out.println(dateTimeFormatter.format(now.plusMonths(-i)));
            String urlDate = dateTimeFormatter.format(now.plusMonths(-i));
            ArrayList<String[]> trList = Spider.htmlTableToList(Constant.URL_PREFIX+urlDate+Constant.URL_SUFFIX);
            newDataMap.put(urlDate,trList);
        }

        return newDataMap;
    }

    public static TreeMap<String, Object> readJsonFileToMap(){
        TreeMap<String, Object> readDataMap = new TreeMap<>();

// 另一种实现
//        try {
//            JsonNode rootNode = objMapper.readTree(new File(dataFile.getPath()));
//            String jsonStr = rootNode.toString();
//            readDataMap = objMapper.readValue(jsonStr, TreeMap.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try {
            readDataMap = objMapper.readValue(dataFile, TreeMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 返回 TreeMap

        return readDataMap;
    }

    private static void writeMapToJsonFile(TreeMap dataMap) {
        // 将传入的 TreeMap 写入 json 文件（覆盖）

        // map 转 json 写入文件
// 另一种实现
//        try {
//            FileOutputStream fileOutputStream = new FileOutputStream(dataFile.getPath());
//            fileOutputStream.write(objMapper.writeValueAsBytes(dataMap));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try {
            objMapper.writeValue(dataFile, dataMap);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
