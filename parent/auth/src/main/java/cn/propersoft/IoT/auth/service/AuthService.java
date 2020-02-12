package cn.propersoft.IoT.auth.service;


import cn.propersoft.IoT.user.entity.UserEntity;
import cn.propersoft.IoT.user.vo.UserVO;

public interface AuthService {

    UserVO login(UserEntity userEntity);

}
