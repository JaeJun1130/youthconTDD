package com.youthcon.tdd;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * TDD지 를 작성하므로 개발자가 혼자만의 상상으로 오버코딩을 안하게 되는 장점이있음
 * 또 TDD 를 먼저 작성해서 현재 필요한 서비스 로직 뭐가 필요한 순차대로 작성 할 수있음.
 */
@WebMvcTest
public class ReviewControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private ReviewService reviewService;

    @Test
    void 후기_조회_성공() throws Exception {
        //given

        //when
        ResultActions perform = mockMvc.perform(get("/reviews/1"));

        //then
        perform
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("content").value("재밌어요"))
                .andExpect(jsonPath("phone").value("010-1111-2222"));
    }

}
