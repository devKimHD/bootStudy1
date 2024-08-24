package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefullServiceTest {
    @Test
    void statefullSingleton()
    {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefullService statefullService1 = ac.getBean("statefullService", StatefullService.class);
        StatefullService statefullService2 = ac.getBean("statefullService", StatefullService.class);

        //Thread A
        int usrA_p =statefullService1.order("usrA",10000);
        //Thread B
        int usrB_p = statefullService2.order("usrB",20000);

        // thread a 금액 조회

        System.out.println("usrA_p = "+ usrA_p);
        System.out.println("usrB_p = "+ usrB_p);
        Assertions.assertThat(statefullService1.getPrice()).isEqualTo(20000);
    }
    static class TestConfig
    {
        @Bean
        public StatefullService statefullService()
        {
            return new StatefullService();
        }
    }

}