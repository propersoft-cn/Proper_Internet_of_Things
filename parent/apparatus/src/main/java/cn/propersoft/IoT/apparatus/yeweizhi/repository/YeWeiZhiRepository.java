package cn.propersoft.IoT.apparatus.yeweizhi.repository;

import cn.propersoft.IoT.apparatus.yeweizhi.entity.YeWeiZhiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YeWeiZhiRepository extends JpaRepository<YeWeiZhiEntity, String> {


}
