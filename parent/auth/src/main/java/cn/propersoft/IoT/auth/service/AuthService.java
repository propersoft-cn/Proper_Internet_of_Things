package cn.propersoft.IoT.auth.service;


import cn.propersoft.IoT.user.vo.UserVO;

public interface AuthService {

    UserVO login(UserVO userVO);

}
