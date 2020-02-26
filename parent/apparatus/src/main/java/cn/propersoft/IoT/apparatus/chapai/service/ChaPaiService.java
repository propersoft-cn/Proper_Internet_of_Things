package cn.propersoft.IoT.apparatus.chapai.service;

import cn.propersoft.IoT.apparatus.analysis.vo.StatisticalAnalysisVO;
import cn.propersoft.IoT.apparatus.chapai.entity.ChaPaiEntity;

import java.util.Date;
import java.util.List;

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

    /**
     * 获取各楼层能耗分析柱状图数据
     * @param floor
     * @param startTime
     * @param endTime
     * @return
     */
    List<StatisticalAnalysisVO> getChaPaiHistogramData(String floor, String startTime, String endTime);
}
