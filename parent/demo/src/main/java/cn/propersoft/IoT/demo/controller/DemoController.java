package cn.propersoft.IoT.demo.controller;

import cn.propersoft.IoT.demo.service.DemoService;
import cn.propersoft.IoT.auth.annotation.UserLoginToken;
import cn.propersoft.IoT.demo.vo.DemoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DemoController {

    @Autowired
    private DemoService demoService;

    @ResponseBody
    @UserLoginToken
    @GetMapping("demo1")
    public String demo1() {
        return "hello springBoot";
    }

    @ResponseBody
    @GetMapping("/demo/findAll")
    public List<DemoVO> findAll() {
        return demoService.findAll();
    }

    @ResponseBody
    @PostMapping("/demo/add")
    public void add(DemoVO demoVO) {
        demoService.add(demoVO);
    }

    @ResponseBody
    @PostMapping("/demo/addRedisDemo")
    public void addRedisDemo(String key, String value) {
        demoService.addRedisDemo(key, value);
    }

    @ResponseBody
    @GetMapping("/demo/addRedisDemo")
    public String getRedisDemo(String key) {
        return demoService.getRedisDemo(key);
    }


}
