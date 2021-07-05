package com.lyl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyl.pojo.UmsUser;
import com.lyl.mapper.UmsUserMapper;
import com.lyl.service.IUmsUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lyl
 * @since 2021-06-30
 */
@Service
public class UmsUserServiceImpl extends ServiceImpl<UmsUserMapper, UmsUser> implements IUmsUserService {

    @Override
    public IPage<UmsUser> page(Integer pageNo, Integer pageSize, String name) {
        QueryWrapper<UmsUser> wrapper = new QueryWrapper<>();
        System.out.println("进入");
        if(StringUtils.isNotBlank(name)) {
            System.out.println("开始查");
            //name为空查全部数据,有值才查
            wrapper.like("nicky_name",name);
            System.out.println(wrapper.getSqlSelect());
            //模糊查询是like
        }
        return this.page(new Page<>(pageNo,pageSize),wrapper);
    }

    @Override
    public List<UmsUser> getAll() {
        QueryWrapper<UmsUser> wrapper=new QueryWrapper<>();
        wrapper.eq("active",1);
        return this.list(wrapper);
    }

}
