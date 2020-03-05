package cn.propersoft.IoT.apparatus.warning.controller;

import cn.propersoft.IoT.apparatus.async.service.ApparatusAsyncService;
import cn.propersoft.IoT.apparatus.warning.service.impl.ApparatusWarnService;
import cn.propersoft.IoT.apparatus.warning.vo.ApparatusWarnVO;
import cn.propersoft.IoT.response.ResultBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "设备警告接口")
@RequestMapping("/warn")
public class ApparatusWarnController {

    @Autowired
    private ApparatusAsyncService apparatusAsyncService;

    @Autowired
    private ApparatusWarnService apparatusWarnService;

    @ResponseBody
    @GetMapping("/getWarnData/{userId}")
    @ApiOperation(value = "获取警告数据(webSocket接口,需要先建立连接)")
    public ResultBody getWarnData(@PathVariable String userId) {
        apparatusAsyncService.getWarnData(userId);
        return ResultBody.success();
    }

    @ResponseBody
    @GetMapping("/getWarnData/list")
    @ApiOperation(value = "获取警告数据列表")
    public ResultBody getWarnDataList(Integer amount) {
        List<ApparatusWarnVO> list = apparatusWarnService.getWarnDataList(amount);
        return ResultBody.success(list);
    }

}
