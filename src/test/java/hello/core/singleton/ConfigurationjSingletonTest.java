package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationjSingletonTest {
    @Test
    void configurationTest()
    {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository_ori = ac.getBean("MemberRepository", MemberRepository.class);
        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();
        System.out.println("memberRepository1"+memberRepository1);
        System.out.println("memberRepository2"+memberRepository2);
        System.out.println("memberRepository_ori"+memberRepository_ori);

        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberRepository_ori);
        Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memberRepository_ori);


    }

    @Test
    void configurationDeep()
    {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);
        System.out.println("been ="+bean.getClass());

    }
}
