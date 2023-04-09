package com.example.fim.controller;

import com.example.fim.domain.Apply;
import com.example.fim.domain.Re;
import com.example.fim.domain.Relation;
import com.example.fim.domain.User;
import com.example.fim.service.AccountService;
import com.example.fim.service.ApplyService;
import com.example.fim.service.RelationService;
import com.example.fim.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/apply")
public class ApplyController {
    @Autowired
    private ApplyService applyService;

    @Autowired
    private UserService userService;

    @Autowired
    private RelationService relationService;


    @GetMapping
    public Re queryApplyList(Long userId) {
        Apply apply = new Apply();
        apply.setRecipientId(userId);
        List<Apply> applies = applyService.queryApplyList(apply);
        List<User> users = new ArrayList<>();
        for (int i = 0; i < applies.size(); i++) {
            User user = new User();
            user.setUserAccount(applies.get(i).getSponsorId());
            users.add(userService.accurateSearch(user));
        }
        return new Re(0, users, "查询成功");
    }

    @GetMapping("/agree")
    public Re agreeApply(Long sponsorId, Long recipientId) {
        Apply apply = new Apply();
        apply.setSponsorId(sponsorId);
        apply.setRecipientId(recipientId);
        apply.setSponsorStatus(1);
        applyService.updateApplyStatus(apply);
        Relation relation = new Relation();
        relation.setUserId(sponsorId);
        relation.setFriendId(recipientId);
        relation.setRelationshipTime(System.currentTimeMillis() / 1000);

        relationService.addFriend(relation);
        relation.setUserId(recipientId);
        relation.setFriendId(sponsorId);
        relationService.addFriend(relation);
        return new Re(0, null, "添加成功");
    }

    @GetMapping("/refuse")
    public Re refuseApply(Long sponsorId, Long recipientId) {
        Apply apply = new Apply();
        apply.setSponsorId(sponsorId);
        apply.setRecipientId(recipientId);
        apply.setSponsorStatus(0);
        applyService.updateApplyStatus(apply);
        return new Re(0, null, "拒绝成功");
    }
}
