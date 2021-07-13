package com.lyl.controller;


import com.lyl.service.IUmsResourceService;
import com.lyl.util.ResultJson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 资源表 前端控制器
 * </p>
 *
 * @author lyl
 * @since 2021-07-06
 */
@RestController
@RequestMapping("//ums-resource")
public class UmsResourceController {
@Resource
    IUmsResourceService resourceService;
@GetMapping("/list")
ResultJson list() {
    return ResultJson.success(resourceService.getResource(0L));
}
}
