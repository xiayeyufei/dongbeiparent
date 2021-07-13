package com.lyl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyl.pojo.UmsUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lyl
 * @since 2021-06-30
 * 不是简简单单的分页了，有条件了，就要开始往service写了
 */
public interface IUmsUserService extends IService<UmsUser> {
    IPage<UmsUser> page(Integer pageNo, Integer pageSize, String name);
    List<UmsUser> getAll();
    Map<String, Object> login(String username, String password) throws Exception;
}
