package cn.propersoft.IoT.apparatus.charushi.repository;

import cn.propersoft.IoT.apparatus.charushi.entity.ChaRuShiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChaRuShiRepository extends JpaRepository<ChaRuShiEntity, String> {


}
