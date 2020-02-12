package cn.propersoft.IoT.auth.controller;

import cn.propersoft.IoT.auth.annotation.UserLoginToken;
import cn.propersoft.IoT.auth.service.AuthService;
import cn.propersoft.IoT.response.ResultBody;
import cn.propersoft.IoT.user.entity.UserEntity;
import cn.propersoft.IoT.user.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @ResponseBody
    @PostMapping("/auth/login")
    public ResultBody login(UserEntity userEntity) {
        UserVO login = authService.login(userEntity);
        return ResultBody.success(login);
    }

    @UserLoginToken
    @GetMapping("/getMessage")
    public String getMessage(){
        return "你已通过验证";
    }
}
