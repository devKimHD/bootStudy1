package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@MainDiscountPolicy
// 컴파일 오류(오타) 잡기 위해 유용
public class RateDiscountPolicy implements DiscountPolicy {
    private int discountPercent = 10;
    // ctrl + shift + o to import
    // ctrl + shift + t to navigate to test
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
