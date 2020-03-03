package cn.propersoft.IoT.exception;

public enum CommonEnum implements BaseErrorInfoInterface {

    // 数据操作错误定义
    SUCCESS("200", "成功!"),
    BODY_NOT_MATCH("400", "请求的数据格式不符!"),
    SIGNATURE_NOT_MATCH("401", "请求的数字签名不匹配!"),
    SIGNATURE_INVALID("401", "签名失效，请重新登陆!"),
    NOT_FOUND("404", "未找到该资源!"),
    INTERNAL_SERVER_ERROR("500", "服务器内部错误!"),
    BUSINESS_ERROR("500","业务异常"),
    SERVER_BUSY("503", "服务器正忙，请稍后再试!"),
    USER_NOTFOUNT("500","用户不存在"),
    USER_ALREADY_EXISTS("500","用户已存在"),
    USER_PASSWORD_WRONG("500","密码错误");

    /**
     * 错误码
     */
    private String resultCode;

    /**
     * 错误描述
     */
    private String resultMsg;

    CommonEnum(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    @Override
    public String getResultCode() {
        return resultCode;
    }

    @Override
    public String getResultMsg() {
        return resultMsg;
    }
}
