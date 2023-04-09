package com.example.fim.utils;

import java.util.HashMap;
import java.util.Map;

public class CodeUtil {
    private static Map<String, String> code = new HashMap<>();

    public static Boolean isOk(String c, String id) {
        if(!code.containsKey(id)) {
            return false;
        }
        if(code.get(id).equals(c)) return true;
        return false;
    }
    public static void setCode(String c, String id) {
        code.put(id, c);
    }
}
