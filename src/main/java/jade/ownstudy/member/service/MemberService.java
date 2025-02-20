package jade.ownstudy.member.service;

import jade.ownstudy.member.entity.Member;
import jade.ownstudy.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }
    // 전체 회원 조회
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    // 특정 회원 조회
    public Optional<Member> getMemberById(Long id) {
        return memberRepository.findById(id);
    }

    // 회원 삭제
    @Transactional
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }
}
