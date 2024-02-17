package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional // 트랜잭션 안에서 데이터 변경하는 것은 트랜잭션이 꼭 있어야 한다.
@RequiredArgsConstructor
public class MemberService {

    // setter 인젝션 주입방법: 보통 애플리케이션 로딩 시점에 조립이 끝나고 수정하지 않기 때문에 불필요함.
    // 생성자 인젝션 주입방법: 테스트 케이스 등을 작성할 때 컴파일로 주입을 알려줌, 코드가 김
    // + 생성자가 하나만 있는 경우에는 @Autowired 어노테이션을 붙이지 않아도 스프링이 자동으로 인젝션 해줌.
    // final : 생성자 값 세팅을 컴파일에서 확인할 수 있다.
    // @RequiredArgsConstructor를 사용하면 final 있는 필드만 가지고 생성자를 만들어줌.
    final private MemberRepository memberRepository;

    //회원 가입
    public Long join(Member member){
        validateDuplicateMember(member);

        memberRepository.join(member);
        return member.getId(); // persist 시점에 id도 생성해줌.
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
    // was가 동시에 여러개 있는 상황에선 동시에 검사를 통과하여 같은 이름이 저장될 수 있다.
    // 따라서, 멀티쓰레드를 고려하여 이름을 unique로 제약조건을 넣는 등의 최후방어를 설정해야함.

    // 회원 전체 조회
    // 읽기에는 (readOnly = true)를 넣으면 Db에 따라 리소스를 적게 쓴다. (default: false)
    @Transactional(readOnly = true)
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
    @Transactional(readOnly = true)
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

}
