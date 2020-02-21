package cn.propersoft.IoT.apparatus.shuiqin.service.impl;


import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.propersoft.IoT.apparatus.shuiqin.entity.ShuiQinEntity;
import cn.propersoft.IoT.apparatus.shuiqin.repository.ShuiQinRepository;
import cn.propersoft.IoT.apparatus.shuiqin.service.ShuiQinService;
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

@Service
public class ShuiQinServiceImpl implements ShuiQinService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShuiQinServiceImpl.class);
    @Autowired
    private ShuiQinRepository shuiQinRepository;

    @Override
    public void getShuiQinData(String deviceId) {
        boolean isStop = true;
        Sort sort = Sort.by(Sort.Direction.DESC, "addTime");
        PageRequest pageRequest = PageRequest.of(1, 1, sort);
        Pageable pageable = pageRequest.first();
        while (isStop) {
            Page<ShuiQinEntity> page = shuiQinRepository.findAll(pageable);
            if (!page.isEmpty()) {
                ShuiQinEntity yaliEntity = page.toList().get(0);
                JSONObject jsonObject = JSONUtil.parseObj(yaliEntity);
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
