package cn.propersoft.IoT.auth.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import cn.propersoft.IoT.auth.service.TokenService;
import cn.propersoft.IoT.user.entity.UserEntity;
import cn.propersoft.IoT.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private UserService userService;

    @Override
    public String getToken(UserEntity userEntity) {

        String token="";
        token= JWT.create().withAudience(userEntity.getId())
                .sign(Algorithm.HMAC256(userEntity.getPassword()));
        return token;
    }
}
