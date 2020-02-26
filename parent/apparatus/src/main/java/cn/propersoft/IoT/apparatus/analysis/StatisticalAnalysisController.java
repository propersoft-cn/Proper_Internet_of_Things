package cn.propersoft.IoT.apparatus.analysis;

import cn.hutool.core.convert.Convert;
import cn.propersoft.IoT.apparatus.chapai.entity.ChaPaiEntity;
import cn.propersoft.IoT.apparatus.chapai.service.ChaPaiService;
import cn.propersoft.IoT.response.ResultBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@Api(tags = "统计分析接口")
@RequestMapping("/statistical/analysis/")
public class StatisticalAnalysisController {

    @Autowired
    private ChaPaiService chaPaiService;

    @ResponseBody
    @GetMapping("/getFloorBatteryData/{floor}/battery")
    @ApiOperation(value = "各楼层能耗分析")
    public ResultBody getFloorBatteryData(@PathVariable String floor, String startTime, String endTime) {
        ChaPaiEntity chaPaiEntity = chaPaiService.getFloorData(floor, new Date(Convert.toLong(startTime)), new Date(Convert.toLong(endTime)));
        return ResultBody.success(chaPaiEntity);
    }


    @ResponseBody
    @GetMapping("/getDeviceBatteryData/{deviceId}/battery")
    @ApiOperation(value = "各设备能耗分析")
    public ResultBody getDeviceBatteryData(@PathVariable String deviceId, String startTime, String endTime) {
        ChaPaiEntity chaPaiEntity = chaPaiService.getFloorData(deviceId, new Date(Convert.toLong(startTime)), new Date(Convert.toLong(endTime)));
        return ResultBody.success(chaPaiEntity);
    }

    @ResponseBody
    @GetMapping("/getFloorWaterData/{floor}/water")
    @ApiOperation(value = "各楼层用水分析")
    public ResultBody getFloorWaterData(@PathVariable String floor, String startTime, String endTime) {
        ChaPaiEntity chaPaiEntity = chaPaiService.getFloorData(floor, new Date(Convert.toLong(startTime)), new Date(Convert.toLong(endTime)));
        return ResultBody.success(chaPaiEntity);
    }
}
