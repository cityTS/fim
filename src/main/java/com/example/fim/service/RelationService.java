package com.example.fim.service;

import com.example.fim.dao.RelationDao;
import com.example.fim.domain.Relation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelationService {
    @Autowired
    RelationDao relationDao;

    public Relation queryExitRelation(Relation relation) {
        List<Relation> relations = relationDao.selectExitRelation(relation);
        if(relations.isEmpty()) {
            return null;
        }
        return relations.get(0);
    }
}
