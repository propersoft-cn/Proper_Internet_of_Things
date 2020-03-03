package cn.propersoft.IoT.auth.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import cn.propersoft.IoT.auth.entity.UserEntity;
import cn.propersoft.IoT.auth.repository.UserRepositroy;
import cn.propersoft.IoT.auth.service.UserService;
import cn.propersoft.IoT.exception.BizException;
import cn.propersoft.IoT.exception.CommonEnum;
import cn.propersoft.IoT.auth.vo.UserVO;
import cn.propersoft.IoT.core.utils.MyBeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepositroy userRepositroy;

    /**
     * 查询全部用户
     */
    @Override
    public List<UserVO> findAll() {
        List<UserEntity> all = userRepositroy.findAll();
        return (List<UserVO>) MyBeanUtils.convert(all, UserVO.class);
    }

    @Override
    public List<UserVO> findByIds(String ids) {
        List<String> idList = StrUtil.split(ids, StrUtil.C_COMMA);
        List<UserEntity> allById = userRepositroy.findAllById(idList);
        return (List<UserVO>) MyBeanUtils.convert(allById, UserVO.class);
    }

    @Override
    public UserVO createUser(UserVO userVO) {
        Optional<UserEntity> one = userRepositroy.findByUsername(userVO.getUsername());
        if (one.isPresent()) {
            throw new BizException(CommonEnum.USER_ALREADY_EXISTS);
        } else {
            UserEntity userEntity = new UserEntity();
            BeanUtil.copyProperties(userVO, userEntity);
            Digester md5 = new Digester(DigestAlgorithm.MD5);
            String digestHex = md5.digestHex(userEntity.getPassword());
            userEntity.setPassword(digestHex);
            UserEntity save = userRepositroy.save(userEntity);
            userVO = new UserVO();
            BeanUtil.copyProperties(save, userVO);
            return userVO;
        }
    }

    @Override
    public Boolean update(String id, String oldPassWord, String newPassWord) {
        Optional<UserEntity> optional = userRepositroy.findById(id);
        if (optional.isPresent()) {
            UserEntity userEntity = optional.get();
            String password = userEntity.getPassword();
            Digester md5 = new Digester(DigestAlgorithm.MD5);
            String digestHex = md5.digestHex(oldPassWord);
            if (StrUtil.equals(password, digestHex)) {
                userEntity.setPassword(md5.digestHex(newPassWord));
                userRepositroy.save(userEntity);
                return true;
            } else {
                throw new BizException(CommonEnum.USER_PASSWORD_WRONG);
            }
        } else {
            throw new BizException(CommonEnum.BUSINESS_ERROR);
        }

    }

    @Override
    public Boolean deleteUser(String ids) {
        try {
            List<String> idList = StrUtil.split(ids, StrUtil.C_COMMA);
            userRepositroy.deleteByIdIn(idList);
            return true;
        } catch (Exception e) {
            throw new BizException(CommonEnum.BUSINESS_ERROR);
        }
    }

    @Override
    public UserEntity findUserById(String userId) {
        Optional<UserEntity> optional = userRepositroy.findById(userId);
        return optional.orElse(null);
    }

    @Override
    public UserEntity findByUsername(String username) {
        Optional<UserEntity> optionalUserEntity = userRepositroy.findByUsername(username);
        return optionalUserEntity.orElse(null);
    }
}
