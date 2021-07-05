package com.lyl.controller;


import com.lyl.pojo.UmsRoleUser;
import com.lyl.service.IUmsRoleUserService;
import com.lyl.service.IUmsUserService;
import com.lyl.util.ResultJson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 角色用户关联表 前端控制器
 * </p>
 *
 * @author lyl
 * @since 2021-07-04
 */
@RestController
@RequestMapping("/ums-role-user")
public class UmsRoleUserController {
    @Resource
    IUmsUserService userService;
    @Resource
    IUmsRoleUserService roleUserService;
    @GetMapping("/getData")
    ResultJson getData(Long roleId){
        Map<String,Object>map=new HashMap<>();
        map.put("users",userService.getAll());
        map.put("values",roleUserService.getUsersByRoleId(roleId));
        return ResultJson.success(map);
    }
    @PostMapping("/save")
    ResultJson save(Long roleId,Long[] userIds){
        return ResultJson.success(roleUserService.save(roleId,userIds),"角色与用户关联成功");

    }

}
