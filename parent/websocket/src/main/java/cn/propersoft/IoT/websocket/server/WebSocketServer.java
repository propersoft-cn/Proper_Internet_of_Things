package cn.propersoft.IoT.websocket.server;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.propersoft.IoT.exception.BizException;
import cn.propersoft.IoT.exception.CommonEnum;
import cn.propersoft.IoT.websocket.config.WebSocketEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/websocket/{userId}", encoders = {WebSocketEncoder.class})
@Component
public class WebSocketServer {


    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketServer.class);
    /**
     * 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
     */
    private static int onlineCount = 0;
    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
     */
    private static ConcurrentHashMap<String, WebSocketServer> webSocketMap = new ConcurrentHashMap<>();
    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;
    /**
     * 接收userId
     */
    private String userId = "";

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        this.session = session;
        this.userId = userId;
        if (webSocketMap.containsKey(userId)) {
            webSocketMap.remove(userId);
            webSocketMap.put(userId, this);
        } else {
            webSocketMap.put(userId, this);
            addOnlineCount();
        }
        LOGGER.info("用户连接:" + userId + ",当前在线人数为:" + getOnlineCount());
        try {
            sendMessage("连接成功");
        } catch (IOException e) {
            LOGGER.error("用户:" + userId + ",网络异常!!!!!!");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if (webSocketMap.containsKey(userId)) {
            webSocketMap.remove(userId);
            subOnlineCount();
        }
        LOGGER.info("用户退出:" + userId + ",当前在线人数为:" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        LOGGER.info("用户消息:" + userId + ",报文:" + message);
        if (StrUtil.isNotBlank(message)) {
            try {
                //解析发送的报文
                JSONObject jsonObject = JSONUtil.parseObj(message);
                jsonObject.put("fromUserId", this.userId);
                String toUserId = jsonObject.getStr("toUserId");
                if (StrUtil.isNotBlank(toUserId) && webSocketMap.containsKey(toUserId)) {
                    webSocketMap.get(toUserId).sendMessage(jsonObject.toString());
                } else {
                    LOGGER.error("请求的userId:" + toUserId + "不在该服务器上");
                }
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
                throw new BizException(CommonEnum.BUSINESS_ERROR);
            }
        }
    }

    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        LOGGER.error("用户错误:" + this.userId + ",原因:" + error.getMessage());
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    public void sendObject(Object o) throws IOException {
        try {
            this.session.getBasicRemote().sendObject(o);
        } catch (EncodeException e) {
            throw new BizException(CommonEnum.BUSINESS_ERROR, e);
        }
    }


    /**
     * 发送自定义消息
     */
    public static void sendInfo(String message, @PathParam("userId") String userId) {
        LOGGER.info("发送消息到:" + userId + "，报文:" + message);
        if (StrUtil.isNotBlank(userId) && webSocketMap.containsKey(userId)) {
            try {
                webSocketMap.get(userId).sendMessage(message);
            } catch (IOException e) {
                throw new BizException(CommonEnum.BUSINESS_ERROR, e);
            }
        } else {
            LOGGER.error("用户" + userId + ",不在线！");
            throw new BizException(CommonEnum.USER_NOTFOUNT);
        }
    }

//    public static void sendObject(String message, @PathParam("userId") String userId) throws IOException {
//        LOGGER.info("发送消息到:" + userId + "，报文:" + message);
//        if (StrUtil.isNotBlank(userId) && webSocketMap.containsKey(userId)) {
//            webSocketMap.get(userId).sendMessage(message);
//        } else {
//            LOGGER.error("用户" + userId + ",不在线！");
//            throw new BizException(CommonEnum.USER_NOTFOUNT);
//        }
//    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }

}
