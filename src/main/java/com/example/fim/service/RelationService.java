package com.example.fim.service;

import com.example.fim.dao.RelationDao;
import com.example.fim.dao.UserDao;
import com.example.fim.domain.Friend;
import com.example.fim.domain.Relation;
import com.example.fim.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RelationService {
    @Autowired
    private RelationDao relationDao;

    @Autowired
    private UserDao userDao;

    /**
     * 查询关系是否存在
     * @param relation
     * @return
     */
    public Relation queryExitRelation(Relation relation) {
        List<Relation> relations = relationDao.selectExitRelation(relation);
        if(relations.isEmpty()) {
            return null;
        }
        return relations.get(0);
    }

    /**
     * 查询好友列表
     * @param relation
     * @return
     */
    public List<Friend> queryUserFriends(Relation relation) {
        List<Relation> relations = relationDao.selectUserFriends(relation);
        List<Friend> res = new ArrayList<>();
        for (int i = 0; i < relations.size(); i++) {
            User user = new User();
            user.setUserAccount(relations.get(i).getFriendId());
            List<User> users = userDao.selectUserExact(user);
            users.get(0).clearSensitiveInformation();
            if(!users.isEmpty()) res.add(new Friend(users.get(0), relation.getFriendNickname()));
        }
        return res;
    }

    public Boolean addFriend(Relation relation) {
        return relationDao.insertFriend(relation) == 1;
    }
}
