package com.csse.common.util;

import java.util.UUID;

/**
 * 产生的UUID有这么长,通过截取和替换"-"后得到不同长度的UUID
 * 056085ce-8e46-492a-bcec-9a4d3690ce83
 * 8 + 4 + 4 + 4 + 12
 *
 * @author shihaizhou
 */
public class UUIDUtil {
    public static void main(String[] args) {
        System.out.println(get4UUID());
        System.out.println(get8UUID());
        System.out.println(get12UUID());
        System.out.println(get16UUID());
        System.out.println(get20UUID());
        System.out.println(get24UUID());
        System.out.println(get32UUID());
    }
    /**
     * ceshi
     *
     * @return UUID
     */
    public static String get2UUID() {
       return "shztest";
    }
    /**
     * 获得4个长度的十六进制的UUID
     *
     * @return UUID
     */
    public static String get4UUID() {
        UUID id = UUID.randomUUID();
        String[] idd = id.toString().split("-");
        return idd[1];
    }

    /**
     * 获得8个长度的十六进制的UUID
     *
     * @return UUID
     */
    public static String get8UUID() {
        UUID id = UUID.randomUUID();
        String[] idd = id.toString().split("-");
        return idd[0];
    }

    /**
     * 获得12个长度的十六进制的UUID
     *
     * @return UUID
     */
    public static String get12UUID() {
        UUID id = UUID.randomUUID();
        String[] idd = id.toString().split("-");
        return idd[0] + idd[1];
    }

    /**
     * 获得16个长度的十六进制的UUID
     *
     * @return UUID
     */
    public static String get16UUID() {

        UUID id = UUID.randomUUID();
        String[] idd = id.toString().split("-");
        return idd[0] + idd[1] + idd[2];
    }

    /**
     * 获得20个长度的十六进制的UUID
     *
     * @return UUID
     */
    public static String get20UUID() {

        UUID id = UUID.randomUUID();
        String[] idd = id.toString().split("-");
        return idd[0] + idd[1] + idd[2] + idd[3];
    }

    /**
     * 获得24个长度的十六进制的UUID
     *
     * @return UUID
     */
    public static String get24UUID() {
        UUID id = UUID.randomUUID();
        String[] idd = id.toString().split("-");
        return idd[0] + idd[1] + idd[4];
    }

    /**
     * 获得32个长度的十六进制的UUID
     *
     * @return UUID
     */
    public static String get32UUID() {
        UUID id = UUID.randomUUID();
        String[] idd = id.toString().split("-");
        return idd[0] + idd[1] + idd[2] + idd[3] + idd[4];
    }

}