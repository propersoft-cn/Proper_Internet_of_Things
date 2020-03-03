package cn.propersoft.IoT.websocket.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 用来封装WebSocket的模型
 */

@Data
public class WebSocketModel implements Serializable {
    private static final long serialVersionUID = 4716759582511821134L;

    private Long createTime;

    private String clientId;

    private Object data;
}
