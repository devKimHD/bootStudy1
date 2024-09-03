package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Provider;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class SingletonWithPrototypeTest1 {
    @Test
    void prototypeFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean bean = ac.getBean(PrototypeBean.class);
        bean.addCount();
        Assertions.assertThat(bean.getCount()).isEqualTo(1);
        PrototypeBean bean2 = ac.getBean(PrototypeBean.class);
        bean2.addCount();
        Assertions.assertThat(bean2.getCount()).isEqualTo(1);
    }

    @Test
    void SingletonClientUsePrototype()
    {

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class,PrototypeBean.class);
        ClientBean cbean1 = ac.getBean(ClientBean.class);
        int cnt1=cbean1.logic();
        Assertions.assertThat(cnt1).isEqualTo(1);
        ClientBean cbean2 = ac.getBean(ClientBean.class);
        int cnt2=cbean2.logic();
        Assertions.assertThat(cnt2).isEqualTo(1);
    }
    @Scope("singleton")
    static class ClientBean
    {
//        private final PrototypeBean prototypeBean;

        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider;
//        @Autowired
//        ApplicationContext applicationContext;
//        @Autowired
//        public ClientBean(PrototypeBean prototypeBean)
//        {
//            this.prototypeBean = prototypeBean;
//        }
        public int logic() {
//            PrototypeBean prototypeBean = applicationContext.getBean(PrototypeBean.class);
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }
    @Scope("prototype")
    static class PrototypeBean
    {
        private int count = 0;
        public void addCount(){count ++;}
        public int getCount(){return count;}

        @PostConstruct
        public void init()
        {
            System.out.println("PrototypeBean.init "+this);
        }
        @PreDestroy
        public void destroy()
        {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
