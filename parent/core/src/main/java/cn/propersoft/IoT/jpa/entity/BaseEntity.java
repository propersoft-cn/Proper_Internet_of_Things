package cn.propersoft.IoT.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.UUID;

@MappedSuperclass
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = -1692052275576005610L;
    @Id
    @Column(
            length = 64
    )
    private String id;

    public BaseEntity() {
        this.setId((String)null);
    }

    public void setId(String id) {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return this.id;
    }
}
