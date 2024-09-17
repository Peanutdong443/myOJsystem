package com.dongd.quesbank.utils;

public enum ResultCodeEnum {
    SUCCESS(200, "success"),
    TOKEN_EXPIRED(401, "tokenExpired"),
    USERNAME_ERROR(501, "usernameError"),
    PASSWORD_ERROR(503, "passwordError"),
    NOT_LOGIN(504, "notLogin"),
    USERID_EXIST(505, "userIdExist"),
    USERTYPE_FAULT(506, "userTypeFault"),
    RESOURCE_FIND_ERROR(507, "resourceFindError");

    private Integer code;
    private String message;
    private ResultCodeEnum(Integer code, String message) {
        this.code=code;
        this.message=message;
    }
    public Integer getCode() {return code;}
    public String getMessage() {return message;}

}
