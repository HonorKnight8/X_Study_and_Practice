package monster.helloworld.x_study_and_practice.domain_survey;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.TreeMap;

@Controller
@RequestMapping("/domain_survey")
public class DomainSurveyController {
    @RequestMapping("/entry")
    public ModelAndView entry(){

        ModelAndView modelAndView = new ModelAndView();
        // 添加 model 数据
        // modelAndView.addObject("test", "testWhat");

        // 设置视图名称，逻辑视图
        modelAndView.setViewName("domain_survey");

        return modelAndView;

    }


    @RequestMapping("/get_data")
    public void responseString(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){

        // 设置编码
        try {
            httpServletRequest.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        httpServletResponse.setContentType("text/html;charset=utf-8");

        // 接收前端提交的数据
        System.out.println("___接收到客户端发送过来的信息：" + httpServletRequest.getParameter("what"));

        // 执行获取数据的业务逻辑
        TreeMap treeMap = DataFileDao.getData();

        // 将读取到的map进行修剪后
        Map<String, Object> map = DomainSurveyService.clipData(treeMap);
        map.put("flag",true); // 添加 flag = true 元素


        // 把 map 转成 json ，最后返回给前端
        ObjectMapper om = new ObjectMapper();
        String mapJson = null;
        PrintWriter out = null;

        try {
            // map转json
            mapJson = om.writeValueAsString(map);
            out = httpServletResponse.getWriter();
            // System.out.println(mapJson);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 返回给前端
        out.println(mapJson);
    }

}
