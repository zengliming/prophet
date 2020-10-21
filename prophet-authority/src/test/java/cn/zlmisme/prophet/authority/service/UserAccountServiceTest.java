package cn.zlmisme.prophet.authority.service;

import cn.zlmisme.prophet.authority.commons.dto.AddUserAccountDTO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserAccountServiceTest {

    @Resource
    private UserAccountService userAccountService;

    @Test
    void addUser() {
        AddUserAccountDTO dto = new AddUserAccountDTO();
        dto.setAccount("admin");
        dto.setPassword("admin");
        dto.setRoleIds(Collections.emptyList());
        userAccountService.addUser(dto);
    }
}