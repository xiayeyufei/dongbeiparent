package com.lyl.controller;


import com.lyl.service.AdminService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * <p>
 * 品牌表 前端控制器
 * </p>
 *
 * @author lyl
 * @since 2021-07-04
 */
@RestController
@RequestMapping("//pms-brand")
public class PmsBrandController {
    @Resource
    AdminService adminService;
    @GetMapping("/index")
    String index() {
//        RestTemplate restTemplate=new RestTemplate();
//        String str=restTemplate.getForObject("http://127.0.0.1:8081/ums-user/index",String.class);
        String str = adminService.index();
        return str;
    }
}
