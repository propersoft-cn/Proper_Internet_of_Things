package cn.propersoft.IoT.apparatus.yeweizhi.service;

import cn.propersoft.IoT.apparatus.yeweizhi.entity.YeWeiZhiEntity;

public interface YeWeiZhiService {
    void getYeWeiZhiData(String deviceId);

    YeWeiZhiEntity findOneByOrderByAddTimeDesc();
}
