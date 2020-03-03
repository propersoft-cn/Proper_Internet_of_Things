package cn.propersoft.IoT.auth.service;

import cn.propersoft.IoT.auth.entity.UserEntity;

public interface TokenService {

    String getToken(UserEntity userEntity);
}
