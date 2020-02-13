package cn.propersoft.IoT.demo.controller;

import cn.propersoft.IoT.demo.service.DemoService;
import cn.propersoft.IoT.auth.annotation.UserLoginToken;
import cn.propersoft.IoT.demo.vo.DemoVO;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "测试类")
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @ResponseBody
    @UserLoginToken
    @ApiOperation(value = "Hello SpringBoot")
    @GetMapping("/demo1")
    public String demo1() {
        return "hello springBoot";
    }

    @ResponseBody
    @ApiOperation(value = "查询所有Demo数据")
    @GetMapping("/findAll")
    public List<DemoVO> findAll() {
        return demoService.findAll();
    }

    @ResponseBody
    @PostMapping("/add")
    @ApiOperation(value = "新增DemoVo")
    public void add(DemoVO demoVO) {
        demoService.add(demoVO);
    }

    @ResponseBody
    @PostMapping("/addRedisDemo")
    @ApiOperation(value = "新增Redis数据 ")
    public void addRedisDemo(@ApiParam(value = "Redis的key", required = true) @RequestParam String key,
                             @ApiParam(value = "Redis的value", required = true) @RequestParam String value) {
        demoService.addRedisDemo(key, value);
    }

    @ResponseBody
    @GetMapping("/addRedisDemo")
    @ApiOperation(value = "根据key获取Redis的值 ")
    public String getRedisDemo(@ApiParam(value = "Redis的value", required = true) @RequestParam String key) {
        return demoService.getRedisDemo(key);
    }


}
