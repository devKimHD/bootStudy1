package hello.core.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat.UUID;

@Component
@Scope(value = "request",proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {
    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }
    public void log(String msg)
    {
        System.out.println("["+uuid+"] ["+requestURL+"] ["+msg+"]");
    }
    @PostConstruct
    public void init()
    {
        uuid = java.util.UUID.randomUUID().toString();
        System.out.println("["+uuid+"] request scope been create "+this);
    }
    @PreDestroy
    public void close()
    {
        System.out.println("["+uuid+"] request scope been destroy "+this);
    }
}
