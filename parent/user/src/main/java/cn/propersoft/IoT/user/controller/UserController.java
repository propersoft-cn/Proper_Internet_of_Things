package cn.propersoft.IoT.user.controller;

import cn.propersoft.IoT.response.ResultBody;
import cn.propersoft.IoT.user.service.UserService;
import cn.propersoft.IoT.user.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @GetMapping("/user/all")
    public ResultBody findAll() {
        List<UserVO> all = userService.findAll();
        return ResultBody.success(all);
    }

}
