/*
 *  Copyright (c)  2004-2009 Aspire Info, Inc.
 *  All rights reserved.
 *
 *  This software is the confidential and proprietary information of Aspire
 *  Info, Inc. ("Confidential Information"). You shall not
 *  disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into
 *  with Aspire.
 */
package com.csse.common.exception;

import java.text.MessageFormat;

/**
 * 业务逻辑异常 。
 * <p>
 * 业务层的每个方法都可能抛出该异常，该异常一般是在Action/Controller层的入口方法中捕获处理，
 * 业务层没有必须的理由不要处理该异常。
 * </p>
 * <p>
 * 注意：PlatformException类及子类使用时，最好使用PlatformException(String errorCode, Object ... args)构造函数，
 * errorCode要在message_*.xml文件中定义。没有充分的理由，请不要使用PlatformException(String message, Throwable cause)
 * 构造函数。
 *
 * @author 施海洲
 */
public class PlatformException extends Exception {
    private static final long serialVersionUID = 2678145046723789238L;
    private int errorCode = 0;
    private Object[] args = null;
    private String message = null;

    /**
     * @return the errorCode
     */
    public int getErrorCode() {
        return errorCode;
    }

    /**
     * @return the args
     */
    public Object[] getArgs() {
        return args;
    }

    /**
     * 返回详细的错误消息。
     */
    @Override
    public String getMessage() {
        return this.message;
    }

    /**
     * 构造函数
     */
    public PlatformException(int errorCode, Object... args) {
        super();
        this.errorCode = errorCode;
        this.args = args;

        message = MessageFormat.format(MessageHelper.getInstance().getMessage(errorCode + ""), args);
    }

    /**
     * @param errorCode
     * @param msg
     */
    public PlatformException(int errorCode, String msg) {
        super();
        this.errorCode = errorCode;
        this.message = msg;
    }

    /**
     * @param message
     * @param cause
     */
    public PlatformException(String message, Throwable cause) {
        super(cause);

        this.message = message;
    }

}
