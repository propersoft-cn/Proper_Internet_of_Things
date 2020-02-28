package cn.propersoft.IoT.demo.entity;

import cn.propersoft.IoT.jpa.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "iot_demo")
@Entity
public class DemoEntity extends BaseEntity {

    private static final long serialVersionUID = -3174267725861733215L;
    @Getter
    @Setter
    @Column
    private String name;

    @Getter
    @Setter
    @Column
    private String name2;

}
