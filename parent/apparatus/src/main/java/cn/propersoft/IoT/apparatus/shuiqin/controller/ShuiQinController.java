package cn.propersoft.IoT.apparatus.shuiqin.controller;

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
    private ShuiQinService shuiQinService;

    @ResponseBody
    @GetMapping("/getShuiQinData/{userId}/device")
    @ApiOperation(value = "获取设备数据")
    public ResultBody getShuiQinData(@PathVariable String userId) {
        shuiQinService.getShuiQinData(userId);
        return ResultBody.success();
    }

}
