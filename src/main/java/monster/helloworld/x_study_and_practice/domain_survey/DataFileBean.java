package monster.helloworld.x_study_and_practice.domain_survey;

import java.io.File;

public class DataFileBean {

    private File dataFile = null;

    {
        // 构造块，初始化 dataFile 对象
        dataFile = new File(
                // System.getProperty("java.io.tmpdir") +
                Constant.DATA_FILE_PARENT
                        + File.separator
                        + Constant.DATA_FILE_NAME);
    }

    public File getDataFile() {
        if (!dataFile.getParentFile().exists()) {
            dataFile.getParentFile().mkdirs();
        }
        return dataFile;
    }

    // 2.声明本类类型的引用指向本类类型的对象，使用private static关键字修饰
    private static DataFileBean dataFileBean = null;               // 懒汉式
    // 1.私有化构造方法
    private DataFileBean() {
    }
    // 3.提供公有的get方法负责将对象返回出去，使用public static关键字修饰
    public static DataFileBean getInstance() {
        //return sin;
        if (dataFileBean == null) {
            dataFileBean = new DataFileBean();
        }
        return dataFileBean;
    }
}
