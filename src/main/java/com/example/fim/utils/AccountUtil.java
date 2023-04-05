package com.example.fim.utils;

import com.example.fim.domain.Account;
import com.example.fim.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.misc.Lock;

import java.util.ArrayList;

/**
 * 账号库工具类
 */
@Component
public class AccountUtil {
    // 禁止创建对象
    private AccountUtil(){}
    private static ArrayList<Account> accounts;
    private static final Lock lock;

    private static AccountService accountService;

    @Autowired
    public void setService(AccountService accountService) {
        AccountUtil.accountService = accountService;
    }

    static {
        accounts = new ArrayList<>();
        lock = new Lock();
    }

    public static Long getAccount() {
        try {
            lock.lock();
            if(accounts.isEmpty()) {
                loadAccounts();
            }
            Long account = accounts.get(0).getAccount();
            accounts.remove(0);
            accountService.removeAccount(account);
            return account;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    private static void loadAccounts() {
        accounts = (ArrayList<Account>) accountService.getAccounts(100);
    }
}
