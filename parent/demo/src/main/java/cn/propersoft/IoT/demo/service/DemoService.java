package cn.propersoft.IoT.demo.service;

import cn.propersoft.IoT.demo.vo.DemoVO;

import java.util.List;

public interface DemoService {


    List<DemoVO> findAll();

    void add(DemoVO demoVO);

    void addRedisDemo(String key,String value);

    String getRedisDemo(String key);

}
