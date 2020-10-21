package cn.zlmisme.prophet.authority.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author liming zeng
 * @create 2020-10-20 16:04
 */
@Data
@TableName("role_resource_ref")
public class RoleResourceRef {

    private Long id;

    @TableField(value = "role_id")
    private Long roleId;

    @TableField(value = "resource_id")
    private Long resourceId;

    @TableField(value = "status")
    private Integer status;
}
