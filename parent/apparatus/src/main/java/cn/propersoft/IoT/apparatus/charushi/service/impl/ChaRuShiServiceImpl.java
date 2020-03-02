package cn.propersoft.IoT.apparatus.charushi.service.impl;


import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.propersoft.IoT.apparatus.chapai.entity.ChaPaiEntity;
import cn.propersoft.IoT.apparatus.charushi.entity.ChaRuShiEntity;
import cn.propersoft.IoT.apparatus.charushi.repository.ChaRuShiRepository;
import cn.propersoft.IoT.apparatus.charushi.service.ChaRuShiService;
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
public class ChaRuShiServiceImpl implements ChaRuShiService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChaRuShiServiceImpl.class);
    @Autowired
    private ChaRuShiRepository chaRuShiRepository;

    @Override
    public void getChaRuShiData(String deviceId) {
        boolean isStop = true;
        Sort sort = Sort.by(Sort.Direction.DESC, "addTime");
        PageRequest pageRequest = PageRequest.of(1, 1, sort);
        Pageable pageable = pageRequest.first();
        while (isStop) {
            Page<ChaRuShiEntity> page = chaRuShiRepository.findAll(pageable);
            if (!page.isEmpty()) {
                ChaRuShiEntity entity = page.toList().get(0);
                JSONObject jsonObject = JSONUtil.parseObj(entity);
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
    public ChaRuShiEntity findOneByOrderByAddTimeDesc() {

        Sort sort = Sort.by(Sort.Direction.DESC, "addTime");
        PageRequest pageRequest = PageRequest.of(1, 1, sort);
        Pageable pageable = pageRequest.first();
        Page<ChaRuShiEntity> page = chaRuShiRepository.findAll(pageable);
        if (!page.isEmpty()) {
            List<ChaRuShiEntity> list = page.toList();
            return list.get(0);
        } else {
            throw new BizException(CommonEnum.BUSINESS_ERROR);
        }
    }
}
