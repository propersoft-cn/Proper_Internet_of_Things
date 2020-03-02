package cn.propersoft.IoT.apparatus.warning.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ApparatusWarnVO implements Serializable {

    private static final long serialVersionUID = 1884665586926237258L;

    private String deviceId;

    private String warnText;

    private Double threshold;

    private Double realValue;

    private Date createTime;


}
