package cn.propersoft.IoT.test.utils;

import cn.propersoft.IoT.exception.BizException;
import cn.propersoft.IoT.exception.CommonEnum;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
public class AddressUtils implements ApplicationListener<WebServerInitializedEvent> {

    private int serverPort;

    public int getServerPort() {
        return serverPort;
    }

    public String getUrl() {
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new BizException(CommonEnum.BUSINESS_ERROR, e);
        }
        assert address != null;
        return "http://" + address.getHostAddress() + ":" + this.serverPort + "/";
    }

    public String getHost() {
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new BizException(CommonEnum.BUSINESS_ERROR, e);
        }
        return address.getHostAddress();
    }

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        serverPort = event.getWebServer().getPort();
    }
}
