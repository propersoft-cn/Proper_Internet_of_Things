package cn.propersoft.IoT.demo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "DemoVO")
public class DemoVO {
    @ApiModelProperty(value = "DemoId")
    private String id;
    @ApiModelProperty(value = "Demo名称")
    private String name;
    @ApiModelProperty(value = "Demo名称2")
    private String name2;
    @ApiModelProperty(value = "Demo名称3")
    private String name3;

}
