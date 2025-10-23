package com.lvwyh.common;

import java.security.SecureRandom;

public final class Ids {
    private static final char[] ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final SecureRandom RNG = new SecureRandom();

    private Ids() {}

    /** 生成指定位数的短ID（默认小写字母+数字），如 10 位：a3k9x1f0bz */
    public static String shortId(int len) {
        char[] buf = new char[len];
        for (int i = 0; i < len; i++) {
            buf[i] = ALPHABET[RNG.nextInt(ALPHABET.length)];
        }
        return new String(buf);
    }
}
