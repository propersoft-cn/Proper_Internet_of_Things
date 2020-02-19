package cn.propersoft.IoT.apparatus.yeweizhi.service.impl;


import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.propersoft.IoT.apparatus.yeweizhi.entity.YeWeiZhiEntity;
import cn.propersoft.IoT.apparatus.yeweizhi.repository.YeWeiZhiRepository;
import cn.propersoft.IoT.apparatus.yeweizhi.service.YeWeiZhiService;
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
public class YeWeiZhiServiceImpl implements YeWeiZhiService {

    private static final Logger LOGGER = LoggerFactory.getLogger(YeWeiZhiServiceImpl.class);
    @Autowired
    private YeWeiZhiRepository yeWeiZhiRepository;

    @Override
    public void getYeWeiZhiData(String deviceId) {
        boolean isStop = true;
        Sort sort = Sort.by(Sort.Direction.DESC, "addTime");
        PageRequest pageRequest = PageRequest.of(1, 1, sort);
        Pageable pageable = pageRequest.first();
        while (isStop) {
            Page<YeWeiZhiEntity> page = yeWeiZhiRepository.findAll(pageable);
            if (!page.isEmpty()) {
                YeWeiZhiEntity yaliEntity = page.toList().get(0);
                JSONObject jsonObject = JSONUtil.parseObj(yaliEntity);
                try {
                    //TODO 追加停止判断
                    //TODO
                    WebSocketServer.sendInfo(jsonObject.toString(), deviceId);
                    Thread.sleep(1000L);
                } catch (Exception e) {
                    throw new BizException(CommonEnum.BUSINESS_ERROR, e);
                }
            }
        }
    }
}
