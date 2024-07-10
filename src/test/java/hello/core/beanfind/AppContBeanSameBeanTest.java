package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AppContBeanSameBeanTest {
    AnnotationConfigApplicationContext ac= new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("타입 조회시 같은 타입 둘이면 중복 오류")
    void findBeanByTypeDup()
    {
//        MemberRepository memberRepository = ac.getBean(MemberRepository.class);
        assertThrows(NoUniqueBeanDefinitionException.class, () ->ac.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("타입 조회시 같은 타입 둘이면 빈이름 지정")
    void findBeanByTypeDupName()
    {
        MemberRepository memberRepository = ac.getBean("memberRepository1",MemberRepository.class);
        assertThat(memberRepository).isInstanceOf(MemberRepository.class);

    }
    @Test
    @DisplayName("특정 타입을 모두 조회")
    void findAllBeanByTypeDupName()
    {
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        for(String key : beansOfType.keySet())
        {
            System.out.println("key = " + key + " value =" + beansOfType.get(key));
        }
        System.out.println("beansOfType=" + beansOfType);
        assertThat(beansOfType.size()).isEqualTo(2);
    }
    @Configuration
    static class SameBeanConfig
    {
        @Bean
        public MemberRepository memberRepository1()
        {
            return new MemoryMemberRepository();
        }
        @Bean
        public MemberRepository memberRepository2()
        {
            return new MemoryMemberRepository();
        }
    }
}
