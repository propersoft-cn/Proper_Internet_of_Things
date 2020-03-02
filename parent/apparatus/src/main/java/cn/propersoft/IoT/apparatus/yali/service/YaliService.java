package cn.propersoft.IoT.apparatus.yali.service;

import cn.propersoft.IoT.apparatus.yali.entity.YaliEntity;

public interface YaliService {
    void getYaLiData(String deviceId);

    YaliEntity findOneByOrderByAddTimeDesc();
}
