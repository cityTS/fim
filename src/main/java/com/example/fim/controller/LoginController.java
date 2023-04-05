package com.example.fim.controller;

import com.example.fim.domain.Re;
import com.example.fim.domain.User;
import com.example.fim.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/")
public class LoginController {
    @Autowired
    private UserService userService;

    /**
     * 注册
     * @param user 账号信息
     * @param code 手机验证码
     * @return
     */
    @PostMapping("/signup/{code}")
    public Re registeredUsers(@RequestBody User user, @PathVariable String code) {
        // TODO 校验code
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
    public Re ackPhone(String phone) {
        User user = new User();
        user.setUserPhone(phone);
        if(userService.exitUser(user)) {
            // TODO 发送验证码
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
        // TODO 校验验证码是否正确
        User user = new User();
        user.setUserAccount(100513L);
        user.setUsername("阳光快乐大男孩");
        user.setAvatarUrl("https://picx.zhimg.com/80/v2-2e1641a8fb38884c8b185ee293d5ae12_720w.webp?source=1940ef5c");
        if(true) {
            return new Re(0, user, "登陆成功");
        }
        return new Re(1, null, "不正确的验证码");
    }
}
