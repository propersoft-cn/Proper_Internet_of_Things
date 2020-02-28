package cn.propersoft.IoT.auth.controller;

import cn.propersoft.IoT.auth.service.AuthService;
import cn.propersoft.IoT.response.ResultBody;
import cn.propersoft.IoT.user.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "认证接口")
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @ResponseBody
    @ApiOperation(value = "获取token")
    @PostMapping("/getToken")
    public ResultBody login(@ApiParam UserVO userVO) {
        UserVO login = authService.login(userVO);
        return ResultBody.success(login.getToken());
    }

}
