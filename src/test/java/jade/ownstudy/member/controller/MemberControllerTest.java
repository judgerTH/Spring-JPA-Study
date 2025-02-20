package jade.ownstudy.member.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import jade.ownstudy.member.entity.Member;
import jade.ownstudy.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MemberController.class)  // Controller 테스트용 어노테이션
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MemberService memberService;

    @Test
    @DisplayName("회원 생성 API 테스트")
    void createMember() throws Exception {
        // Given
        Member member = new Member(1L, "홍길동", "hong@example.com", null);
        when(memberService.saveMember(any(Member.class))).thenReturn(member);

        // When & Then
        mockMvc.perform(post("/api/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(member)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("홍길동"))
                .andExpect(jsonPath("$.email").value("hong@example.com"));
    }

    @Test
    @DisplayName("회원 조회 API 테스트")
    void getMemberById() throws Exception {
        // Given
        Member member = new Member(1L, "홍길동", "hong@example.com", null);
        when(memberService.getMemberById(1L)).thenReturn(Optional.of(member));

        // When & Then
        mockMvc.perform(get("/api/members/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("홍길동"));
    }

    @Test
    @DisplayName("회원 삭제 API 테스트")
    void deleteMember() throws Exception {
        // When & Then
        mockMvc.perform(delete("/api/members/1"))
                .andExpect(status().isNoContent());
    }
}