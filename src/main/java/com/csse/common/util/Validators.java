package com.csse.common.util;


import com.csse.common.exception.PlatformException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

/**
 * 检查类型处理类
 */
public class Validators {
    /**
     * 检测一个字符串不能为空。
     *
     * @param value    - 字符串的值
     * @param itemName - 要检查项目的名称
     * @throws PlatformException
     */
    public static void checkNotNullAndEmpty(String value, String itemName) throws PlatformException {
        if (value == null || value.trim().equals("") || value.trim().equals("null")) {
            throw new PlatformException(9001, (Object) itemName);
        }
    }
    /**
     * 检测一个字符串不能非法。
     *
     * @param value    - 字符串的值
     * @param itemName - 要检查项目的名称
     * @throws PlatformException
     */
    public static void checkSpecial(String value, String itemName) throws PlatformException {
        if (!value.matches("[\\da-zA-z]+")) {
            throw new PlatformException(9011, (Object)itemName);
        }
    }

    /**
     * 检测一个字符串的最大字节数是否超过指定长度。
     *
     * @param value     - 字符串的值
     * @param maxLength - 指定最大字节数
     * @param itemName  - 要检查项目的名称
     * @throws PlatformException
     */
    public static void checkMaxByteLength(String value, int maxLength, String itemName) throws PlatformException {
        checkNotNullAndEmpty(value,itemName);
        if (value.getBytes().length > maxLength) {
            throw new PlatformException(9003,  (Object)itemName, maxLength);
        }
    }

    /**
     * 检测一个字符串是指定长度。
     *
     * @param value     - 字符串的值
     * @param maxLength - 指定最大字节数
     * @param itemName  - 要检查项目的名称
     * @throws PlatformException
     */
    public static void checkEqualByteLength(String value, int maxLength, String itemName) throws PlatformException {
        checkNotNullAndEmpty(value, itemName);
        if (value.getBytes().length != maxLength) {
            throw new PlatformException(9002,  (Object)itemName, maxLength);
        }
    }

    /**
     * 检查一个字符串必须是数字
     *
     * @param value
     * @param itemName
     * @throws PlatformException
     */
    public static void checkIsNumeric(String value, String itemName) throws PlatformException {
        if (!value.matches("\\d*")) {
            throw new PlatformException(9004,  (Object)itemName);
        }
    }

    /**
     * 检查一个字符串必须是手机号码
     * 移动： 139 138 137 136 135 134 147 150 151 152 157 158 159 178 182 183 184 187 188
     * 联通： 130 131 132 155 156 185 186 145 176
     * 电信： 133 153 177 180 181 189
     * <p/> 整理如下:
     * 130,131,132,133,134,135,136,137,138,139
     * 145,147
     * 150,151,152,153,155,156,157,158,159
     * 176,177,178
     * 180,181,182,183,184,185,186,187,188,189
     *
     * @param value
     * @param itemName
     * @throws PlatformException
     */
    public static void checkIsMobile(String value, String itemName) throws PlatformException {
        checkNotNullAndEmpty(value,itemName);
        Pattern p = compile("(^13\\d{9}$)|(^14)[5,7]\\d{8}$|(^15[0,1,2,3,5,6,7,8,9]\\d{8}$)|(^17)[6,7,8]\\d{8}$|(^18\\d{9}$)");
        Matcher m = p.matcher(value);
        if (!m.matches()) {
            throw new PlatformException(9005,  (Object)itemName);
        }
    }


    /**
     * 检查一个字符串必须是double
     *
     * @param value
     * @param itemName
     * @throws PlatformException
     */
    public static void checkIsDouble(String value, String itemName) throws PlatformException {
        checkNotNullAndEmpty(value, itemName);
        try {
            Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new PlatformException(9006,  (Object)itemName);
        }
    }

    /**
     * 检测一个字符串是不是JSON。
     *
     * @param value    - 字符串的值
     * @param itemName - 要检查项目的名称
     * @throws PlatformException
     */
    public static void checkIsJSON(String value, String itemName) throws PlatformException {
        checkNotNullAndEmpty(value,itemName);
        boolean isJson = new JsonValidator().validate(value);
        if (!isJson) {
            throw new PlatformException(9007,  (Object)itemName);
        }
    }

    /**
     * 检测一个字符串是不是JSON。
     *
     * @param value    - 字符串的值
     * @param itemName - 要检查项目的名称
     * @throws PlatformException
     */
    public static void checkContains(String value, String lists, String itemName) throws PlatformException {
        checkNotNullAndEmpty(value, itemName);
        List<String> cps = Arrays.asList(lists.split(","));
        checkContains(value, cps, itemName);
    }

    /**
     * 检测一个字符串是不是JSON。
     *
     * @param value    - 字符串的值
     * @param itemName - 要检查项目的名称
     * @throws PlatformException
     */
    public static void checkContains(String value, List<String> lists, String itemName) throws PlatformException {
        checkNotNullAndEmpty(value, itemName);
        if (null != lists && !lists.contains(value)) {
            throw new PlatformException(9007,  (Object)itemName, value);
        }
    }

    /**
     * 检查一个文件名是否是Excel文件
     *
     * @param fileName
     * @throws PlatformException
     */
    public static void checkIsExcelFile(String fileName) throws PlatformException {
        if (fileName != null) {
            if (!(fileName.toLowerCase().endsWith("xls") || fileName.toLowerCase().endsWith("xlsx"))) {
                //请上传Excel文件。
                throw new PlatformException(9012);
            }
        } else {
            //请上传Excel文件。
            throw new PlatformException(9012);
        }
    }

    /**
     * 判断字符串是否为日期字符串
     *
     * @param value    - 字符串的值
     * @param itemName - 要检查项目的名称
     * @throws PlatformException
     */
    public static void checkIsDate(String value, String itemName) throws PlatformException {
        checkNotNullAndEmpty(value, itemName);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(value);
        } catch (ParseException e) {
            throw new PlatformException(9010,  (Object)itemName);
        }
    }

    public static void main(String[] args) {
        try {
            checkNotNullAndEmpty("", "userId");
            checkIsMobile("125747","phoneId");
        } catch (PlatformException e) {
            System.out.println(e.getErrorCode()+"||||"+e.getMessage());
            e.printStackTrace();
        }
    }
}
