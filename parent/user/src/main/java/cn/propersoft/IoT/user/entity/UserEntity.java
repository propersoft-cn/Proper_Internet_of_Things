package cn.propersoft.IoT.user.entity;

import cn.propersoft.IoT.jpa.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "zl_user")
public class UserEntity extends BaseEntity {

    @Getter
    @Setter
    @Column
    private String username;
    @Getter
    @Setter
    @Column
    private String password;
    @Getter
    @Setter
    @Column
    private String name;
    @Getter
    @Setter
    @Column
    private String role;


}
