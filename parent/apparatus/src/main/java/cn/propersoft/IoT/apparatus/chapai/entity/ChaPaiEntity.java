package cn.propersoft.IoT.apparatus.chapai.entity;

import cn.propersoft.IoT.jpa.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "chapai")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChaPaiEntity extends BaseEntity {

    private static final long serialVersionUID = 7594420331401691569L;

    @Column(nullable = false, length = 32)
    private String equipmentName;

    @Column(precision = 6, scale = 0)
    private Double dianya;

    @Column(precision = 6, scale = 0)
    private Double dianliu;

    @Column(precision = 6, scale = 0)
    private Double dianliang;

    @Column(nullable = false)
    private Date addTime;

}
