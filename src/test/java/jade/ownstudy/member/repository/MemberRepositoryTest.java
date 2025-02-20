package jade.ownstudy.member.repository;


import jade.ownstudy.member.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest  // JPA 관련 테스트를 위한 어노테이션 (H2 사용)
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("회원 저장 및 조회 테스트")
    void saveAndFindMember() {
        // Given
        Member member = Member.builder()
                .name("홍길동")
                .email("hong@example.com")
                .build();

        // When
        Member savedMember = memberRepository.save(member);
        Optional<Member> foundMember = memberRepository.findById(savedMember.getId());

        // Then
        assertThat(foundMember).isPresent();
        assertThat(foundMember.get().getName()).isEqualTo("홍길동");
        assertThat(foundMember.get().getEmail()).isEqualTo("hong@example.com");
    }

    @Test
    @DisplayName("전체 회원 조회 테스트")
    void findAllMembers() {
        // Given
        Member member1 = memberRepository.save(new Member(null, "홍길동", "hong@example.com", null));
        Member member2 = memberRepository.save(new Member(null, "이순신", "lee@example.com", null));

        // When
        List<Member> members = memberRepository.findAll();

        // Then
        assertThat(members).hasSize(2);
    }

    @Test
    @DisplayName("회원 삭제 테스트")
    void deleteMember() {
        // Given
        Member member = memberRepository.save(new Member(null, "홍길동", "hong@example.com", null));

        // When
        memberRepository.deleteById(member.getId());
        Optional<Member> deletedMember = memberRepository.findById(member.getId());

        // Then
        assertThat(deletedMember).isEmpty();
    }
}