package cn.propersoft.IoT.apparatus.shuiqin.entity;

import cn.propersoft.IoT.jpa.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "shuiqin")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShuiQinEntity extends BaseEntity {

    private static final long serialVersionUID = 8225725235842014408L;

    @Column(nullable = false, length = 32)
    private String equipmentName;

    @Column
    private String shuiqin;

    @Column(nullable = false)
    private Date addTime;


}
