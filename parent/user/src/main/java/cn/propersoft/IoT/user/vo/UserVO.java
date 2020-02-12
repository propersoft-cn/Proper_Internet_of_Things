package cn.propersoft.IoT.user.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {
    private String id;
    private String username;
    private String password;
    private String name;
    private String role;
    private String token;
}
