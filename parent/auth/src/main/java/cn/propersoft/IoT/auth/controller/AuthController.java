package cn.propersoft.IoT.auth.controller;

import cn.propersoft.IoT.auth.annotation.UserLoginToken;
import cn.propersoft.IoT.auth.service.AuthService;
import cn.propersoft.IoT.auth.service.TokenService;
import cn.propersoft.IoT.response.ResultBody;
import cn.propersoft.IoT.auth.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@Api(tags = "认证接口")
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private TokenService tokenService;


    @ResponseBody
    @ApiOperation(value = "获取token")
    @PostMapping("/getToken")
    public ResultBody login(@ApiParam UserVO userVO) {
        UserVO login = authService.login(userVO);
        return ResultBody.success(login.getToken());
    }

    @ResponseBody
    @UserLoginToken
    @ApiOperation(value = "登出")
    @PostMapping("/logout")
    public ResultBody logout(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        return ResultBody.success(tokenService.deleteToken(token));
    }

}
