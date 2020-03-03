package cn.propersoft.IoT.apparatus.chapai.controller;

import cn.propersoft.IoT.apparatus.async.service.ApparatusAsyncService;
import cn.propersoft.IoT.apparatus.chapai.service.ChaPaiService;
import cn.propersoft.IoT.response.ResultBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "插排仪表数据接口")
@RequestMapping("/chapai")
public class ChaPaiController {

    @Autowired
    private ApparatusAsyncService apparatusAsyncService;

    @ResponseBody
    @GetMapping("/getChaPaiData/{userId}/device")
    @ApiOperation(value = "获取设备数据(webSocket接口需要先建立连接)")
    public ResultBody getYaLiData(@PathVariable String userId) {
        apparatusAsyncService.getChaPaiData(userId);
        return ResultBody.success();
    }
}
