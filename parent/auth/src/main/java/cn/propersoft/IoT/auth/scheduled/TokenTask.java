package cn.propersoft.IoT.auth.scheduled;

import cn.propersoft.IoT.auth.service.TokenService;
import cn.propersoft.IoT.auth.service.impl.TokenServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class TokenTask {
    private static final Logger logger = LoggerFactory.getLogger(TokenServiceImpl.class);
    @Autowired
    private TokenService tokenService;

    //一号的1点开始每隔三天执行一次
    @Scheduled(cron = "* * 1 1/3 * *")
    public void cleanToken() {
        logger.info("---------- clean token start ----------");
        tokenService.deleteAllToken();
        logger.info("---------- clean token end ----------");
    }


}
