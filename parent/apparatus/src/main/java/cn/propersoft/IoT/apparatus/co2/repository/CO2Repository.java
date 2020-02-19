package cn.propersoft.IoT.apparatus.co2.repository;

import cn.propersoft.IoT.apparatus.co2.entity.CO2Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CO2Repository extends JpaRepository<CO2Entity, String> {


}
