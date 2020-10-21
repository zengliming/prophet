package cn.zlmisme.prophet.authority.dao.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author liming zeng
 * @create 2020-10-20 15:53
 */
@Data
@TableName("user_role")
public class UserRole {

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 角色名称
     */
    @TableField(value = "role_name", insertStrategy = FieldStrategy.NOT_NULL, updateStrategy = FieldStrategy.NOT_NULL)
    private String roleName;

    /**
     * 角色编码
     */
    @TableField(value = "role_code", insertStrategy = FieldStrategy.NOT_NULL, updateStrategy = FieldStrategy.NOT_NULL)
    private String roleCode;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;


}
