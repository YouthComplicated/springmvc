package com.lanmo.myexception;

/**
 * 自定义Error
 */
public class MyError {
    private int code;
    private String messag;

    public MyError(int code, String messag) {
        this.code = code;
        this.messag = messag;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessag() {
        return messag;
    }

    public void setMessag(String messag) {
        this.messag = messag;
    }

    @Override
    public String toString() {
        return "MyError{" +
                "code=" + code +
                ", messag='" + messag + '\'' +
                '}';
    }
}
