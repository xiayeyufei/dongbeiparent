package com.lyl.service;

import com.lyl.pojo.UmsRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author jinyaxu
 * @since 2021-07-02
 */
public interface IUmsRoleService extends IService<UmsRole> {
    List<UmsRole> rolelist(String name);
}
