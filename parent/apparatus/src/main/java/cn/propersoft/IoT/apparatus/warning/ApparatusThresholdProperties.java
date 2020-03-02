package cn.propersoft.IoT.apparatus.warning;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "internet.of.things.threshold")
public class ApparatusThresholdProperties {

    private Double dianYaMin = 0d;
    private Double dianYaMax = 0d;
    private Double dianLiuMin = 0d;
    private Double dianLiuMax = 0d;
    /**
     * 插入式温度
     */
    private Double cWenDuMin = 0d;
    private Double cWenDuMax = 0d;
    private Double co2Min = 0d;
    private Double co2Max = 0d;
    private Double shuiQinMin = 0d;
    private Double shuiQinMax = 0d;
    /**
     * 温度湿度表的温度
     */
    private Double wsWenDuMin = 0d;
    private Double wsWenDuMax = 0d;
    private Double wsShiDuMin = 0d;
    private Double wsShiDuMax = 0d;
    private Double yaLiMin = 0d;
    private Double yaLiMax = 0d;
    private Double yeWeiMin = 0d;
    private Double yeWeiMax = 0d;


}
