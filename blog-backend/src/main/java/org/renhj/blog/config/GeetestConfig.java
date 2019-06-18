package org.renhj.blog.config;

import lombok.Getter;


public class GeetestConfig {
    private static final String geeTest_id="5f034210fc57ef7df11b319c77949377";
    private static final String geeTest_key="1a2182cf6eecd7462cac965925ed6c76";
    private static final boolean newfangled = true;

    public static String getGeeTest_id() {
        return geeTest_id;
    }

    public static String getGeeTest_key() {
        return geeTest_key;
    }

    public static boolean isNewfangled() {
        return newfangled;
    }
}
