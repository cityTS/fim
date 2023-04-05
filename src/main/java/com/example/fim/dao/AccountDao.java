package com.example.fim.dao;

import com.example.fim.domain.Account;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccountDao {
    /**
     * 查询num个未使用的账号
     * @param num 查询的数量
     * @return
     */
    List<Account> selectAccounts(Integer num);

    /**
     * 获取最新的账号
     * @return
     */
    Long selectLatestAccount();

    /**
     * 添加备用账号
     * @param account 账号
     * @return
     */
    Integer insertAccount(Long account);

    /**
     * 使用过的账号删除备用库
     * @param account 账号
     * @return
     */
    Integer updateAccount(Long account);
}
