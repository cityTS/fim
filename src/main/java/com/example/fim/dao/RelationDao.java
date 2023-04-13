package com.example.fim.dao;

import com.example.fim.domain.Relation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RelationDao {
    /**
     * 查询关系是否存在
     * @param relation
     * @return
     */
    List<Relation> selectExitRelation(Relation relation);

    /**
     * 查询用户的好友
     * @param relation
     * @return
     */
    List<Relation> selectUserFriends(Relation relation);

    Integer insertFriend(Relation relation);

    Integer deleteFriend(Long id1, Long id2, Long now);
}
