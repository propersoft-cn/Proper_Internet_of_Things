package cn.propersoft.IoT.apparatus.blend.serviceImpl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.propersoft.IoT.apparatus.chapai.entity.ChaPaiEntity;
import cn.propersoft.IoT.apparatus.chapai.service.ChaPaiService;
import cn.propersoft.IoT.apparatus.charushi.entity.ChaRuShiEntity;
import cn.propersoft.IoT.apparatus.charushi.service.ChaRuShiService;
import cn.propersoft.IoT.apparatus.co2.entity.CO2Entity;
import cn.propersoft.IoT.apparatus.co2.service.CO2Service;
import cn.propersoft.IoT.apparatus.shuiqin.service.ShuiQinService;
import cn.propersoft.IoT.apparatus.warning.serviceImpl.ApparatusWarnServiceImpl;
import cn.propersoft.IoT.apparatus.wenduAndshidu.entity.WenShiDuEntity;
import cn.propersoft.IoT.apparatus.wenduAndshidu.service.WenShiDuService;
import cn.propersoft.IoT.apparatus.yali.service.YaliService;
import cn.propersoft.IoT.apparatus.yeweizhi.entity.YeWeiZhiEntity;
import cn.propersoft.IoT.apparatus.yeweizhi.service.YeWeiZhiService;
import cn.propersoft.IoT.exception.BizException;
import cn.propersoft.IoT.exception.CommonEnum;
import cn.propersoft.IoT.websocket.server.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ApparatusBlendServiceImpl {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApparatusBlendServiceImpl.class);

    @Autowired
    private ChaPaiService chaPaiService;

    @Autowired
    private ChaRuShiService chaRuShiService;

    @Autowired
    private CO2Service co2Service;

    @Autowired
    private ShuiQinService shuiQinService;

    @Autowired
    private WenShiDuService wenShiDuService;

    @Autowired
    private YaliService yaliService;

    @Autowired
    private YeWeiZhiService yeWeiZhiService;

    public void getWenShiDuAndChaPaiData(String userId) {

        Map<String, Object> map = new HashMap<>(2);

        while (true) {
            ChaPaiEntity chaPaiEntity = chaPaiService.findOneByOrderByAddTimeDesc();
            WenShiDuEntity wenShiDuEntity = wenShiDuService.findOneByOrderByAddTimeDesc();
            map.put("wendu_shidu", wenShiDuEntity);
            map.put("chapai", chaPaiEntity);
            JSONObject jsonObject = JSONUtil.parseObj(map);
            String json = jsonObject.toString();
            WebSocketServer.sendInfo(json, userId);
            map = new HashMap<>(2);
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                throw new BizException(CommonEnum.INTERNAL_SERVER_ERROR, e);
            }
        }


    }

    public void getTouRuShiAndChaRuShiData(String userId) {
        Map<String, Object> map = new HashMap<>(2);
        while (true) {
            YeWeiZhiEntity yeWeiZhiEntity = yeWeiZhiService.findOneByOrderByAddTimeDesc();
            ChaRuShiEntity chaRuShiEntity = chaRuShiService.findOneByOrderByAddTimeDesc();
            map.put("tourushi_yewei", yeWeiZhiEntity);
            map.put("charushi", chaRuShiEntity);
            JSONObject jsonObject = JSONUtil.parseObj(map);
            String json = jsonObject.toString();
            WebSocketServer.sendInfo(json, userId);
            map = new HashMap<>(2);
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                throw new BizException(CommonEnum.INTERNAL_SERVER_ERROR, e);
            }
        }
    }

    public void getCo2AndWenShiDuData(String userId) {

        Map<String, Object> map = new HashMap<>(2);
        while (true) {
            CO2Entity co2Entity = co2Service.findOneByOrderByAddTimeDesc();
            WenShiDuEntity wenShiDuEntity = wenShiDuService.findOneByOrderByAddTimeDesc();
            map.put("co2", co2Entity);
            map.put("wendu_shidu", wenShiDuEntity);
            JSONObject jsonObject = JSONUtil.parseObj(map);
            String json = jsonObject.toString();
            WebSocketServer.sendInfo(json, userId);
            map = new HashMap<>(2);
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                throw new BizException(CommonEnum.INTERNAL_SERVER_ERROR, e);
            }
        }
    }
}
