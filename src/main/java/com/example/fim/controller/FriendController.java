package com.example.fim.controller;

import com.example.fim.domain.Re;
import com.example.fim.domain.Relation;
import com.example.fim.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/friend")
public class FriendController {
    @Autowired
    private RelationService relationService;


    /**
     * 查询好友列表
     * @param userId
     * @return
     */
    @GetMapping
    public Re queryUserFriends(Long userId) {
        Relation relation = new Relation();
        relation.setUserId(userId);
        return new Re(0, relationService.queryUserFriends(relation), "查询成功");
    }
}
