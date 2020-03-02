package cn.propersoft.IoT.user.service;

import cn.propersoft.IoT.user.entity.UserEntity;

public interface TokenService {

    String getToken(UserEntity userEntity);
}
