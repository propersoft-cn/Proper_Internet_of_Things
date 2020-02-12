package cn.propersoft.IoT.auth.service.impl;

import cn.hutool.core.convert.Convert;
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
    public UserVO login(UserEntity userEntity) {
        UserEntity user = userService.findByUsername(userEntity.getUsername());
        if (user == null) {
            throw new BizException(CommonEnum.USER_NOTFOUNT);
        } else {
            if (!user.getPassword().equals(userEntity.getPassword())) {
                throw new BizException(CommonEnum.USER_PASSWORD_WRONG);
            } else {
                String token = tokenService.getToken(user);
                UserVO userVO = Convert.convert(UserVO.class, user);
                userVO.setToken(token);
                return userVO;
            }
        }
    }
}
