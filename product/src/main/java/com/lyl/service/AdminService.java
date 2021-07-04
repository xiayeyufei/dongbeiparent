package com.lyl.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("admin")
public interface AdminService {
    @RequestMapping("/ums-user/index")
    String index();
}
