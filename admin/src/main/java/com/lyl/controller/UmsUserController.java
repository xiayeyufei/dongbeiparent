package com.lyl.controller;


import com.lyl.pojo.UmsUser;
import com.lyl.service.IImgService;
import com.lyl.service.IUmsUserService;
import com.lyl.util.ResultJson;

import io.minio.errors.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wangyu
 * @since 2021-06-25
 */
@RestController
@RequestMapping("/ums-user")
public class UmsUserController {
    @Resource
    BCryptPasswordEncoder passwordEncoder;
    @Resource
    IUmsUserService umsUserService;
    @Resource
    IImgService imgService;
    @GetMapping("/list")
    ResultJson list(Integer pageNo, Integer pageSize, String name) throws InterruptedException {
        return ResultJson.success(umsUserService.page(pageNo,pageSize,name)) ;
    }
    @PostMapping("/add")
    ResultJson add(UmsUser umsUser, MultipartFile file) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, InvalidBucketNameException, ErrorResponseException {
        umsUser.setPassword(passwordEncoder.encode(umsUser.getRawPassword()));
        umsUser.setIcon(imgService.upload(file));
        return ResultJson.success(umsUserService.save(umsUser),"添加用户成功");
    }
    @GetMapping("/getone")
    ResultJson getOne(Long id) {
        return ResultJson.success(umsUserService.getById(id));
    }
    @PostMapping("/update")
    ResultJson update(UmsUser umsUser, MultipartFile file) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, InvalidBucketNameException, ErrorResponseException {
        if(file != null && file.getSize() > 0) {
            umsUser.setIcon(imgService.upload(file));
        }
        return ResultJson.success(umsUserService.updateById(umsUser),"修改用户成功");
    }

    @PostMapping("/del")
    ResultJson del(UmsUser umsUser) {
        String message = umsUser.getActive() == 0 ? "删除用户成功" : "恢复用户成功";
        return ResultJson.success(umsUserService.updateById(umsUser),message);
    }
    @GetMapping("/index")
    String index() {
        return "这里是admin中的index方法";
    }

    @PostMapping("/login")
    ResultJson login(String username, String password) throws Exception {
        return ResultJson.success(umsUserService.login(username, password),"登录成功");
    }
}
