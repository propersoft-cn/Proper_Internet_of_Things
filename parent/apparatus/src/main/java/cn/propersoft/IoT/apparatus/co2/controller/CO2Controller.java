package cn.propersoft.IoT.apparatus.co2.controller;

import cn.propersoft.IoT.apparatus.co2.service.impl.CO2Service;
import cn.propersoft.IoT.response.ResultBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "二氧化碳仪表数据接口(webSocket接口需要先建立连接)")
@RequestMapping("/co2")
public class CO2Controller {

    @Autowired
    private CO2Service co2Service;

    @ResponseBody
    @GetMapping("/getCo2Data/{userId}/device")
    @ApiOperation(value = "获取设备数据")
    public ResultBody getCo2Data(@PathVariable String userId) {
        co2Service.getCo2Data(userId);
        return ResultBody.success();
    }

}
