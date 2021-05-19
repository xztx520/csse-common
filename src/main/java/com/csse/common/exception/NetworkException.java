package com.csse.common.exception;

/***
 * shihaizhou
 */
@SuppressWarnings("serial")
public class NetworkException extends PlatformException {
    public NetworkException(int errorCode, Object... args) {
        super(errorCode, args);
    }

    public NetworkException(int errorCode, String msg) {
        super(errorCode, msg);
    }

}