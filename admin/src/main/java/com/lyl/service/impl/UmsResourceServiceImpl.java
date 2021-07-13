package com.lyl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lyl.pojo.UmsResource;
import com.lyl.mapper.UmsResourceMapper;
import com.lyl.service.IUmsResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 资源表 服务实现类
 * </p>
 *
 * @author lyl
 * @since 2021-07-06
 */
@Service
public class UmsResourceServiceImpl extends ServiceImpl<UmsResourceMapper, UmsResource> implements IUmsResourceService {
    @Resource
    UmsResourceMapper resourceMapper;
    @Override
    public List<UmsResource> getResource(Long parentId) {
        QueryWrapper<UmsResource> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",parentId);
        List<UmsResource> list = this.list(wrapper);
        for(UmsResource resource : list) {
            resource.setChildren(getResource(resource.getId()));
        }
        return list;
    }

    @Override
    public List<Long> getLast() {
        QueryWrapper<UmsResource> wrapper = new QueryWrapper<>();
        wrapper.eq("haschildren",0);
        List<UmsResource> list = this.list(wrapper);
        List<Long> result = new ArrayList<>();
        list.forEach(umsResource -> {
            result.add(umsResource.getId());
        });
        return result;
    }

    @Override
    public List<UmsResource> getByUserId(Long userId) {
        return resourceMapper.getByUserId(userId);
    }
}
