package cn.propersoft.IoT.apparatus.blend.controller;

import cn.propersoft.IoT.apparatus.async.service.ApparatusAsyncService;
import cn.propersoft.IoT.response.ResultBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "混合仪表数据接口")
@RequestMapping("/blend")
public class ApparatusBlendController {

    @Autowired
    private ApparatusAsyncService apparatusAsyncService;

    @ResponseBody
    @GetMapping("/getData/WenShiDuAndChaPai/{userId}")
    @ApiOperation(value = "获取温度湿度和插排数据(webSocket接口需要先建立连接)")
    public ResultBody getWenShiDuAndChaPaiData(@PathVariable String userId) {
        apparatusAsyncService.getWenShiDuAndChaPaiData(userId);
        return ResultBody.success();
    }


    @ResponseBody
    @GetMapping("/getData/TouRuShiAndChaRuShi/{userId}")
    @ApiOperation(value = "获取投入式和插入式数据(webSocket接口需要先建立连接)")
    public ResultBody getTouRuShiAndChaRuShiData(@PathVariable String userId) {
        apparatusAsyncService.getTouRuShiAndChaRuShiData(userId);
        return ResultBody.success();
    }



    @ResponseBody
    @GetMapping("/getData/CO2AndWenShiDu/{userId}")
    @ApiOperation(value = "获取温度湿度和插入式数据(webSocket接口需要先建立连接)")
    public ResultBody getCo2AndWenShiDuData(@PathVariable String userId) {
        apparatusAsyncService.getCo2AndWenShiDuData(userId);
        return ResultBody.success();
    }
}
