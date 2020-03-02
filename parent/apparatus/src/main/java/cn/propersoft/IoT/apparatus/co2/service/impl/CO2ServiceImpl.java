package cn.propersoft.IoT.apparatus.co2.service.impl;


import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.propersoft.IoT.apparatus.chapai.entity.ChaPaiEntity;
import cn.propersoft.IoT.apparatus.co2.entity.CO2Entity;
import cn.propersoft.IoT.apparatus.co2.repository.CO2Repository;
import cn.propersoft.IoT.apparatus.co2.service.CO2Service;
import cn.propersoft.IoT.exception.BizException;
import cn.propersoft.IoT.exception.CommonEnum;
import cn.propersoft.IoT.websocket.server.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CO2ServiceImpl implements CO2Service {

    private static final Logger LOGGER = LoggerFactory.getLogger(CO2ServiceImpl.class);
    @Autowired
    private CO2Repository co2Repository;

    @Override
    public void getCo2Data(String deviceId) {
        boolean isStop = true;
        Sort sort = Sort.by(Sort.Direction.DESC, "addTime");
        PageRequest pageRequest = PageRequest.of(1, 1, sort);
        Pageable pageable = pageRequest.first();
        while (isStop) {
            Page<CO2Entity> page = co2Repository.findAll(pageable);
            if (!page.isEmpty()) {
                CO2Entity co2Entity = page.toList().get(0);
                JSONObject jsonObject = JSONUtil.parseObj(co2Entity);
                try {
                    //TODO 追加停止判断
                    WebSocketServer.sendInfo(jsonObject.toString(), deviceId);
                    Thread.sleep(1000L);
                } catch (Exception e) {
                    throw new BizException(CommonEnum.BUSINESS_ERROR, e);
                }
            }
        }
    }

    @Override
    public CO2Entity findOneByOrderByAddTimeDesc() {
        Sort sort = Sort.by(Sort.Direction.DESC, "addTime");
        PageRequest pageRequest = PageRequest.of(1, 1, sort);
        Pageable pageable = pageRequest.first();
        Page<CO2Entity> page = co2Repository.findAll(pageable);
        if (!page.isEmpty()) {
            List<CO2Entity> list = page.toList();
            return list.get(0);
        } else {
            throw new BizException(CommonEnum.BUSINESS_ERROR);
        }
    }
}
