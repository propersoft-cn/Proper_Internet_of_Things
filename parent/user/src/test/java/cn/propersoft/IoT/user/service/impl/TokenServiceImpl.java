package cn.propersoft.IoT.user.service.impl;

import cn.propersoft.IoT.user.entity.UserEntity;
import cn.propersoft.IoT.user.service.TokenService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

    @Override
    public String getToken(UserEntity userEntity) {

        String token="";
        token= JWT.create().withAudience(userEntity.getId())
                .sign(Algorithm.HMAC256(userEntity.getPassword()));
        return token;
    }
}
