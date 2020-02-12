package cn.propersoft.IoT.demo.repository;

import cn.propersoft.IoT.demo.entity.Demo2Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Demo2Repository extends JpaRepository<Demo2Entity,String> {
}
