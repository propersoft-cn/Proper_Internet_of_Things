package cn.propersoft.IoT.apparatus.wenduAndshidu.service;

import cn.propersoft.IoT.apparatus.wenduAndshidu.entity.WenShiDuEntity;

public interface WenShiDuService {
    void getWenShiDuData(String deviceId);

    WenShiDuEntity findOneByOrderByAddTimeDesc();
}
