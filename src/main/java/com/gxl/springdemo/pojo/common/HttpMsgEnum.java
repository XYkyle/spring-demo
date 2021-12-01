package com.gxl.springdemo.pojo.common;

/**
 * @author heimu
 * @Date 2021/9/8 下午7:47
 * @Version 1.0
 * 保证这个类唯一,防止code意义不唯一
 */
public enum HttpMsgEnum {

    //200
    SUCCESS("200", "操作成功"),

    //6xx
    NO_PERMISSION("600", "没有权限"),
    UNAUTHORIZED_ACCESS("601", "非法访问"),
    NO_ROLE("602", "用户无角色"),
    NO_MATCH_ROLE("603", "没有匹配角色"),

    //7xx
    PARAM_ILLEGAL("700", "参数异常"),
    SERVICE_ERROR("701","业务异常"),

    //8xx
    NOT_LOGIN("800", "用户未登录"),
    REMOTE_LOGIN("801", "异地登录"),
    RE_LOGIN("802", "登录已失效，请重新登录"),
    FAILED("810", "业务操作失败"),

    //9xx
    NOT_KNOW_ERROR("999","未知异常")
    ;
    private String code;
    private String msg;

    HttpMsgEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static HttpMsgEnum getEnum(String code) {
        for (HttpMsgEnum value : HttpMsgEnum.values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }

}
