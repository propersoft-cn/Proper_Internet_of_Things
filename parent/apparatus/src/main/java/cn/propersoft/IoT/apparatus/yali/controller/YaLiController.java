package cn.propersoft.IoT.apparatus.yali.controller;

import cn.propersoft.IoT.apparatus.yali.service.YaliService;
import cn.propersoft.IoT.response.ResultBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "压力仪表数据接口(webSocket接口需要先建立连接)")
@RequestMapping("/yali")
public class YaLiController {

    @Autowired
    private YaliService yaliService;

    @ResponseBody
    @GetMapping("/getYaLiData/{userId}/device")
    @ApiOperation(value = "获取设备数据")
    public ResultBody getYaLiData(@PathVariable String userId) {
        yaliService.getYaLiData(userId);
        return ResultBody.success();
    }

}
