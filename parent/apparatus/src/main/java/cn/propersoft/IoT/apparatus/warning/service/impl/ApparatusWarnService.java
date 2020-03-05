package cn.propersoft.IoT.apparatus.warning.service.impl;

import cn.propersoft.IoT.apparatus.warning.entity.ApparatusWarnEntity;
import cn.propersoft.IoT.apparatus.warning.vo.ApparatusWarnVO;

import java.util.List;

public interface ApparatusWarnService {


    List<ApparatusWarnEntity> saveAll(List<ApparatusWarnEntity> apparatusWarnEntityList);

    List<ApparatusWarnVO> getWarnDataList(Integer amount);
}
