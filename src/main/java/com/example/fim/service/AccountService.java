package com.example.fim.service;

import com.example.fim.dao.AccountDao;
import com.example.fim.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountDao accountDao;

    /**
     * 获取num个备选账号
     * @param num
     * @return
     */
    public List<Account> getAccounts(Integer num) {
        List<Account> accounts = accountDao.selectAccounts(num);
        if(accounts.size() != num) {
            Long latestAccount = accountDao.selectLatestAccount();
            if(latestAccount == null) {
                latestAccount = 100000L;
            } else {
                latestAccount++;
            }
            List<Long> newAccount = new ArrayList<>();
            for(int i = 0; i <= 1000; i++, latestAccount++) {
                newAccount.add(latestAccount);
            }
            // 洗牌算法随机排序
            Collections.shuffle(newAccount);
            for (int i = 0; i < newAccount.size(); i++) {
                accountDao.insertAccount(newAccount.get(i));
            }
            accounts = accountDao.selectAccounts(num);
        }
        return accounts;
    }

    public Boolean removeAccount(Long account) {
        return accountDao.updateAccount(account) >= 1;
    }
}
