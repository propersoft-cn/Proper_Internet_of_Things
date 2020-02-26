package cn.propersoft.IoT.apparatus.co2.entity;

import cn.propersoft.IoT.jpa.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "eryanghuatan")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CO2Entity extends BaseEntity {

    private static final long serialVersionUID = 8225725235842014408L;

    @Column(nullable = false, length = 32)
    private String equipmentName;

    @Column(precision = 6, scale = 2)
    private Double co2Num;

    @Column(nullable = false)
    private Date addTime;


}
