package cn.zlmisme.prophet.authority.commons.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author liming zeng
 * @create 2020-10-20 16:31
 */
@Data
public final class AddUserAccountDTO {

    @NotNull
    private String account;

    @NotNull
    private String password;

    @NotEmpty
    private List<Long> roleIds;
}
