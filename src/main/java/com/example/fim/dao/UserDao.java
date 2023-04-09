package com.example.fim.dao;

import com.example.fim.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {
    Integer insertUser(User user);

    /**
     * 精准的查找（条件交）
     * @param user 条件
     * @return
     */
    List<User> selectUserExact(User user);

    /**
     * 粗略的查找(条件并)
     * @param user 条件
     * @return
     */
    List<User> selectUserRough(User user);

    /**
     * 查询用户的基本信息（对外可查看的）
     * @param str
     * @return
     */
    List<User> selectUserBasicInfo(String str);

    Integer updateUserInfo(User user);

}
