package com.lyl.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient("admin")
public interface AdminService {
    @RequestMapping(value = "/img/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)//有文件上传用consume
    String upload(@RequestPart MultipartFile file);//文本类型必须加这个requestparam，文件是part
}
