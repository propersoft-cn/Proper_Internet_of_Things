package cn.propersoft.IoT.apparatus.analysis.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class StatisticalAnalysisVO implements Serializable {

    private static final long serialVersionUID = 4070722274798529566L;
    private String date;

    private String value;

}
