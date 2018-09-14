package com.guye.sun.common.exception;


import com.guye.sun.common.exception.enums.CustomResponseCode;

import java.text.MessageFormat;

/**
 * 自定义异常。
 */
public class DefinedException extends BaseException {

    private static final long serialVersionUID = -8994969925213820584L;

    private static final int BAD_REQUEST = 400;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int RESOURCE_REPLICATION = 409;

    /**
     * 规范的HTTP状态响应码,如400/403/503等
     */
    private transient int code;
    /**
     * 自定义返回码
     */
    private int customCode;

    public static DefinedException badRequest(String message, Object... arguments) {
        return new DefinedException(BAD_REQUEST, MessageFormat.format(message, arguments));
    }

    public static DefinedException badRequest(String message) {
        return new DefinedException(BAD_REQUEST, message);
    }

    public static DefinedException badRequest(CustomResponseCode responseCode) {
        return new DefinedException(BAD_REQUEST, responseCode.code(), responseCode.message());
    }

    public static DefinedException badRequest(Integer customCode, String message) {
        return new DefinedException(BAD_REQUEST, customCode, message);
    }

    public static DefinedException notFound(String message, Object... arguments) {
        return new DefinedException(NOT_FOUND, MessageFormat.format(message, arguments));
    }

    public static DefinedException notFound(String message) {
        return new DefinedException(NOT_FOUND, message);
    }

    public static DefinedException notFound(CustomResponseCode responseCode) {
        return new DefinedException(NOT_FOUND, responseCode.code(), responseCode.message());
    }

    public static DefinedException notFound(Integer customCode, String message) {
        return new DefinedException(NOT_FOUND, customCode, message);
    }

    public static DefinedException resourceReplication(String message) {
        return new DefinedException(RESOURCE_REPLICATION, message);
    }

    public static DefinedException forbidden() {
        return new DefinedException(FORBIDDEN, "登录过期,请重新登录");
    }

    public DefinedException(String message) {
        super(message);
        this.setCustomCode(BAD_REQUEST);
        this.setCode(BAD_REQUEST);
    }

    public DefinedException(String message, Throwable e) {
        super(message, e);
        this.setCustomCode(BAD_REQUEST);
        this.setCode(BAD_REQUEST);
    }

    public DefinedException(int code, String message) {
        super(message);
        this.setCustomCode(code);
        this.setCode(code);
    }

    public DefinedException(int code, int customcode, String message) {
        super(message);
        this.setCustomCode(customcode);
        this.setCode(code);
    }

    public DefinedException(int code, String message, Throwable t) {
        super(message, t);
        this.code = code;
    }

    public ErrorResponseEntity toErrorResponseEntity() {
        return new ErrorResponseEntity(this.customCode, this.getMessage());
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCustomCode() {
        return customCode;
    }

    public void setCustomCode(int customcode) {
        this.customCode = customcode;
    }
}
