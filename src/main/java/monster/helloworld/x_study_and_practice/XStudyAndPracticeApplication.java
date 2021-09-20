package monster.helloworld.x_study_and_practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
//@EnableWebMvc
public class XStudyAndPracticeApplication{
//public class XStudyAndPracticeApplication extends SpringBootServletInitializer {
//
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(XStudyAndPracticeApplication.class);
//    }


    public static void main(String[] args) {
        SpringApplication.run(XStudyAndPracticeApplication.class, args);
    }

}
