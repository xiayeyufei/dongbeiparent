package com.lyl.service;

import com.lyl.pojo.UmsRoleUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色用户关联表 服务类
 * </p>
 *
 * @author lyl
 * @since 2021-07-04
 */
public interface IUmsRoleUserService extends IService<UmsRoleUser> {
    boolean save(Long roleId,Long[] userIds);
    List<UmsRoleUser> getUsersByRoleId(Long roleId);
}
