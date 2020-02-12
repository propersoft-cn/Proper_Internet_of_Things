package cn.propersoft.IoT.user.service;

import cn.propersoft.IoT.user.entity.UserEntity;
import cn.propersoft.IoT.user.vo.UserVO;

import java.util.List;

public interface UserService {

    //查询全部用户
    List<UserVO> findAll();

    //根据ids获取用户
    List<UserVO> findByIds(String ids);

    //根据条件筛选用户

    //新建用户
    UserVO createUser(UserVO userVO);

    //修改用户密码
    Boolean update(String id, String oldPassWord, String newPassWord);

    //删除用户
    Boolean deleteUser(String ids);

    UserEntity findUserById(String userId);

    UserEntity findByUsername(String username);
    //TODO 分页获取用户

}
