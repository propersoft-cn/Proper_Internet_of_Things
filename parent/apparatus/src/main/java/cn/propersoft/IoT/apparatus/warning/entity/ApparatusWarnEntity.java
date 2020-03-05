package cn.propersoft.IoT.apparatus.warning.entity;


import cn.propersoft.IoT.jpa.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "apparatus_warn")
@Entity
@Data
public class ApparatusWarnEntity extends BaseEntity {

    private static final long serialVersionUID = 6152693145627780235L;

    /**
     * 楼层
     */
    @Column
    private String floor;

    @Column
    private String deviceId;

    /**
     * 传感器
     */
    @Column
    private String sensor;

    @Column
    private String warnText;

    @Column
    private Double threshold;

    @Column
    private Double realValue;

    @Column
    private Date createTime;

    @Column
    private String other;
}
