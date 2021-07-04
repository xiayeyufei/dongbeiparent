package com.lyl.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyl.pojo.UmsUser;
import com.lyl.service.IImgService;
import com.lyl.service.IUmsUserService;
import com.lyl.util.ResultJson;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lyl
 * @since 2021-06-30
 */
@RestController
@RequestMapping("/ums-user")
public class UmsUserController {
    @Resource
    IImgService iImgService;
    @Resource
    IUmsUserService umsUserService;
    @Resource
    BCryptPasswordEncoder passwordEncoder;
    @GetMapping("/list")
    ResultJson list(Integer pageNo, Integer pageSize,String name){//在参数当中定义和json属性名一样的名字
        System.out.println(pageNo+"-----------"+pageSize);
//    int i =100/0;
//    try {
//        TimeUnit.SECONDS.sleep(1);
//    } catch (InterruptedException e) {
//        e.printStackTrace();
//    }
        return ResultJson.success( umsUserService.page(pageNo,pageSize,name));
    }
    @PostMapping("/add")
    ResultJson add(UmsUser umsUser, MultipartFile file) throws IOException, ServerException, InvalidBucketNameException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {//MultipartFile file 专门接受文件的，名字必须和前端一致，
        // 构建 MinioClient对象
        // 使用MinIO服务的URL，端口，Access key和Secret key创建一个MinioClient对象
        // MinioClient minioClient = new MinioClient("https://play.min.io", "Q3AM3UQ867SPQQA43P2F", "zuf+tfteSlswRu7BJ86wekitnifILbZam1KYY3TG");有横线的意思可以用但不建议用
        /*
        定义新文件名
        因为上传文件不只有用户，还有其他功能也会拥有上传图片的功能，比如商品logo，所以要封装
         */
        umsUser.setIcon(iImgService.upload(file));
        umsUser.setPassword(passwordEncoder.encode(umsUser.getRawPassword()));
        return ResultJson.success(umsUserService.save(umsUser),"添加用户成功");
    }
    @PostMapping("/update")
    ResultJson update(UmsUser umsUser, MultipartFile file) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, InvalidBucketNameException, ErrorResponseException {
        if(file != null && file.getSize() > 0) {
            //有文件上传我才改，没文件就不上传了
            System.out.println("图片已修改");
            umsUser.setIcon(iImgService.upload(file));
        }
        return ResultJson.success(umsUserService.updateById(umsUser),"修改用户成功");
    }
    @GetMapping("/getone")
    ResultJson getOne(Long id){
        return ResultJson.success(umsUserService.getById(id));
    }
    @PostMapping("/del")
    ResultJson del(UmsUser umsUser) {
        String message = umsUser.getActive() == 0 ? "删除用户成功" : "恢复用户成功";
        return ResultJson.success(umsUserService.updateById(umsUser),message);
    }
}
