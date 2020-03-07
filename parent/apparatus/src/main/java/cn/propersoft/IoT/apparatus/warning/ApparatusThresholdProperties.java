package cn.propersoft.IoT.apparatus.warning;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "internet.of.things.threshold")
public class ApparatusThresholdProperties {

    private Double dianYaMin = 0d;
    private Double dianYaMax = 20000d;
    private Double dianLiuMin = 0d;
    private Double dianLiuMax = 20000d;
    /**
     * 插入式温度
     */
    private Double cWenDuMin = 0d;
    private Double cWenDuMax = 20000d;
    private Double co2Min = 0d;
    private Double co2Max = 20000d;
    private Double shuiQinMin = 0d;
    private Double shuiQinMax = 20000d;
    /**
     * 温度湿度表的温度
     */
    private Double wsWenDuMin = 0d;
    private Double wsWenDuMax = 20000d;
    private Double wsShiDuMin = 0d;
    private Double wsShiDuMax = 20000d;
    private Double yaLiMin = 0d;
    private Double yaLiMax = 20000d;
    private Double yeWeiMin = 0d;
    private Double yeWeiMax = 20000d;


}
