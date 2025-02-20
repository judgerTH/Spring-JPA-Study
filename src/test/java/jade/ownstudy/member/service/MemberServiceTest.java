package jade.ownstudy.member.service;

import jade.ownstudy.member.entity.Member;
import jade.ownstudy.member.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Mock 객체 초기화
    }

    @Test
    @DisplayName("회원 저장 테스트")
    void saveMember() {
        // Given
        Member member = new Member(null, "홍길동", "hong@example.com", null);
        when(memberRepository.save(any(Member.class))).thenReturn(member);

        // When
        Member savedMember = memberService.saveMember(member);

        // Then
        assertThat(savedMember.getName()).isEqualTo("홍길동");
        verify(memberRepository, times(1)).save(member);
    }

    @Test
    @DisplayName("회원 조회 테스트")
    void getMemberById() {
        // Given
        Member member = new Member(1L, "홍길동", "hong@example.com", null);
        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));

        // When
        Optional<Member> foundMember = memberService.getMemberById(1L);

        // Then
        assertThat(foundMember).isPresent();
        assertThat(foundMember.get().getName()).isEqualTo("홍길동");
    }

    @Test
    @DisplayName("회원 삭제 테스트")
    void deleteMember() {
        // Given
        Long memberId = 1L;

        // When
        memberService.deleteMember(memberId);

        // Then
        verify(memberRepository, times(1)).deleteById(memberId);
    }
}