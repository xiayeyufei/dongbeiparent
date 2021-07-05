package com.lyl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lyl.pojo.UmsRole;
import com.lyl.mapper.UmsRoleMapper;
import com.lyl.service.IUmsRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author jinyaxu
 * @since 2021-07-02
 */
@Service
public class UmsRoleServiceImpl extends ServiceImpl<UmsRoleMapper, UmsRole> implements IUmsRoleService {

    @Override
    public List<UmsRole> rolelist(String name) {
        QueryWrapper<UmsRole> queryWrapper=new QueryWrapper<>();
        if(StringUtils.isNotBlank(name)) {
            queryWrapper.like("name",name);
        }
        return this.list(queryWrapper);
    }
}
