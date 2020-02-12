package cn.propersoft.IoT.demo.repository;

import cn.propersoft.IoT.demo.entity.DemoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemoRepository extends JpaRepository<DemoEntity,String> {
}
