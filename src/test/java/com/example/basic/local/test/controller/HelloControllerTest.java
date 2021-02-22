package com.example.basic.local.test.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * MockMvc 이용한 test
 * MockMvc method
 * perform - 주어진 url 을 수행할 수 있는 환경을 구성,
 *         - POST,DELETE,PUT,GET 등 다양한 method 처리 가능
 *         - header 값 세팅, AcceptType 설정 지원
 *         - ex) mockMvc.perform(post("/v1/signin").params(params)
 * andDo - perform 요청을 처리, andDo(print()) 를 하면 처리 결과를 console 화면에서 볼 수 있음
 * andExpert - 검증 내용을 체크
 *           - 결과가 200 OK 인지 체크 : ex) andExpert(status().isOK())
 *           - 결과 데이터가 Json 인 경우 다음과 같이 체크 가능 : ex) andExpert(jsonPath("$.success").value(true))
 * andReturn - 테스트 완료 후 결과 객체를 받을 수 있음, 후속 작업이 필요할 때 용이
 *           - ex) MvcResult result = mockMvc.perform(post("/v1/signin").params(params)).andDo(print())
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class HelloControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void helloworldString() throws Exception {
        mockMvc.perform(get("/helloworld/string"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andExpect(content().string("helloworld"));
    }

    @Test
    void helloworldJson() throws Exception {
        mockMvc.perform(get("/helloworld/json"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=utf-8"))
                .andExpect(jsonPath("$.message").value("helloworld"));
    }

    @Test
    void helloworldPage() throws Exception {
        mockMvc.perform(get("/helloworld/page"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("helloworld"))
                .andExpect(content().string("helloworld"));
    }

}