package controller;

import com.lanmo.controller.StudentController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan(value = "com.lanmo")
@WebAppConfiguration
public class TestStudentController {

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        StudentController studentController = new StudentController();
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
    }


    @Test
    public void testStudentController(){

        MvcResult mvcResult = null;
        try {
            mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/hello"))
                    .andExpect(MockMvcResultMatchers.status().is(200))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();

            int status = mvcResult.getResponse().getStatus();
            System.out.println("请求状态码：" + status);
            String result = mvcResult.getResponse().getContentAsString();
            System.out.println("接口返回结果：" + result);

//            JSONObject resultObj =
            // 判断接口返回json中success字段是否为true
//            Assert.assertTrue(resultObj.getBooleanValue("success"));

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
