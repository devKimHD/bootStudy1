package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
//        basePackages = "hello.core.member",
//        basePackageClasses = AutoAppConfig.class)
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes =
                Configuration.class))
//AppConfig 제외하려고
//지정 안할시 내위치 기준 + 하위 패키지 까지
public class AutoAppConfig
{
//    @Bean(name="memoryMemberRepository")
//    MemberRepository memberRepository()
//    {
//        return new MemoryMemberRepository();
//    }
}
