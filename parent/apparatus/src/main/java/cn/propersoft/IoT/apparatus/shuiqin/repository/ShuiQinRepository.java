package cn.propersoft.IoT.apparatus.shuiqin.repository;

import cn.propersoft.IoT.apparatus.shuiqin.entity.ShuiQinEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShuiQinRepository extends JpaRepository<ShuiQinEntity, String> {


}
