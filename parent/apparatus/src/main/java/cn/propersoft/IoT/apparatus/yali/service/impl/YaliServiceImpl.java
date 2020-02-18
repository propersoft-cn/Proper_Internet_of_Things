package cn.propersoft.IoT.apparatus.yali.service.impl;


import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.propersoft.IoT.apparatus.yali.entity.YaliEntity;
import cn.propersoft.IoT.apparatus.yali.repository.YaliRepository;
import cn.propersoft.IoT.apparatus.yali.service.YaliService;
import cn.propersoft.IoT.exception.BizException;
import cn.propersoft.IoT.exception.CommonEnum;
import cn.propersoft.IoT.websocket.server.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class YaliServiceImpl implements YaliService {

    private static final Logger LOGGER = LoggerFactory.getLogger(YaliServiceImpl.class);
    @Autowired
    private YaliRepository yaliRepository;

    @Override
    public void getYaLiData(String deviceId) {
        boolean isStop = true;
        while (isStop) {
            List<YaliEntity> list = yaliRepository.findOneByOrderByAddTimeDesc();
            if (list.size() > 0) {
                YaliEntity yaliEntity = list.get(0);
                JSONObject jsonObject = JSONUtil.parseObj(yaliEntity);
                try {
                    //TODO 追加停止判断
                    //TODO
                    WebSocketServer.sendInfo(jsonObject.toString(), deviceId);
                    Thread.sleep(1000L);
                } catch (Exception e) {
                    throw new BizException(CommonEnum.BUSINESS_ERROR, e);
                }
            }
        }
    }
}
