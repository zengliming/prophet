package cn.zlmisme.prophet.authority.dao.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author liming zeng
 * @create 2020-10-20 14:51
 */
@Data
@Builder
@TableName("user_account")
public class UserAccount {

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 账号
     */
    @TableField(value = "account", insertStrategy = FieldStrategy.NOT_NULL, updateStrategy = FieldStrategy.NOT_NULL)
    private String account;

    /**
     * 密码
     */
    @TableField(value = "passwd", insertStrategy = FieldStrategy.NOT_NULL, updateStrategy = FieldStrategy.NOT_NULL)
    private String password;

    /**
     * 盐
     */
    @TableField(value = "salt", insertStrategy = FieldStrategy.NOT_NULL, updateStrategy = FieldStrategy.NOT_NULL)
    private String salt;

    /**
     * 状态 0是禁用、1是启用
     */
    @TableField(value = "status", insertStrategy = FieldStrategy.NOT_NULL, updateStrategy = FieldStrategy.NOT_NULL)
    private Integer status;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

}
