package cn.propersoft.IoT.apparatus.analysis;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.propersoft.IoT.apparatus.chapai.entity.ChaPaiEntity;
import cn.propersoft.IoT.apparatus.chapai.service.ChaPaiService;
import cn.propersoft.IoT.response.ResultBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@Api(tags = "统计分析接口")
@RequestMapping("/statistical/analysis/")
public class StatisticalAnalysisController {

    @Autowired
    private ChaPaiService chaPaiService;

    @ResponseBody
    @GetMapping("/getFloorBatteryData/{floor}/pie")
    @ApiOperation(value = "各楼层能耗分析饼图")
    public ResultBody getFloorBatteryPieData(@PathVariable String floor, String startTime, String endTime) {
        ChaPaiEntity chaPaiEntity = chaPaiService.getFloorData(floor, new Date(Convert.toLong(startTime)), new Date(Convert.toLong(endTime)));
        return ResultBody.success(chaPaiEntity);
    }

    @ResponseBody
    @GetMapping("/getFloorBatteryData/{floor}/histogram")
    @ApiOperation(value = "各楼层能耗分析柱状图")
    public ResultBody getFloorBatteryHistogramData(@PathVariable String floor, String startTime, String endTime) {
        List<Map> result = new ArrayList<Map>();
        DateTime dateTime = DateUtil.date(new Date());
        Calendar calendar = dateTime.toCalendar();
        for (int i = 0; i < 6; i++) {
            Map map = new HashMap();
            calendar.add(Calendar.MONTH, i);
            String format = DateUtil.format(calendar.getTime(), "yyyy-MM");
            map.put(format, RandomUtil.randomInt(100, 150));
            result.add(map);
        }
        return ResultBody.success(result);
    }


    @ResponseBody
    @GetMapping("/getDeviceBatteryData/{deviceId}/pie")
    @ApiOperation(value = "各设备能耗分析饼图")
    public ResultBody getDeviceBatteryData(@PathVariable String deviceId, String startTime, String endTime) {
        ChaPaiEntity chaPaiEntity = chaPaiService.getFloorData(deviceId, new Date(Convert.toLong(startTime)), new Date(Convert.toLong(endTime)));
        return ResultBody.success(chaPaiEntity);
    }

    @ResponseBody
    @GetMapping("/getDeviceBatteryData/{deviceId}/histogram")
    @ApiOperation(value = "各设备能耗分析柱状图")
    public ResultBody getDeviceBatteryHistogramData(@PathVariable String deviceId) {
        List<Map> result = new ArrayList<Map>();
        DateTime dateTime = DateUtil.date(new Date());
        Calendar calendar = dateTime.toCalendar();
        for (int i = 0; i < 6; i++) {
            Map map = new HashMap();
            calendar.add(Calendar.MONTH, i);
            String format = DateUtil.format(calendar.getTime(), "yyyy-MM");
            map.put(format, RandomUtil.randomInt(100, 150));
            result.add(map);
        }
        return ResultBody.success(result);
    }

    @ResponseBody
    @GetMapping("/getFloorWaterData/{floor}/pie")
    @ApiOperation(value = "各楼层用水分析饼图")
    public ResultBody getFloorWaterData(@PathVariable String floor, String startTime, String endTime) {
        ChaPaiEntity chaPaiEntity = chaPaiService.getFloorData(floor, new Date(Convert.toLong(startTime)), new Date(Convert.toLong(endTime)));
        return ResultBody.success(chaPaiEntity);
    }

    @ResponseBody
    @GetMapping("/getFloorWaterData/{floor}/histogram")
    @ApiOperation(value = "各楼层用水分析柱状图")
    public ResultBody getFloorWaterHistogramData(@PathVariable String floor) {
        List<Map> result = new ArrayList<Map>();
        DateTime dateTime = DateUtil.date(new Date());
        Calendar calendar = dateTime.toCalendar();
        for (int i = 0; i < 6; i++) {
            Map map = new HashMap();
            calendar.add(Calendar.MONTH, i);
            String format = DateUtil.format(calendar.getTime(), "yyyy-MM");
            map.put(format, RandomUtil.randomInt(100, 150));
            result.add(map);
        }
        return ResultBody.success(result);
    }
}
