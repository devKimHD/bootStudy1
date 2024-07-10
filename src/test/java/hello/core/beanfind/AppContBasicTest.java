package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class AppContBasicTest {
    AnnotationConfigApplicationContext ac= new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름 조회")
    void findBeanByName()
    {
        MemberService memberService = ac.getBean("memberService",MemberService.class);
//        System.out.println("memberService "+ memberService);
//        System.out.println("memberService.getClass() "+ memberService.getClass());
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("이름 없이 타입으로 조회")
    void findBeanByType()
    {
        MemberService memberService = ac.getBean(MemberService.class);
//        System.out.println("memberService "+ memberService);
//        System.out.println("memberService.getClass() "+ memberService.getClass());
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입")
    void findBeanByName2()
    {
        //좋은 코드는 아님
        MemberService memberService = ac.getBean("memberService",MemberServiceImpl.class);
//        System.out.println("memberService "+ memberService);
//        System.out.println("memberService.getClass() "+ memberService.getClass());
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름 조회X")
    void findBeanByNameX()
    {
        //ac.getBean("x",memberService.class)
        
//        System.out.println("memberService "+ memberService);
//        System.out.println("memberService.getClass() "+ memberService.getClass());
        //예외 처리 되어야 성공
        assertThrows(NoSuchBeanDefinitionException.class
                , () -> ac.getBean("xxx",MemberService.class));
    }
}
