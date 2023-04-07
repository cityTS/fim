package com.example.fim.service;

import com.example.fim.dao.UserDao;
import com.example.fim.domain.User;
import com.example.fim.utils.AccountUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    /**
     * 添加用户
     * @param user 用户信息
     * @return 是否添加成功
     */
    public Boolean AddUser(User user) {
        user.setCreatedAt(System.currentTimeMillis() / 1000);
        user.setUserAccount(AccountUtil.getAccount());
        return userDao.insertUser(user) == 1;
    }

    /**
     * 判断是否有满足条件的用户
     * @param user 条件
     * @return
     */
    public Boolean exitUser(User user) {
        return !userDao.selectUserExact(user).isEmpty();
    }

    public User queryUserBasicInfo(String str) {
        List<User> users = userDao.selectUserBasicInfo(str);
        if(users.isEmpty()) {
            return null;
        }
        return users.get(0);
    }
}
