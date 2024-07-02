package hello.core.discount;

public class FixDiscountPolicy implements DiscountPolicy{

        private int discountFixAmount = 1000; // 1000원 할인

        @Override
        public int discount(hello.core.member.Member member, int price) {
            if (member.getGrade() == hello.core.member.Grade.VIP) {
                return discountFixAmount;
            }
            else
            {
                return 0;
            }
        }
}
