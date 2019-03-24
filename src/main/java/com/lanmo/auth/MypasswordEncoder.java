package com.lanmo.auth;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 自定义实现spring security 密码加密
 */
public class MypasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return null;
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return false;
    }
}
