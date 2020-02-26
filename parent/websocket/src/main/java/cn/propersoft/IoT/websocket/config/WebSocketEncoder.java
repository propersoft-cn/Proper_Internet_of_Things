package cn.propersoft.IoT.websocket.config;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class WebSocketEncoder implements Encoder.Text<Object> {
    @Override
    public String encode(Object o) throws EncodeException {
        JSONObject jsonObject = JSONUtil.parseObj(o);
        return jsonObject.toString();
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
