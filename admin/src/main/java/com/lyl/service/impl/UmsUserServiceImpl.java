package com.lyl.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyl.pojo.UmsResource;
import com.lyl.pojo.UmsUser;
import com.lyl.mapper.UmsUserMapper;
import com.lyl.service.IUmsResourceService;
import com.lyl.service.IUmsUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Resource
    BCryptPasswordEncoder passwordEncoder;
    @Resource
    IUmsResourceService resourceService;
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

    @Override
    public Map<String, Object> login(String username, String password) throws Exception {
        QueryWrapper<UmsUser> wrapper = new QueryWrapper<>();
        wrapper.eq("login_name",username);
        UmsUser user = this.getOne(wrapper);
        if(user == null) {
            throw new Exception("用户名或密码错误");
        }
        if(user.getActive() == 0) {
            throw new Exception("该用户已经失效,无法登录");
        }
//        System.out.println(user.getRawPassword());
        if(!passwordEncoder.matches(password,user.getPassword())) {

            throw new Exception("用户名或密码错误");
        }


        // 如果登录成功 获取到用户权限，这样就是返回权限
        List<UmsResource> resources = resourceService.getByUserId(user.getId());
        Map<String, Object> split = split(resources);
        Map<String,Object> reslut = new HashMap<>();
        reslut.put("front",split.get("front"));
        //return 的东西只需要前端权限和token
        String token = JWT.create().withClaim("username",username)
                .withClaim("backurls",(List<String>)split.get("back"))
                .sign(Algorithm.HMAC256("liuyanliang"));
        reslut.put("token",token);
        return reslut;
    }

    private Map<String,Object> split(List<UmsResource> resources) {//拆成前后端权限
        //后端权限只要记住地址就行了
        Map<String,Object> map = new HashMap<>();
        List<String> backurls = new ArrayList<>();
        //前端权限，需要组合成有层级关系的
        List<UmsResource> front = getByParentId(resources,0L);
        for(UmsResource resource : resources) {
            //先把最高级的0节点提出来，然后再提取剩下的里面的
            if(StringUtils.isNotBlank(resource.getBackUrl())) {
                backurls.add(resource.getBackUrl());
            }
        }
        System.out.println(front);
        //backurl
        map.put("back",backurls);
        map.put("front",front);
        return map;
    }
    /*写个递归*/
    private List<UmsResource> getByParentId(List<UmsResource> list, Long parentId) {
        List<UmsResource> result = new ArrayList<>();
        //一定要拆箱
        for(UmsResource resource : list) {

            if(resource.getParentId().longValue() == parentId.longValue() && resource.getType().intValue() == 0) {
                //遍历整个数组如果他的父节点是是符合要求的且
                resource.setChildren(getByParentId(list, resource.getId()));
                result.add(resource);
            }
        }
        return result;
    }
}
