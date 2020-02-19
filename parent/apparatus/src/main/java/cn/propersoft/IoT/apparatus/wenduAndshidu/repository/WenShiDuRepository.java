package cn.propersoft.IoT.apparatus.wenduAndshidu.repository;

import cn.propersoft.IoT.apparatus.wenduAndshidu.entity.WenShiDuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WenShiDuRepository extends JpaRepository<WenShiDuEntity, String> {


}
