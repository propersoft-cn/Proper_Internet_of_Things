package cn.propersoft.IoT.apparatus.yeweizhi.controller;

import cn.propersoft.IoT.apparatus.async.service.ApparatusAsyncService;
import cn.propersoft.IoT.apparatus.yeweizhi.service.YeWeiZhiService;
import cn.propersoft.IoT.response.ResultBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "液位值仪表数据接口")
@RequestMapping("/yeweizhi")
public class YeWeiZhiController {

    @Autowired
    private ApparatusAsyncService apparatusAsyncService;

    @ResponseBody
    @GetMapping("/getYeWeiZhiData/{userId}/device")
    @ApiOperation(value = "获取设备数据(webSocket接口需要先建立连接)")
    public ResultBody getYeWeiZhiData(@PathVariable String userId) {
        apparatusAsyncService.getYeWeiZhiData(userId);
        return ResultBody.success();
    }

}
