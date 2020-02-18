package cn.propersoft.IoT.apparatus.yali.controller;

import cn.propersoft.IoT.apparatus.yali.service.YaliService;
import cn.propersoft.IoT.response.ResultBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "压力仪表数据接口")
@RequestMapping("/yali")
public class YaLiController {

    @Autowired
    private YaliService yaliService;

    @ResponseBody
    @GetMapping("/getYaLiData/{deviceId}/device")
    @ApiOperation(value = "获取设备数据")
    public ResultBody getYaLiData(@PathVariable String deviceId) {
        yaliService.getYaLiData(deviceId);
        return ResultBody.success();
    }

}
