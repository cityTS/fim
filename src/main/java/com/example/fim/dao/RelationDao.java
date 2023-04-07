package com.example.fim.dao;

import com.example.fim.domain.Relation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RelationDao {
    List<Relation> selectExitRelation(Relation relation);
}
