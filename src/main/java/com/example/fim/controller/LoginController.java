package com.example.fim.controller;

import com.example.fim.domain.Re;
import com.example.fim.domain.User;
import com.example.fim.service.UserService;
import com.example.fim.utils.CodeUtil;
import com.example.fim.utils.HttpClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("/api/")
public class LoginController {
    @Autowired
    private UserService userService;


    @GetMapping("/getCode/{phone}")
    public Re getCode(@PathVariable String phone) throws IOException {
        String s = HttpClientUtils.get("http://43.139.136.169:10025/api/get_message/?phone=" + phone);
        CodeUtil.setCode(s, phone);
        return new Re(0, null, "已发送验证码");
    }

    /**
     * 注册
     * @param user 账号信息
     * @param code 手机验证码
     * @return
     */
    @PostMapping("/signup/{code}")
    public Re registeredUsers(@RequestBody User user, @PathVariable String code) {
        if(!CodeUtil.isOk(code, user.getUserPhone())) {
            return new Re(1, null, "验证码不正确");
        }
        if(userService.AddUser(user)) {
            return new Re(0, null, "注册成功");
        }
        return new Re(1, null, "注册失败");
    }

    /**
     * 确认该手机号是否注册用户
     * @param phone 手机号
     * @return
     */
    @GetMapping("/signin/ack/phone")
    public Re ackPhone(String phone) throws IOException {
        User user = new User();
        user.setUserPhone(phone);
        if(userService.exitUser(user)) {
            String s = HttpClientUtils.get("http://43.139.136.169:10025/api/get_message/?phone=" + phone);
            CodeUtil.setCode(s, phone);
            return new Re(0, null, "已发送验证码");
        }
        return new Re(1, null, "该手机号还未注册");
    }

    /**
     * 手机号+验证码登录
     * @param phone 手机号
     * @param code 验证码
     * @return
     */
    @GetMapping("/signin/phone")
    public Re signInByPhone(String phone, String code) {
        if(!CodeUtil.isOk(code, phone)) {
            return new Re(1, null, "验证码不正确");
        }
        User user = new User();
        user.setUserPhone(phone);
        user = userService.accurateSearch(user);
        user.clearSensitiveInformation();
        if(true) {
            return new Re(0, user, "登陆成功");
        }
        return new Re(1, null, "不正确的验证码");
    }

    @GetMapping("/signin/email")
    public Re signInByEmail(String email, String password) {
        User user = new User();
        user.setUserEmail(email);
        user = userService.accurateSearch(user);
        if(user == null) {
            return new Re(1, null, "查无此用户");
        }
        if(user.getUserPassword().equals(password)) {
            return new Re(0, user, "登陆成功");
        }
        return new Re(1, null, "密码错误");
    }
}
