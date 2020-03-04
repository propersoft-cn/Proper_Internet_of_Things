package cn.propersoft.IoT.auth.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Assert;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import cn.propersoft.IoT.auth.entity.TokenEntity;
import cn.propersoft.IoT.auth.service.AuthService;
import cn.propersoft.IoT.auth.service.TokenService;
import cn.propersoft.IoT.exception.BizException;
import cn.propersoft.IoT.exception.CommonEnum;
import cn.propersoft.IoT.auth.entity.UserEntity;
import cn.propersoft.IoT.auth.service.UserService;
import cn.propersoft.IoT.auth.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;


    @Override
    @Transactional(rollbackOn = BizException.class)
    public UserVO login(UserVO userVO) {
        UserEntity userEntity = Convert.convert(UserEntity.class, userVO);
        UserEntity user = userService.findByUsername(userEntity.getUsername());
        if (user == null) {
            throw new BizException(CommonEnum.USER_NOTFOUNT);
        } else {
            Digester md5 = new Digester(DigestAlgorithm.MD5);
            String digestHex = md5.digestHex(userEntity.getPassword());
            if (!user.getPassword().equals(digestHex)) {
                throw new BizException(CommonEnum.USER_PASSWORD_WRONG);
            } else {
                String token = tokenService.getToken(user);
                TokenEntity tokenEntity = tokenService.findByUserId(user.getId());
                if (null == tokenEntity) {
                    tokenEntity = new TokenEntity();
                    tokenEntity.setUserId(user.getId());
                    tokenEntity.setToken(token);
                } else {
                    tokenEntity.setToken(token);
                }
                if (!tokenService.saveToken(tokenEntity)) {
                    throw new BizException(CommonEnum.INTERNAL_SERVER_ERROR);
                }
                userVO = Convert.convert(UserVO.class, user);
                userVO.setToken(token);
                return userVO;
            }
        }
    }
}
