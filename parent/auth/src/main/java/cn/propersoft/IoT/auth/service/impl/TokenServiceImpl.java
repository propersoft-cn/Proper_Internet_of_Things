package cn.propersoft.IoT.auth.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.propersoft.IoT.auth.entity.TokenEntity;
import cn.propersoft.IoT.auth.repository.TokenRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import cn.propersoft.IoT.auth.service.TokenService;
import cn.propersoft.IoT.auth.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TokenServiceImpl implements TokenService {

    private static final Logger logger = LoggerFactory.getLogger(TokenServiceImpl.class);
    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public String getToken(UserEntity userEntity) {

        String token = "";
        token = JWT.create().withAudience(userEntity.getId())
                .sign(Algorithm.HMAC256(IdUtil.simpleUUID()));
        return token;
    }

    @Override
    public Boolean saveToken(TokenEntity tokenEntity) {
        TokenEntity entity = tokenRepository.save(tokenEntity);
        return StrUtil.isNotBlank(entity.getUserId());
    }

    @Override
    public TokenEntity findByUserId(String id) {
        Optional<TokenEntity> optionalTokenEntity = tokenRepository.findByUserId(id);
        return optionalTokenEntity.orElse(null);
    }

    @Override
    public void deleteAllToken() {
        tokenRepository.deleteAll();

    }

    @Override
    public Boolean deleteToken(String token) {
        String userId = JWT.decode(token).getAudience().get(0);
        Optional<TokenEntity> entityOptional = tokenRepository.findByUserId(userId);
        entityOptional.ifPresent(tokenEntity -> tokenRepository.delete(tokenEntity));
        return true;
    }
}
