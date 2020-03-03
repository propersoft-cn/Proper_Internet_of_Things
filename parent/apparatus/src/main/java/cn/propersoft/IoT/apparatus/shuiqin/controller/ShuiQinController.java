package cn.propersoft.IoT.apparatus.shuiqin.controller;

import cn.propersoft.IoT.apparatus.async.service.ApparatusAsyncService;
import cn.propersoft.IoT.apparatus.shuiqin.service.ShuiQinService;
import cn.propersoft.IoT.response.ResultBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "水侵仪表数据接口")
@RequestMapping("/shuiqin")
public class ShuiQinController {

    @Autowired
    private ApparatusAsyncService apparatusAsyncService;

    @ResponseBody
    @GetMapping("/getShuiQinData/{userId}/device")
    @ApiOperation(value = "获取设备数据(webSocket接口需要先建立连接)")
    public ResultBody getShuiQinData(@PathVariable String userId) {
        apparatusAsyncService.getShuiQinData(userId);
        return ResultBody.success();
    }

}
