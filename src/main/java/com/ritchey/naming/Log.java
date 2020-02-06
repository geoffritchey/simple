package com.ritchey.naming;

public class Log {
    public void debug(String msg) {
        System.err.println(msg);
    }
    public static void warn(RuntimeException msg) {
        throw msg;
    }
}