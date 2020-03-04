package cn.propersoft.IoT.auth.service;

import cn.propersoft.IoT.auth.entity.TokenEntity;
import cn.propersoft.IoT.auth.entity.UserEntity;

import java.util.Optional;

public interface TokenService {

    String getToken(UserEntity userEntity);

    Boolean saveToken(TokenEntity tokenEntity);

    TokenEntity findByUserId(String id);

    void deleteAllToken();

    Boolean deleteToken(String token);
}
