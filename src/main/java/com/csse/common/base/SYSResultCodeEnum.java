package com.csse.common.base;



/**
 * @Description: 系统错误码
 * @Author: SHIHAIZHOU
 * @Date: Created in 下午 5:30 2019/4/2 0002
 * @Modificd by:
 */
public enum SYSResultCodeEnum {
    /**
     * sys
     */
    SUCCESS("成功", 0),
    FAIL("失败", 1);

    private String msg;
    private int code;

    SYSResultCodeEnum(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }


    /**
     * 根据value值 获取key值
     *
     * @param value
     * @return
     */
    public static String getString(int value) {
        SYSResultCodeEnum[] values = SYSResultCodeEnum.values();
        for (SYSResultCodeEnum alarmStatus : values) {
            if (alarmStatus.getCode() == value) {
                return alarmStatus.getMsg();
            }
        }
        return null;
    }

    /**
     * 根据value值 获取key值
     *
     * @param value
     * @return
     */
    public static boolean isContainValue(int value) {
        SYSResultCodeEnum[] values = SYSResultCodeEnum.values();
        for (SYSResultCodeEnum alarmStatus : values) {
            if (alarmStatus.getCode() == value) {
                return true;
            }
        }
        return false;
    }


}
