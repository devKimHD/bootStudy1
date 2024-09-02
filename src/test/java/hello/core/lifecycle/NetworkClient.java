package hello.core.lifecycle;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class NetworkClient  {
    private String url;

    public NetworkClient()
    {
        System.out.println("NetworkClient.NetworkClient url" +url);
        connect();
        call("init msg");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void connect(){
        System.out.println("NetworkClient.connect url" + url);

    }
    public void call(String message)
    {
        System.out.println("NetworkClient.call msg"+url+","+message);
    }
    public void disconnect(){
        System.out.println("NetworkClient.disconnect close");
    }

    @PostConstruct
    public void init() throws Exception {
        System.out.println("NetworkClient.init");
        connect();
        call("init afterPropertiesSet msg");
    }

    @PreDestroy
    public void close() throws Exception {
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
