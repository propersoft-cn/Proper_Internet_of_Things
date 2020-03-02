package cn.propersoft.IoT.apparatus.charushi.service;

import cn.propersoft.IoT.apparatus.charushi.entity.ChaRuShiEntity;

public interface ChaRuShiService {
    void getChaRuShiData(String deviceId);

    ChaRuShiEntity findOneByOrderByAddTimeDesc();
}
