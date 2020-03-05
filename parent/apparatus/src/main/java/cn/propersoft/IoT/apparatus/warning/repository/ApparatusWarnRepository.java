package cn.propersoft.IoT.apparatus.warning.repository;

import cn.propersoft.IoT.apparatus.warning.entity.ApparatusWarnEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApparatusWarnRepository extends JpaRepository<ApparatusWarnEntity, String> {
}
