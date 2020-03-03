package cn.propersoft.IoT.auth.entity;

import cn.propersoft.IoT.jpa.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "iot_user")
public class UserEntity extends BaseEntity {

    private static final long serialVersionUID = -6240146766010214651L;
    @Column(unique = true)
    private String username;
    @Column
    private String password;
    @Column
    private String name;
    @Column
    private String role;


}
