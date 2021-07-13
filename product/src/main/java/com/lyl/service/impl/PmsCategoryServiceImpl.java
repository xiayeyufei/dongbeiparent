package com.lyl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lyl.pojo.PmsCategory;
import com.lyl.mapper.PmsCategoryMapper;
import com.lyl.service.IPmsCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 分类表 服务实现类
 * </p>
 *
 * @author lyl
 * @since 2021-07-12
 */
@Service
public class PmsCategoryServiceImpl extends ServiceImpl<PmsCategoryMapper, PmsCategory> implements IPmsCategoryService {
    public List<PmsCategory> getByParentId(Long parentId, Integer active) {
        QueryWrapper<PmsCategory> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",parentId);
        if(active != null) {
            wrapper.eq("active",active);
        }
        List<PmsCategory> list = this.list(wrapper);
        for(PmsCategory category : list) {
            if(category.getActive() == 1) {
                category.setChildren(getByParentId(category.getId(),active));
            }
        }
        return list;
    }

    @Override
    public List<PmsCategory> getByParentId(Long parentId) {
        return this.getByParentId(parentId,null);
    }


    @Override
    public List<PmsCategory> getAll(Long parentId) {
        return this.getByParentId(parentId,1);
    }
}
