package com.youthcon.tdd;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * TDD 를 작성하므로 개발자가 혼자만의 상상으로 오버코딩을 안하게 되는 장점이있음
 * 또 TDD 를 먼저 작성해서 현재 필요한 서비스 로직 뭐가 필요한 순차대로 작성 할 수있음.
 */

//스프링 의존
//@AutoConfigureMockMvc
//@SpringBootTest

//해당 컨트롤러에 정확한 테스트를 진행하기 위한 명시
@WebMvcTest(ReviewController.class)
public class ReviewControllerTest {
    @Autowired
    private MockMvc mockMvc;

    //주입받았다고 가정함(가짜주입)
    @MockBean
    private ReviewService reviewService;

    private Long id = 1L;
    private String content = "재밌어요";
    private String phoneNumber = "010-1111-2222";


    @Test
    void 후기_조회_성공() throws Exception {
        //given
        given(reviewService.getById(id)).willReturn(new Review(id, content, phoneNumber));

        //when
        ResultActions perform = mockMvc.perform(get("/reviews/" + id));

        //then
        perform
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(id))
                .andExpect(jsonPath("content").value(content))
                .andExpect(jsonPath("phoneNumber").value(phoneNumber));
    }

    @Test
    void 후기_조회_실패() throws Exception {
        //given
        given(reviewService.getById(10000L)).willThrow(new ReviewNotFountException("no review id : " + id));

        //when
        ResultActions perform = mockMvc.perform(get("/reviews/" + 10000));

        //then
        perform
                .andExpect(status().isNotFound());
    }

    @Test
    void 선물하기() throws Exception {
        //given
        given(reviewService.sendGift(id)).willReturn(new Review(id, content, phoneNumber));

        //when
        ResultActions perform = mockMvc.perform(put("/reviews/" + id));

        //then
        perform
                .andExpect(status().isOk())
                .andExpect(jsonPath("sent").value(true));
    }

}
