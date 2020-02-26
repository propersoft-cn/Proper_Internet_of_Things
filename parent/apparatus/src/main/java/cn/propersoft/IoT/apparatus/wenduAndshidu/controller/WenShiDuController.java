package cn.propersoft.IoT.apparatus.wenduAndshidu.controller;

import cn.propersoft.IoT.apparatus.wenduAndshidu.service.impl.WenShiDuService;
import cn.propersoft.IoT.response.ResultBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "温度湿度仪表数据接口(webSocket接口需要先建立连接)")
@RequestMapping("/wenduAndShidu")
public class WenShiDuController {

    @Autowired
    private WenShiDuService wenShiDuService;

    @ResponseBody
    @GetMapping("/getWenShiDuData/{userId}/device")
    @ApiOperation(value = "获取设备数据")
    public ResultBody getWenShiDuData(@PathVariable String userId) {
        wenShiDuService.getWenShiDuData(userId);
        return ResultBody.success();
    }

}
