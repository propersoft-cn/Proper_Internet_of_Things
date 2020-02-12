package cn.propersoft.IoT.auth.service;

import cn.propersoft.IoT.user.entity.UserEntity;

public interface TokenService {

    String getToken(UserEntity userEntity);
}
