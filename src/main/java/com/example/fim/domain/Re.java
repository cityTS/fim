package com.example.fim.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Re {
    /**
     * 响应码
     */
    private Integer code;
    /**
     * 返回数据
     */
    private Object data;
    /**
     * 详细信息
     */
    private String msg;
}
