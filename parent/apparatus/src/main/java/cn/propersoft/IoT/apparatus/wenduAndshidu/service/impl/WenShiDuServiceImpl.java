package cn.propersoft.IoT.apparatus.wenduAndshidu.service.impl;


import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.propersoft.IoT.apparatus.co2.entity.CO2Entity;
import cn.propersoft.IoT.apparatus.wenduAndshidu.entity.WenShiDuEntity;
import cn.propersoft.IoT.apparatus.wenduAndshidu.repository.WenShiDuRepository;
import cn.propersoft.IoT.apparatus.wenduAndshidu.service.WenShiDuService;
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
public class WenShiDuServiceImpl implements WenShiDuService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WenShiDuServiceImpl.class);
    @Autowired
    private WenShiDuRepository wenShiDuRepository;

    @Override
    public void getWenShiDuData(String deviceId) {
        boolean isStop = true;
        Sort sort = Sort.by(Sort.Direction.DESC, "addTime");
        PageRequest pageRequest = PageRequest.of(1, 1, sort);
        Pageable pageable = pageRequest.first();
        while (isStop) {
            Page<WenShiDuEntity> page = wenShiDuRepository.findAll(pageable);
            if (!page.isEmpty()) {
                WenShiDuEntity yaliEntity = page.toList().get(0);
                JSONObject jsonObject = JSONUtil.parseObj(yaliEntity);
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
    public WenShiDuEntity findOneByOrderByAddTimeDesc() {
        Sort sort = Sort.by(Sort.Direction.DESC, "addTime");
        PageRequest pageRequest = PageRequest.of(1, 1, sort);
        Pageable pageable = pageRequest.first();
        Page<WenShiDuEntity> page = wenShiDuRepository.findAll(pageable);
        if (!page.isEmpty()) {
            List<WenShiDuEntity> list = page.toList();
            return list.get(0);
        } else {
            throw new BizException(CommonEnum.BUSINESS_ERROR);
        }
    }
}
