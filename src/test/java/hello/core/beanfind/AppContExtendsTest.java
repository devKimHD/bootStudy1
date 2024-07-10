package hello.core.beanfind;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AppContExtendsTest {
    AnnotationConfigApplicationContext ac= new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모 조회시 자식 둘 이상 있으면 중복 오류")
    void findBeanByParSonTypeDup()
    {
//
        assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(DiscountPolicy.class));
    }
    @Test
    @DisplayName("부모 조회시 자식 둘 빈이름 지정")
    void findBeanByParSonTypeName()
    {
//
        DiscountPolicy rateDisCountPolicy = ac.getBean("rateDisCountPolicy", DiscountPolicy.class);
        assertThat(rateDisCountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("특정 하위 타입조회")
    void findBeanBySub()
    {
//
        DiscountPolicy rateDisCountPolicy = ac.getBean(RateDiscountPolicy.class);
        assertThat(rateDisCountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모로 전부 조회")
    void findAllBeanByPar()
    {
//
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        assertThat(beansOfType.size()).isEqualTo(2);
        for(String key : beansOfType.keySet())
        {
            System.out.println("key =" + key + " value="+beansOfType.get(key));
        }
    }
    @Test
    @DisplayName("Obj 전부 조회")
    void findAllBeanByObj()
    {
//
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);

        for(String key : beansOfType.keySet())
        {
            System.out.println("key =" + key + " value="+beansOfType.get(key));
        }
    }
    @Configuration
    static class TestConfig
    {
        @Bean
        public DiscountPolicy rateDisCountPolicy()
        {
            return new RateDiscountPolicy();
        }
        @Bean
        public DiscountPolicy fixDisCountPolicy()
        {
            return new FixDiscountPolicy();
        }
    }
}
