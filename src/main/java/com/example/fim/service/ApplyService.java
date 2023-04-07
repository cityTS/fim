package com.example.fim.service;

import com.example.fim.dao.ApplyDao;
import com.example.fim.domain.Apply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplyService {
    @Autowired
    ApplyDao applyDao;

    /**
     * 查询未处理的请求
     * @param apply 包含申请者和被申请者的id
     * @return
     */
    public Apply queryUntreatedApply(Apply apply) {
        List<Apply> applies = applyDao.selectUntreatedApply(apply);
        if(applies.isEmpty()) {
            return null;
        }
        return applies.get(0);
    }

    public Boolean addFriend(Apply apply) {
        apply.setSponsorTime(System.currentTimeMillis() / 1000);
        return applyDao.insertApply(apply) == 1;
    }
}
