package com.devaon.managecontact.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@SpringBootTest
class HelloWorldControllerTest {

    @Autowired
    private HelloWorldController helloWorldController;

    //모의 http request, response 만들어 테스트 진행하도록 해줌
    private MockMvc mockMvc;

    //단지 메소드 호출 테스트
    @Test
    public void helloWorld() {
        System.out.println(helloWorldController.helloWord());

        assertThat(helloWorldController.helloWord()).isEqualTo("HelloWorld");
    }

    //실제 http 테스트
    @Test
    public void mockMvcHelloWorld() throws Exception {

        //MockMvc setting
        mockMvc = MockMvcBuilders.standaloneSetup(helloWorldController).build();

        //실제 동작하도록 요청
        mockMvc.perform(
                //get 메소드로 요청
                MockMvcRequestBuilders.get("/api/helloworld")
        ).andDo(MockMvcResultHandlers.print())
         .andExpect(MockMvcResultMatchers.status().isOk())
         .andExpect(MockMvcResultMatchers.content().string("HelloWorld"));
    }
}