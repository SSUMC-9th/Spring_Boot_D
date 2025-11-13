package com.umc9th.peter.domain.test.controller;

import com.umc9th.peter.domain.test.exception.code.TestErrorCode;
import com.umc9th.peter.global.api.code.GeneralSuccessCode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class TestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void test() throws Exception {
        mockMvc.perform(get("/temp/test"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isSuccess").value(true))
                .andExpect(jsonPath("$.code").value(GeneralSuccessCode.OK.getCode()))
                .andExpect(jsonPath("$.message").value(GeneralSuccessCode.OK.getMessage()))
                .andExpect(jsonPath("$.result.testing").isString())
                .andExpect(jsonPath("$.result.testing").isNotEmpty());
    }

    @Test
    void testException() throws Exception {
        mockMvc.perform(get("/temp/exception")
                        .param("flag", "0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isSuccess").value(true))
                .andExpect(jsonPath("$.code").value(GeneralSuccessCode.OK.getCode()))
                .andExpect(jsonPath("$.message").value(GeneralSuccessCode.OK.getMessage()))
                .andExpect(jsonPath("$.result.testing").isString())
                .andExpect(jsonPath("$.result.testing").isNotEmpty());

        TestErrorCode testExceptionCode = TestErrorCode.TEST_EXCEPTION;
        mockMvc.perform(get("/temp/exception")
                        .param("flag", "1"))
                .andExpect(status().is(testExceptionCode.getStatus().value()))
                .andExpect(jsonPath("$.isSuccess").value(false))
                .andExpect(jsonPath("$.code").value(testExceptionCode.getCode()))
                .andExpect(jsonPath("$.message").value(testExceptionCode.getMessage()))
                .andExpect(jsonPath("$.result").isString())
                .andExpect(jsonPath("$.result").isNotEmpty());
    }

}
