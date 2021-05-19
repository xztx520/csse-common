package com.csse.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * shihaizhou
 */
public class MathUtil {

    private MathUtil() {

    }

    /**
     * 提供精确的加法运算。
     *
     * @param v1    被加数
     * @param v2    加数
     * @param scale 保留小数位数, 如果位2, 输出结果为: 0.23, 12.49, 11.00, 3.40 , 其结果都经过四舍五入。
     * @return 两个参数的和
     */

    public static String add(String v1, String v2, int scale) {
        String m_strFormat = validate(scale);
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        double res = b1.add(b2).doubleValue();
        DecimalFormat mformat = new DecimalFormat(m_strFormat);

        return mformat.format(res);
    }

    /**
     * 提供精确的减法运算。
     *
     * @param v1    被减数
     * @param v2    减数
     * @param scale 保留小数位数, 如果位2, 输出结果为: 0.23, 12.49, 11.00, 3.40 , 其结果都经过四舍五入。
     * @return 两个参数的差
     */

    public static String sub(String v1, String v2, int scale) {
        String m_strFormat = validate(scale);
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        double res = b1.subtract(b2).doubleValue();
        DecimalFormat mformat = new DecimalFormat(m_strFormat);

        return mformat.format(res);
    }

    /**
     * 提供精确的乘法运算。
     *
     * @param v1    被乘数
     * @param v2    乘数
     * @param scale 保留小数位数, 如果位2, 输出结果为: 0.23, 12.49, 11.00, 3.40 , 其结果都经过四舍五入。
     * @return 两个参数的积
     */

    public static String mul(String v1, String v2, int scale) {
        String m_strFormat = validate(scale);
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        double res = b1.multiply(b2).doubleValue();
        double result = round(Double.toString(res), scale);
        DecimalFormat mformat = new DecimalFormat(m_strFormat);

        return mformat.format(res);
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * <p/>
     * 定精度，以后的数字四舍五入。
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 保留小数位数, 如果位2, 输出结果为: 0.23, 12.49, 11.00, 3.40 , 其结果都经过四舍五入。
     * @return 两个参数的商
     */

    public static String div(String v1, String v2, int scale) {
        String m_strFormat = validate(scale);
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        double result = b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
        DecimalFormat mformat = new DecimalFormat(m_strFormat);

        return mformat.format(result);
    }

    /**
     * 提供精确的小数位四舍五入处理。
     *
     * @param v     需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */

    public static double round(String v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(v);
        BigDecimal one = new BigDecimal("1");

        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 公用部分
     *
     * @param scale
     * @return
     */
    public static String validate(int scale) {
        String m_strFormat = "##0";
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        if (scale > 0) {
            m_strFormat += ".";
            for (int i = 0; i < scale; i++) {
                m_strFormat += "0";
            }
        }

        return m_strFormat;
    }


    public static void main(String[] args) {
        String s = add("0.05", "0.01", 3);
        System.out.println(s);

        s = sub("1.0", "0.42", 3);
        System.out.println(s);

        s = mul("4.015", "100", 3);
        System.out.println(s);

        s = div("123.3", "100", 3);
        System.out.println(s);

        System.out.println("========================");

        s = add("0.05", "0.01", 2);
        System.out.println(s);

        s = sub("1.0", "0.42", 2);
        System.out.println(s);

        s = mul("4.015", "100", 2);
        System.out.println(s);

        s = div("123.3", "100", 2);
        System.out.println(s);
    }

}