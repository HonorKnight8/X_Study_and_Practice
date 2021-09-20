package monster.helloworld.x_study_and_practice.domain_survey;

public class Constant {
    // 本地测试环境标记(win + IDEA)
//    public static final Boolean IS_LOCAL_TEST_ENV = true;
    public static final Boolean IS_LOCAL_TEST_ENV = false;

    public static final String DATA_FILE_PARENT = IS_LOCAL_TEST_ENV ? "D:\\test\\domain_survey" : "/templates/domain_survey";
    public static final String DATA_FILE_NAME = "dataFile.json";

    // http://www.securityspace.com/s_survey/data/202108/domain.html
    public static final String URL_PREFIX = "http://www.securityspace.com/s_survey/data/";
    public static final String URL_SUFFIX = "/domain.html";

}
