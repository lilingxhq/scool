package com.man.common.enums;


public enum SysErrorEnums implements IErrorCode {
    CODE_NOTVALIDATE (202,"参数缺失"),
    /**token非法*/
    TOKEN_INVALID(400,"token非法!"),
	/**token失效*/
	TOKEN_EXPIRED(1402,"token过期!"),
    AUTHORIZATION_NOT_BLANK(401,"Authorization不能为空!"),
    SYS_HEADER_ERROR(411,"请求头不合法"),
    SYS_ERROR(500,"未知异常，请联系管理员"),
    NO_EXIST_ACCOUNT(504,"该账号不存在!"),
    // dsf
    ERROR_ACTORPWD(505,"账号或密码错误!");
    private Integer errorCode;
    private String errorMessage;
    
    private SysErrorEnums(Integer errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
    //根据code返回 enum
    public static SysErrorEnums getEnumByCode(Integer code) {
        for (SysErrorEnums enm : SysErrorEnums.values()) {
            if (enm.getErrorCode().equals(code)) {
                return enm;
            }
        }
        return null;
    }
    @Override
    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }
    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}