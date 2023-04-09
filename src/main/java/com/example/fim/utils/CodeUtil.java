package com.example.fim.utils;

import java.util.HashMap;
import java.util.Map;

public class CodeUtil {
    private static Map<String, String> code = new HashMap<>();

    public static Boolean isOk(String phone, String Code) {
        Code = "\"" + Code + "\"";
        if(!code.containsKey(phone)) {
            return false;
        }
        if(code.get(phone).equals(Code)) return true;
        return false;
    }
    public static void setCode(String phone, String Code) {
        code.put(phone, Code);
    }
}
