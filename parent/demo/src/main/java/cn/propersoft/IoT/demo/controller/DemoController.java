package cn.propersoft.IoT.demo.controller;

import cn.propersoft.IoT.demo.entity.DemoEntity;
import cn.propersoft.IoT.demo.service.DemoService;
import cn.propersoft.IoT.auth.annotation.UserLoginToken;
import cn.propersoft.IoT.demo.vo.DemoVO;
import cn.propersoft.IoT.websocket.server.WebSocketServer;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@Api(tags = "无用测试接口")
@RequestMapping("/demo")
public class DemoController {

    private static final Logger LOGGER= LoggerFactory.getLogger(DemoController.class);

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

    @GetMapping("/push/{toUserId}")
    public ResponseEntity<String> pushToWeb(String message, @PathVariable String toUserId) throws IOException {
        Boolean isStop = true;
        while (isStop) {
            DemoEntity entity = demoService.findOneByOrderById();
            WebSocketServer.sendInfo(entity.getName(), toUserId);
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LOGGER.info("MSG SEND SUCCESS");
        }

//        WebSocketServer.sendInfo(message, toUserId);
        return ResponseEntity.ok("MSG SEND SUCCESS");
    }

}
