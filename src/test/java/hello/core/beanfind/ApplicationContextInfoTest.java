package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {
    AnnotationConfigApplicationContext ac= new AnnotationConfigApplicationContext(AppConfig.class);
    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean()
    {
        String[] beanDefintionNames = ac.getBeanDefinitionNames();
        for (String beanDefintionName : beanDefintionNames)
        {
            Object bean = ac.getBean(beanDefintionName);
            System.out.println("name =" + beanDefintionName + " object" + bean);
        }
    }
    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean()
    {
        String[] beanDefintionNames = ac.getBeanDefinitionNames();
        for (String beanDefintionName : beanDefintionNames)
        {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefintionName);
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) //내가 등록한 빈
//            if (beanDefinition.getRole() == BeanDefinition.ROLE_INFRASTRUCTURE) //컨테이너가 등록한 빈
            {
                Object bean = ac.getBean(beanDefintionName);
                System.out.println("name =" + beanDefintionName + " object" + bean);
            }
                    }
    }
}
