package cn.zlmisme.prophet.authority.service;

import cn.zlmisme.prophet.authority.commons.dto.AddUserAccountDTO;
import cn.zlmisme.prophet.authority.dao.entity.AccountRoleRef;
import cn.zlmisme.prophet.authority.dao.entity.UserAccount;
import cn.zlmisme.prophet.authority.dao.entity.UserRole;
import cn.zlmisme.prophet.authority.dao.mapper.AccountRoleRefMapper;
import cn.zlmisme.prophet.authority.dao.mapper.UserAccountMapper;
import cn.zlmisme.prophet.authority.dao.mapper.UserRoleMapper;
import cn.zlmisme.prophet.commons.util.PasswordUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liming zeng
 * @create 2020-10-20 16:26
 */
@Service
public class UserAccountService {

    @Resource
    private UserAccountMapper userAccountMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Resource
    private AccountRoleRefMapper accountRoleRefMapper;

    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public void addUser(AddUserAccountDTO dto) {
        if (userAccountMapper.countByName(dto.getAccount()) > 0) {
            throw new IllegalArgumentException("账号名重复");
        }
        final PasswordUtil.PasswordInfo passwordInfo = PasswordUtil.generate(dto.getPassword());
        final UserAccount userAccount = UserAccount.builder()
                .account(dto.getAccount()).password(passwordInfo.getPassword())
                .salt(passwordInfo.getSalt()).status(1).build();
        userAccountMapper.insert(userAccount);
        if (!CollectionUtils.isEmpty(dto.getRoleIds())) {
            final List<UserRole> userRoles = userRoleMapper.selectBatchIds(dto.getRoleIds());
            for (UserRole userRole : userRoles) {
                AccountRoleRef ref = AccountRoleRef.builder()
                        .roleId(userRole.getId()).userAccountId(userAccount.getId()).status(1).build();
                accountRoleRefMapper.insert(ref);
            }
        }

    }
}
