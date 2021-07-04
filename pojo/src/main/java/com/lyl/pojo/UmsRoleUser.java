package com.lyl.pojo;

import com.lyl.pojo.BasePojo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 角色用户关联表
 * </p>
 *
 * @author lyl
 * @since 2021-07-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UmsRoleUser extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 用户id
     */
    private Long userId;


}
