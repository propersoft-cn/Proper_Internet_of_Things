package cn.propersoft.IoT.apparatus.async.service;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.propersoft.IoT.apparatus.chapai.entity.ChaPaiEntity;
import cn.propersoft.IoT.apparatus.chapai.service.ChaPaiService;
import cn.propersoft.IoT.apparatus.charushi.entity.ChaRuShiEntity;
import cn.propersoft.IoT.apparatus.charushi.service.ChaRuShiService;
import cn.propersoft.IoT.apparatus.co2.entity.CO2Entity;
import cn.propersoft.IoT.apparatus.co2.service.CO2Service;
import cn.propersoft.IoT.apparatus.shuiqin.service.ShuiQinService;
import cn.propersoft.IoT.apparatus.warning.ApparatusThresholdProperties;
import cn.propersoft.IoT.apparatus.warning.vo.ApparatusWarnVO;
import cn.propersoft.IoT.apparatus.wenduAndshidu.entity.WenShiDuEntity;
import cn.propersoft.IoT.apparatus.wenduAndshidu.service.WenShiDuService;
import cn.propersoft.IoT.apparatus.yali.entity.YaliEntity;
import cn.propersoft.IoT.apparatus.yali.service.YaliService;
import cn.propersoft.IoT.apparatus.yeweizhi.entity.YeWeiZhiEntity;
import cn.propersoft.IoT.apparatus.yeweizhi.service.YeWeiZhiService;
import cn.propersoft.IoT.exception.BizException;
import cn.propersoft.IoT.exception.CommonEnum;
import cn.propersoft.IoT.websocket.server.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ApparatusAsyncService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApparatusAsyncService.class);
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

    @Autowired
    private ApparatusThresholdProperties apparatusThresholdProperties;


    @Async
    public void getCo2Data(String userId) {
        co2Service.getCo2Data(userId);
    }

    @Async
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

    @Async
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

    @Async
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

    @Async
    public void getChaPaiData(String userId) {
        chaPaiService.getChaPaiData(userId);
    }

    @Async
    public void getChaRuShiData(String userId) {
        chaRuShiService.getChaRuShiData(userId);
    }

    @Async
    public void getShuiQinData(String userId) {
        shuiQinService.getShuiQinData(userId);
    }

    @Async
    public void getWenShiDuData(String userId) {
        wenShiDuService.getWenShiDuData(userId);
    }

    @Async
    public void getYaLiData(String userId) {
        yaliService.getYaLiData(userId);
    }

    @Async
    public void getYeWeiZhiData(String userId) {
        yeWeiZhiService.getYeWeiZhiData(userId);
    }


    @Async
    public void getWarnData(String userId) {
        List<ApparatusWarnVO> result = new ArrayList<>();

        while (true) {
            judgeChaPai(result);
            judgeCaRuShi(result);
            judgeCo2(result);
            judgeWenDuAndShiDu(result);
            judgeYaLi(result);
            judgeYeWeiZhi(result);
            //TODO 水侵保留
            JSONArray jsonArray = JSONUtil.parseArray(result);
            String message = jsonArray.toString();
            WebSocketServer.sendInfo(message, userId);
            result = new ArrayList<>();
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                throw new BizException(CommonEnum.INTERNAL_SERVER_ERROR, e);
            }
        }
    }

    private void judgeYeWeiZhi(List<ApparatusWarnVO> result) {
        YeWeiZhiEntity yeWeiZhiEntity = yeWeiZhiService.findOneByOrderByAddTimeDesc();
        if (yeWeiZhiEntity.getYewei() >= apparatusThresholdProperties.getYeWeiMax()) {
            ApparatusWarnVO apparatusWarnVO = new ApparatusWarnVO();
            apparatusWarnVO.setDeviceId(yeWeiZhiEntity.getEquipmentName());
            apparatusWarnVO.setCreateTime(yeWeiZhiEntity.getAddTime());
            apparatusWarnVO.setRealValue(yeWeiZhiEntity.getYewei());
            apparatusWarnVO.setThreshold(apparatusThresholdProperties.getYeWeiMax());
            apparatusWarnVO.setWarnText("液位值高于报警阈值");
            result.add(apparatusWarnVO);
        }

        if (yeWeiZhiEntity.getYewei() <= apparatusThresholdProperties.getYeWeiMin()) {
            ApparatusWarnVO apparatusWarnVO = new ApparatusWarnVO();
            apparatusWarnVO.setDeviceId(yeWeiZhiEntity.getEquipmentName());
            apparatusWarnVO.setCreateTime(yeWeiZhiEntity.getAddTime());
            apparatusWarnVO.setRealValue(yeWeiZhiEntity.getYewei());
            apparatusWarnVO.setThreshold(apparatusThresholdProperties.getYeWeiMin());
            apparatusWarnVO.setWarnText("液位值低于报警阈值");
            result.add(apparatusWarnVO);
        }


    }


    private void judgeYaLi(List<ApparatusWarnVO> result) {
        YaliEntity yaliEntity = yaliService.findOneByOrderByAddTimeDesc();
        if (yaliEntity.getYaliNum() >= apparatusThresholdProperties.getYaLiMax()) {
            ApparatusWarnVO apparatusWarnVO = new ApparatusWarnVO();
            apparatusWarnVO.setDeviceId(yaliEntity.getEquipmentName());
            apparatusWarnVO.setCreateTime(yaliEntity.getAddTime());
            apparatusWarnVO.setRealValue(yaliEntity.getYaliNum());
            apparatusWarnVO.setThreshold(apparatusThresholdProperties.getYaLiMax());
            apparatusWarnVO.setWarnText("压力高于报警阈值");
            result.add(apparatusWarnVO);
        }

        if (yaliEntity.getYaliNum() <= apparatusThresholdProperties.getYaLiMin()) {
            ApparatusWarnVO apparatusWarnVO = new ApparatusWarnVO();
            apparatusWarnVO.setDeviceId(yaliEntity.getEquipmentName());
            apparatusWarnVO.setCreateTime(yaliEntity.getAddTime());
            apparatusWarnVO.setRealValue(yaliEntity.getYaliNum());
            apparatusWarnVO.setThreshold(apparatusThresholdProperties.getYaLiMin());
            apparatusWarnVO.setWarnText("压力低于报警阈值");
            result.add(apparatusWarnVO);
        }
    }

    private void judgeWenDuAndShiDu(List<ApparatusWarnVO> result) {
        WenShiDuEntity wenShiDuEntity = wenShiDuService.findOneByOrderByAddTimeDesc();
        if (wenShiDuEntity.getWendu() >= apparatusThresholdProperties.getWsWenDuMax()) {
            ApparatusWarnVO apparatusWarnVO = new ApparatusWarnVO();
            apparatusWarnVO.setDeviceId(wenShiDuEntity.getEquipmentName());
            apparatusWarnVO.setCreateTime(wenShiDuEntity.getAddTime());
            apparatusWarnVO.setRealValue(wenShiDuEntity.getWendu());
            apparatusWarnVO.setThreshold(apparatusThresholdProperties.getWsWenDuMax());
            apparatusWarnVO.setWarnText("温度高于报警阈值");
            result.add(apparatusWarnVO);
        }

        if (wenShiDuEntity.getWendu() <= apparatusThresholdProperties.getWsWenDuMin()) {
            ApparatusWarnVO apparatusWarnVO = new ApparatusWarnVO();
            apparatusWarnVO.setDeviceId(wenShiDuEntity.getEquipmentName());
            apparatusWarnVO.setCreateTime(wenShiDuEntity.getAddTime());
            apparatusWarnVO.setRealValue(wenShiDuEntity.getWendu());
            apparatusWarnVO.setThreshold(apparatusThresholdProperties.getWsWenDuMin());
            apparatusWarnVO.setWarnText("温度低于报警阈值");
            result.add(apparatusWarnVO);
        }

        if (wenShiDuEntity.getShidu() >= apparatusThresholdProperties.getWsShiDuMax()) {
            ApparatusWarnVO apparatusWarnVO = new ApparatusWarnVO();
            apparatusWarnVO.setDeviceId(wenShiDuEntity.getEquipmentName());
            apparatusWarnVO.setCreateTime(wenShiDuEntity.getAddTime());
            apparatusWarnVO.setRealValue(wenShiDuEntity.getShidu());
            apparatusWarnVO.setThreshold(apparatusThresholdProperties.getWsShiDuMax());
            apparatusWarnVO.setWarnText("湿度高于报警阈值");
            result.add(apparatusWarnVO);
        }


        if (wenShiDuEntity.getShidu() <= apparatusThresholdProperties.getWsShiDuMin()) {
            ApparatusWarnVO apparatusWarnVO = new ApparatusWarnVO();
            apparatusWarnVO.setDeviceId(wenShiDuEntity.getEquipmentName());
            apparatusWarnVO.setCreateTime(wenShiDuEntity.getAddTime());
            apparatusWarnVO.setRealValue(wenShiDuEntity.getShidu());
            apparatusWarnVO.setThreshold(apparatusThresholdProperties.getWsShiDuMin());
            apparatusWarnVO.setWarnText("温度低于报警阈值");
            result.add(apparatusWarnVO);
        }


    }


    private void judgeCo2(List<ApparatusWarnVO> result) {
        CO2Entity co2Entity = co2Service.findOneByOrderByAddTimeDesc();
        if (co2Entity.getCo2Num() >= apparatusThresholdProperties.getCo2Max()) {
            ApparatusWarnVO apparatusWarnVO = new ApparatusWarnVO();
            apparatusWarnVO.setDeviceId(co2Entity.getEquipmentName());
            apparatusWarnVO.setCreateTime(co2Entity.getAddTime());
            apparatusWarnVO.setRealValue(co2Entity.getCo2Num());
            apparatusWarnVO.setThreshold(apparatusThresholdProperties.getCo2Max());
            apparatusWarnVO.setWarnText("二氧化碳浓度高于报警阈值");
            result.add(apparatusWarnVO);
        }

        if (co2Entity.getCo2Num() <= apparatusThresholdProperties.getCo2Min()) {
            ApparatusWarnVO apparatusWarnVO = new ApparatusWarnVO();
            apparatusWarnVO.setDeviceId(co2Entity.getEquipmentName());
            apparatusWarnVO.setCreateTime(co2Entity.getAddTime());
            apparatusWarnVO.setRealValue(co2Entity.getCo2Num());
            apparatusWarnVO.setThreshold(apparatusThresholdProperties.getCo2Min());
            apparatusWarnVO.setWarnText("二氧化碳浓度低于报警阈值");
            result.add(apparatusWarnVO);
        }


    }


    private void judgeCaRuShi(List<ApparatusWarnVO> result) {
        ChaRuShiEntity chaRuShiEntity = chaRuShiService.findOneByOrderByAddTimeDesc();
        if (chaRuShiEntity.getWendu() >= apparatusThresholdProperties.getCWenDuMax()) {
            ApparatusWarnVO apparatusWarnVO = new ApparatusWarnVO();
            apparatusWarnVO.setDeviceId(chaRuShiEntity.getEquipmentName());
            apparatusWarnVO.setCreateTime(chaRuShiEntity.getAddTime());
            apparatusWarnVO.setRealValue(chaRuShiEntity.getWendu());
            apparatusWarnVO.setThreshold(apparatusThresholdProperties.getCWenDuMax());
            apparatusWarnVO.setWarnText("温度高于报警阈值");
            result.add(apparatusWarnVO);
        }

        if (chaRuShiEntity.getWendu() <= apparatusThresholdProperties.getCWenDuMin()) {
            ApparatusWarnVO apparatusWarnVO = new ApparatusWarnVO();
            apparatusWarnVO.setDeviceId(chaRuShiEntity.getEquipmentName());
            apparatusWarnVO.setCreateTime(chaRuShiEntity.getAddTime());
            apparatusWarnVO.setRealValue(chaRuShiEntity.getWendu());
            apparatusWarnVO.setThreshold(apparatusThresholdProperties.getCWenDuMin());
            apparatusWarnVO.setWarnText("温度低于报警阈值");
            result.add(apparatusWarnVO);
        }

    }

    private void judgeChaPai(List<ApparatusWarnVO> result) {
        ChaPaiEntity chaPaiEntity = chaPaiService.findOneByOrderByAddTimeDesc();
        if (chaPaiEntity.getDianya() >= apparatusThresholdProperties.getDianYaMax()) {
            ApparatusWarnVO apparatusWarnVO = new ApparatusWarnVO();
            apparatusWarnVO.setDeviceId(chaPaiEntity.getEquipmentName());
            apparatusWarnVO.setCreateTime(chaPaiEntity.getAddTime());
            apparatusWarnVO.setRealValue(chaPaiEntity.getDianya());
            apparatusWarnVO.setThreshold(apparatusThresholdProperties.getDianYaMax());
            apparatusWarnVO.setWarnText("电压高于报警阈值");
            result.add(apparatusWarnVO);
        }

        if (chaPaiEntity.getDianya() <= apparatusThresholdProperties.getDianYaMin()) {
            ApparatusWarnVO apparatusWarnVO = new ApparatusWarnVO();
            apparatusWarnVO.setDeviceId(chaPaiEntity.getEquipmentName());
            apparatusWarnVO.setCreateTime(chaPaiEntity.getAddTime());
            apparatusWarnVO.setRealValue(chaPaiEntity.getDianya());
            apparatusWarnVO.setThreshold(apparatusThresholdProperties.getDianYaMin());
            apparatusWarnVO.setWarnText("电压小于报警阈值");
            result.add(apparatusWarnVO);
        }

        if (chaPaiEntity.getDianliu() >= apparatusThresholdProperties.getDianLiuMax()) {
            ApparatusWarnVO apparatusWarnVO = new ApparatusWarnVO();
            apparatusWarnVO.setDeviceId(chaPaiEntity.getEquipmentName());
            apparatusWarnVO.setCreateTime(chaPaiEntity.getAddTime());
            apparatusWarnVO.setRealValue(chaPaiEntity.getDianliu());
            apparatusWarnVO.setThreshold(apparatusThresholdProperties.getDianLiuMax());
            apparatusWarnVO.setWarnText("电流高于报警阈值");
            result.add(apparatusWarnVO);
        }

        if (chaPaiEntity.getDianliu() <= apparatusThresholdProperties.getDianLiuMin()) {
            ApparatusWarnVO apparatusWarnVO = new ApparatusWarnVO();
            apparatusWarnVO.setDeviceId(chaPaiEntity.getEquipmentName());
            apparatusWarnVO.setCreateTime(chaPaiEntity.getAddTime());
            apparatusWarnVO.setRealValue(chaPaiEntity.getDianliu());
            apparatusWarnVO.setThreshold(apparatusThresholdProperties.getDianLiuMin());
            apparatusWarnVO.setWarnText("电流小于报警阈值");
            result.add(apparatusWarnVO);
        }
    }


}
