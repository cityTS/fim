package com.example.fim.controller;

import com.example.fim.domain.*;
import com.example.fim.service.ApplyService;
import com.example.fim.service.RelationService;
import com.example.fim.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    ApplyService applyService;

    @Autowired
    RelationService relationService;

    @GetMapping("/basic")
    public Re queryUserBasic(String str, Long userId) {
        User user = userService.queryUserBasicInfo(str);
        if(user == null) {
            return new Re(1, null, "无相关用户信息");
        }
        Apply ask = new Apply();
        ask.setSponsorId(userId);
        ask.setRecipientId(user.getUserAccount());
        Apply apply = applyService.queryUntreatedApply(ask);
        UserBasic userBasic = new UserBasic(user, 1);
        if(apply != null) {
            return new Re(0, userBasic, "查询成功");
        }
        Relation relation = new Relation();
        relation.setUserId(userId);
        relation.setFriendId(user.getUserAccount());
        Relation relation1 = relationService.queryExitRelation(relation);
        if(relation1 == null) {
            userBasic.setStatus(0);
            return new Re(0, userBasic, "查询成功");
        }
        userBasic.setStatus(2);
        return new Re(0, userBasic, "查询成功");
    }

    @GetMapping("/add")
    public Re addFriend(Long userId, Long friendId) {
        Relation relation = new Relation();
        relation.setUserId(userId);
        relation.setFriendId(friendId);
        Relation relation1 = relationService.queryExitRelation(relation);
        if(relation1 != null) {
            return new Re(1, null, "用户" + friendId + "已是您的好友");
        }
        Apply apply = new Apply();
        apply.setSponsorId(friendId);
        apply.setRecipientId(userId);
        Apply apply1 = applyService.queryUntreatedApply(apply);
        if(apply1 != null) {
            return new Re(1, null, "您与用户" + friendId + "存在一条未处理的请求记录");
        }
        apply.setSponsorId(userId);
        apply.setRecipientId(friendId);
        apply1 = applyService.queryUntreatedApply(apply);
        if(apply1 != null) {
            return new Re(1, null, "您与用户" + friendId + "存在一条未处理的请求记录");
        }
        if (applyService.addFriend(apply)) {
            return new Re(0, null, "申请成功");
        }
        return new Re(1, null, "发生了未知错误");
    }

    @GetMapping("/avatar")
    public Re queryAvatarUrl(String userId) {
        User user = userService.queryUserBasicInfo(userId);
        if(user != null) {
            return new Re(0, user.getAvatarUrl(), "查询成功");
        }
        return new Re(1, null, "查无此用户");
    }

    @GetMapping("/update")
    public Re updateUser(String avatarUrl, String username, String password, Long userAccount) {
        User user = new User();
        user.setAvatarUrl(avatarUrl);
        user.setUserAccount(userAccount);
        user.setUsername(username);
        if(password != null && password.equals("")) {
            password = null;
        }
        user.setUserPassword(password);
        if(userService.updateUser(user)) {
            return new Re(0, null, "更新成功");
        }
        return new Re(1, null, "更新失败");
    }

}
