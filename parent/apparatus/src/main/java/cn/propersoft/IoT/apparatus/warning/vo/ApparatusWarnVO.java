package cn.propersoft.IoT.apparatus.warning.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ApparatusWarnVO implements Serializable {

    private static final long serialVersionUID = 1884665586926237258L;

    /**
     * 楼层
     */
    private String floor;

    private String deviceId;

    /**
     * 传感器
     */
    private String sensor;

    private String warnText;

    private Double threshold;

    private Double realValue;

    private Date createTime;

    private String other;


}
