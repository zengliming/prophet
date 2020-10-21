package cn.zlmisme.prophet.authority.dao.mapper;

import cn.zlmisme.prophet.authority.dao.entity.UserAccount;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author liming zeng
 * @create 2020-10-20 16:11
 */
public interface UserAccountMapper extends BaseMapper<UserAccount> {

    /**
     * 根据账号名统计
     * @param account 账号
     * @return 数量
     */
    default int countByName(String account) {
        QueryWrapper<UserAccount> countQuery = new QueryWrapper<>();
        countQuery.eq("account", account).last("limit 1");
        return this.selectCount(countQuery);
    }
}
