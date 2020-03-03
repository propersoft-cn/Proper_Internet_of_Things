package cn.propersoft.IoT.apparatus.charushi.controller;

import cn.propersoft.IoT.apparatus.async.service.ApparatusAsyncService;
import cn.propersoft.IoT.apparatus.charushi.service.ChaRuShiService;
import cn.propersoft.IoT.apparatus.yali.service.YaliService;
import cn.propersoft.IoT.response.ResultBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "温度仪表数据接口")
@RequestMapping("/charushi")
public class ChaRuShiController {

    @Autowired
    private ApparatusAsyncService apparatusAsyncService;

    @ResponseBody
    @GetMapping("/getChaRuShiData/{userId}/device")
    @ApiOperation(value = "获取设备数据(webSocket接口需要先建立连接)")
    public ResultBody getYaLiData(@PathVariable String userId) {
        apparatusAsyncService.getChaRuShiData(userId);
        return ResultBody.success();
    }

}
