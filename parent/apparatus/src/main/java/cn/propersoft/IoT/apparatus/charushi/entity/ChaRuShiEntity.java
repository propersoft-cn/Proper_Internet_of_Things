package cn.propersoft.IoT.apparatus.charushi.entity;

import cn.propersoft.IoT.jpa.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "charushi")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChaRuShiEntity extends BaseEntity {
    private static final long serialVersionUID = -7615418619504887257L;

    @Column(nullable = false, length = 32)
    private String equipmentName;

    @Column(precision = 6, scale = 2)
    private Double wendu;

    @Column(nullable = false)
    private Date addTime;

}
