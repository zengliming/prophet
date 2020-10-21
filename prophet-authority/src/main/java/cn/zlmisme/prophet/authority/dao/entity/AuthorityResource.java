package cn.zlmisme.prophet.authority.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author liming zeng
 * @create 2020-10-20 16:00
 */
@Data
@TableName("authority_resource")
public class AuthorityResource {

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 请求路径
     */
    @TableField(value = "uri")
    private String uri;

    /**
     * 请求方法
     */
    @TableField(value = "method")
    private String method;

    /**
     * 资源编码
     */
    @TableField("resource_code")
    private String resourceCode;

    /**
     * 上一级id
     */
    @TableField(value = "prev_id")
    private Long prevId;

    /**
     * 菜单
     */
    @TableField(value = "menu_desc")
    private String menuDesc;

    /**
     * 状态 0是禁用、1是启用
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 排序
     */
    @TableField(value = "sort")
    private Integer sort;


}
