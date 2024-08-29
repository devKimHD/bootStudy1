package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
//final 붙은 파라미터로 생성자 생성
public class OrderServiceImpl implements OrderService
{
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository ;
    private final DiscountPolicy discountPolicy;

//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();



//    @Autowired //선택적 주입
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//        System.out.println("memberRepository"+memberRepository);
//    }
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//        System.out.println("discountPolicy"+discountPolicy);
//    }
//생성자 하나면 생략 가능
    @Autowired

    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy)
    {
        System.out.println("OrderServiceImpl"+memberRepository);
//        System.out.println("discountPolicy"+discountPolicy);
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
//    @Autowired
//    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy)
//    {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
    public MemberRepository getMemberRepository()
    {
        return memberRepository;
    }
}
