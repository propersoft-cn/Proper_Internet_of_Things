package cn.propersoft.IoT.user.controller;

import cn.propersoft.IoT.response.ResultBody;
import cn.propersoft.IoT.user.service.UserService;
import cn.propersoft.IoT.user.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "用户接口")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @GetMapping("/all")
    @ApiOperation(value = "查询所有用户")
    public ResultBody findAll() {
        List<UserVO> all = userService.findAll();
        return ResultBody.success(all);
    }

    @ResponseBody
    @PostMapping("/create")
    @ApiOperation(value = "新增用户")
    public ResultBody create(UserVO userVO) {
        userService.createUser(userVO);
        return ResultBody.success();
    }


    @ResponseBody
    @DeleteMapping("/delete")
    @ApiOperation(value = "删除用户")
    public ResultBody delete(@ApiParam(value = "用户id集合（逗号分隔）") @RequestParam String ids) {
        return ResultBody.success(userService.deleteUser(ids));
    }

    @ResponseBody
    @PutMapping("/update")
    @ApiOperation(value = "修改密码")
    public ResultBody update(@ApiParam(value = "用户id") @RequestParam String id,
                             @ApiParam(value = "旧密码") @RequestParam String oldPassword,
                             @ApiParam(value = "新密码") @RequestParam String newPassword) {
        return ResultBody.success(userService.update(id, oldPassword, newPassword));
    }


}
