package cn.propersoft.IoT.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "用户")
public class UserVO {
    @ApiModelProperty(value = "用户Id")
    private String id;
    @ApiModelProperty(value = "用户账号")
    private String username;
    @ApiModelProperty(value = "用户密码")
    private String password;
    @ApiModelProperty(value = "用户姓名")
    private String name;
    @ApiModelProperty(value = "用户角色", hidden = true)
    private String role;
    @ApiModelProperty(value = "用户凭证", hidden = true)
    private String token;
}
