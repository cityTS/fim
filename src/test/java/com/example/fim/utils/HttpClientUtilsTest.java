package com.example.fim.utils;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class HttpClientUtilsTest {

    @Test
    public void test1() {
        System.out.println("123213");
    }
    @Test
    public void test() throws IOException {
        String s = HttpClientUtils.get("http://43.139.136.169:10025/api/get_message/?phone=15077114002");
        System.out.println(s);
    }
}
