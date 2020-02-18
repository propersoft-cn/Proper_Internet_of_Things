package cn.propersoft.IoT.apparatus.yali.repository;

import cn.propersoft.IoT.apparatus.yali.entity.YaliEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface YaliRepository extends JpaRepository<YaliEntity, String> {


    List<YaliEntity> findOneByOrderByAddTimeDesc();
}
