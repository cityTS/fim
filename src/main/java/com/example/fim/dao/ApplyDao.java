package com.example.fim.dao;

import com.example.fim.domain.Apply;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApplyDao {

    List<Apply> selectUntreatedApply(Apply apply);

    Integer insertApply(Apply apply);
}
