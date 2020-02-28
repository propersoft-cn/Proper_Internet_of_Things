package cn.propersoft.IoT.demo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.propersoft.IoT.demo.entity.Demo2Entity;
import cn.propersoft.IoT.demo.entity.DemoEntity;
import cn.propersoft.IoT.demo.repository.Demo2Repository;
import cn.propersoft.IoT.demo.repository.DemoRepository;
import cn.propersoft.IoT.demo.service.DemoService;
import cn.propersoft.IoT.cache.service.RedisHelper;
import cn.propersoft.IoT.demo.vo.DemoVO;
import cn.propersoft.IoT.exception.BizException;
import cn.propersoft.IoT.exception.CommonEnum;
import cn.propersoft.IoT.core.utils.MyBeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class DemoServiceImpl implements DemoService {

    private static final Logger logger = LoggerFactory.getLogger(DemoServiceImpl.class);

    @Autowired
    private DemoRepository demoRepository;

    @Autowired
    private Demo2Repository demo2Repository;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisHelper redisHelper;


    @Override
    public List<DemoVO> findAll() {
        List<DemoEntity> demoEntityList = demoRepository.findAll();
        return (List<DemoVO>) MyBeanUtils.convert(demoEntityList, DemoVO.class, "id", "name");
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void add(DemoVO demoVO) {
        DemoEntity demoEntity = new DemoEntity();
        Demo2Entity demo2Entity = new Demo2Entity();

        BeanUtil.copyProperties(demoVO, demoEntity);
        BeanUtil.copyProperties(demoVO, demo2Entity);

        demoRepository.save(demoEntity);
        //int a = 1 / 0;
        demo2Repository.save(demo2Entity);
    }


    @Override
    public void addRedisDemo(String key, String value) {
        redisHelper.valuePut(key, value);
    }

    @Override
    public String getRedisDemo(String key) {
        return Convert.toStr(redisHelper.getValue(key));
    }

    @Override
    public DemoEntity findOneByOrderById() {
        Optional<DemoEntity> demoEntity = demoRepository.findOneByOrderById();
        if (demoEntity.isPresent()) {
            DemoEntity entity = demoEntity.get();
            return entity;
        }
        throw new BizException(CommonEnum.BUSINESS_ERROR);
    }


}
