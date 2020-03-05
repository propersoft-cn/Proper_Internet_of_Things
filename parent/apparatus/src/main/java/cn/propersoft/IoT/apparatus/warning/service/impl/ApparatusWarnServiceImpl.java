package cn.propersoft.IoT.apparatus.warning.service.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.propersoft.IoT.apparatus.charushi.entity.ChaRuShiEntity;
import cn.propersoft.IoT.apparatus.warning.entity.ApparatusWarnEntity;
import cn.propersoft.IoT.apparatus.warning.repository.ApparatusWarnRepository;
import cn.propersoft.IoT.apparatus.warning.vo.ApparatusWarnVO;
import cn.propersoft.IoT.core.utils.MyBeanUtils;
import cn.propersoft.IoT.exception.BizException;
import cn.propersoft.IoT.exception.CommonEnum;
import cn.propersoft.IoT.websocket.server.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ApparatusWarnServiceImpl implements ApparatusWarnService {

    @Autowired
    private ApparatusWarnRepository apparatusWarnRepository;

    @Override
    public List<ApparatusWarnEntity> saveAll(List<ApparatusWarnEntity> apparatusWarnEntityList) {
        return apparatusWarnRepository.saveAll(apparatusWarnEntityList);
    }

    @Override
    public List<ApparatusWarnVO> getWarnDataList(Integer amount) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        PageRequest pageRequest = PageRequest.of(1, amount, sort);
        Pageable pageable = pageRequest.first();
        Page<ApparatusWarnEntity> page = apparatusWarnRepository.findAll(pageable);
        if (!page.isEmpty()) {
            List<ApparatusWarnEntity> content = page.getContent();
            List<ApparatusWarnVO> list = (List<ApparatusWarnVO>) MyBeanUtils.convert(content, ApparatusWarnVO.class);
            return list;
        } else {
            throw new BizException(CommonEnum.BUSINESS_ERROR);
        }
    }
}
