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
@Table(name = "iot_token")
public class TokenEntity extends BaseEntity {
    private static final long serialVersionUID = -5828343973317088616L;

    @Column(unique = true, length = 64)
    private String userId;

    @Column(length = 255)
    private String token;


}
