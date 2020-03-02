package cn.propersoft.IoT.apparatus.co2.service;

import cn.propersoft.IoT.apparatus.co2.entity.CO2Entity;

public interface CO2Service {
    void getCo2Data(String deviceId);

    CO2Entity findOneByOrderByAddTimeDesc();
}
