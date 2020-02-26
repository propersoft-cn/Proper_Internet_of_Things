package cn.propersoft.IoT.apparatus.chapai.service;

import cn.propersoft.IoT.apparatus.chapai.entity.ChaPaiEntity;

import java.util.Date;

public interface ChaPaiService {

    void getChaPaiData(String deviceId);

    /**
     * 楼层能耗分析
     * @param floor
     * @param startTime
     * @param endTime
     * @return
     */
    ChaPaiEntity getFloorData(String floor, Date startTime, Date endTime);
}
