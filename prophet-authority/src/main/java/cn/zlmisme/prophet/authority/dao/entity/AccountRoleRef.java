package cn.zlmisme.prophet.authority.dao.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author liming zeng
 * @create 2020-10-20 15:56
 */
@Data
@Builder
@TableName("account_role_ref")
public class AccountRoleRef {

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     *
     */
    @TableField(value = "role_id", insertStrategy = FieldStrategy.NOT_NULL, updateStrategy = FieldStrategy.NOT_NULL)
    private Long roleId;

    /**
     *
     */
    @TableField(value = "user_account_id", insertStrategy = FieldStrategy.NOT_NULL, updateStrategy = FieldStrategy.NOT_NULL)
    private Long userAccountId;

    /**
     *
     */
    @TableField(value = "status", insertStrategy = FieldStrategy.NOT_NULL, updateStrategy = FieldStrategy.NOT_NULL)
    private Integer status;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

}
