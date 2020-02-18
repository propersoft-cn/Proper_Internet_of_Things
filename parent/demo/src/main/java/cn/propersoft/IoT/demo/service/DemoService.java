package cn.propersoft.IoT.demo.service;

import cn.propersoft.IoT.demo.entity.DemoEntity;
import cn.propersoft.IoT.demo.vo.DemoVO;

import java.util.List;
import java.util.Optional;

public interface DemoService {


    List<DemoVO> findAll();

    void add(DemoVO demoVO);

    void addRedisDemo(String key,String value);

    String getRedisDemo(String key);

    DemoEntity findOneByOrderById();
}
