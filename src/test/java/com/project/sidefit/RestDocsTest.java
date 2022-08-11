package com.project.sidefit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.sidefit.api.dto.response.Response;
import com.project.sidefit.api.dto.sign.EmailRequestDto;
import com.project.sidefit.domain.service.security.SignService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureRestDocs
public class RestDocsTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SignService signService;

    /*@Test
    @DisplayName("이메일 중복")
    public void checkEmail() throws Exception {
        //given
        Response response = Response.success();

        // TODO any() 부분에 email 형태로 정규식
        given(signService.validateDuplicatedEmail(any(String.class))).willReturn(true);


        //when
        EmailRequestDto request = EmailRequestDto.builder().email("test@gmail.com").build();
        ResultActions result = this.mockMvc.perform(get("/api/auth/email/check")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .accept(MediaType.APPLICATION_JSON)
        );

        //then
        result.andExpect(status().isOk())
                .andDo(document("check-email",
                        requestFields(
                                fieldWithPath("email").type(JsonFieldType.STRING).description("검사할 email")
                        ),
                        responseFields()
                        ))
    }

    @Test
    @DisplayName("login 테스트")
    public void login_test() throws Exception {
        //given


        //when

        //then
    }*/

    @Test
    @DisplayName("healthcheck api")
    public void healthcheck_테스트() throws Exception {

        ResultActions result = this.mockMvc.perform(get("/api/healthcheck")
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andDo(document("healthcheck", responseFields(
                                fieldWithPath("success").type(JsonFieldType.BOOLEAN).description("성공 유무"),
                                fieldWithPath("code").type(JsonFieldType.NUMBER).description("상태 코드"),
                                fieldWithPath("result.data").type(JsonFieldType.STRING).description("healthcheck msg")
                        ))
                );
    }
}
