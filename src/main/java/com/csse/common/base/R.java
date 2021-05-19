package com.csse.common.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 响应信息主体
 *
 * @param <T>
 * @author shihaizhou
 */

public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    @JSONField(ordinal = 1)
    private int rsltcode = SYSResultCodeEnum.SUCCESS.getCode();
    @JSONField(ordinal = 2)
    private String rsltmsg = SYSResultCodeEnum.SUCCESS.getMsg();
    @JSONField(ordinal = 3)
    private T data;

    public R() {
        super();
    }

    public R(T data) {
        super();
        this.data = data;
    }

    public R(int rsltcode, String rsltmsg) {
        super();
        this.rsltcode = rsltcode;
        this.rsltmsg = rsltmsg;
    }

    public R(int rsltcode, String rsltmsg, T data) {
        super();
        this.rsltcode = rsltcode;
        this.rsltmsg = rsltmsg;
        this.data = data;
    }

    public int getRsltcode() {
        return rsltcode;
    }

    public void setRsltcode(int rsltcode) {
        this.rsltcode = rsltcode;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getRsltmsg() {
        return rsltmsg;
    }

    public void setRsltmsg(String rsltmsg) {
        this.rsltmsg = rsltmsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}