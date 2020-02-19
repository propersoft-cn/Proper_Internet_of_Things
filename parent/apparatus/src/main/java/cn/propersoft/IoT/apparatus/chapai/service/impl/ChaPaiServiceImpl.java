package cn.propersoft.IoT.apparatus.chapai.service.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.propersoft.IoT.apparatus.chapai.entity.ChaPaiEntity;
import cn.propersoft.IoT.apparatus.chapai.repository.ChaPaiRepository;
import cn.propersoft.IoT.apparatus.chapai.service.ChaPaiService;
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
public class ChaPaiServiceImpl implements ChaPaiService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChaPaiServiceImpl.class);

    @Autowired
    private ChaPaiRepository chaPaiRepository;

    @Override
    public void getChaPaiData(String deviceId) {
        boolean isStop = true;
        Sort sort = Sort.by(Sort.Direction.DESC, "addTime");
        PageRequest pageRequest = PageRequest.of(1, 1, sort);
        Pageable pageable = pageRequest.first();
        while (isStop) {
            Page<ChaPaiEntity> page = chaPaiRepository.findAll(pageable);
            if (!page.isEmpty()) {
                List<ChaPaiEntity> list = page.toList();
                JSONObject jsonObject = JSONUtil.parseObj(list.get(0));
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
}
