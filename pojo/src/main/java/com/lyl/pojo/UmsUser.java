package com.lyl.pojo;

import com.lyl.pojo.BasePojo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author lyl
 * @since 2021-07-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UmsUser extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 昵称
     */
    private String nickyName;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 是否有效
     */
    private Integer active;

    /**
     * 用户头像
     */
    private String icon;


}
