package com.example.fim.domain;

import com.alibaba.druid.sql.visitor.functions.Char;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    /**
     * 用户账号
     */
    private Long userAccount;
    /**
     * 用户密码
     */
    private String userPassword;
    /**
     * 用户昵称
     */
    private String username;
    /**
     * 用户手机号
     */
    private String userPhone;
    /**
     * 用户邮箱
     */
    private String userEmail;
    /**
     * 用户性别
     */
    private Char userSex;
    /**
     * 用户生日
     */
    private Long userBirth;
    /**
     * 账户状态
     */
    private Boolean userFrozen;
    /**
     * 创建日期
     */
    private Long createdAt;

    /**
     * 用户头像
     */
    private String avatarUrl;
}
