package cn.propersoft.IoT.demo.entity;

import cn.propersoft.IoT.jpa.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "zl_demo2")
@Entity
public class Demo2Entity extends BaseEntity {

    @Getter
    @Setter
    @Column
    private String name;

    @Getter
    @Setter
    @Column
    private String name2;


}
