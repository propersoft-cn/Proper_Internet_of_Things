package cn.propersoft.IoT.auth.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import cn.propersoft.IoT.auth.service.AuthService;
import cn.propersoft.IoT.auth.service.TokenService;
import cn.propersoft.IoT.exception.BizException;
import cn.propersoft.IoT.exception.CommonEnum;
import cn.propersoft.IoT.user.entity.UserEntity;
import cn.propersoft.IoT.user.service.UserService;
import cn.propersoft.IoT.user.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;


    @Override
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
                userVO = Convert.convert(UserVO.class, user);
                userVO.setToken(token);
                return userVO;
            }
        }
    }
}
