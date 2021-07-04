package com.lyl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lyl.pojo.Img;
import com.lyl.mapper.ImgMapper;
import com.lyl.service.IImgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>
 * 图片表 服务实现类
 * </p>
 *
 * @author lyl
 * @since 2021-07-03
 */
@Service
public class ImgServiceImpl extends ServiceImpl<ImgMapper, Img> implements IImgService {
    //获取配置文件，application.yml的信息
    @Value("${minio.endpoint}")
    String endpoint;
    @Value("${minio.username}")
    String username;
    @Value("${minio.password}")
    String password;
    @Override
    public String upload(MultipartFile file) throws IOException, ServerException, InvalidBucketNameException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {


        // 上传之前先看看 以前传没传过，传没传过就看这三个变量MD5，size，suffix
        String md5 = DigestUtils.md5Hex(file.getInputStream());
        //大小
        long size = file.getSize();
        //前缀
        String suffix = FilenameUtils.getExtension(file.getOriginalFilename());
        QueryWrapper<Img> wrapper = new QueryWrapper<>();
        wrapper.eq("md5", md5)
                .eq("size", size)
                .eq("suffix", suffix);
        //按照这个条件来查找
        Img img = this.getOne(wrapper);
        //如果为null，就是说明为null，
        if (img != null) {
            return img.getUrl();}
        else {
            /*
        定义新文件名
        因为上传文件不只有用户，还有其他功能也会拥有上传图片的功能，比如商品logo，所以要封装
         */
            StringBuilder builder = new StringBuilder();
            LocalDateTime now = LocalDateTime.now();
            builder.append(now.format(DateTimeFormatter.ofPattern("yyyyMMddmmssSSS")));
            builder.append(RandomStringUtils.random(6, false, true));
            //FilenameUtils.getExtension(file.getOriginalFilename())
            builder.append(".").append(suffix);
            MinioClient minioClient = MinioClient.builder()
                    .endpoint(endpoint)
                    .credentials(username, password).build();
            PutObjectArgs args = PutObjectArgs.builder()
                    .object(builder.toString())
                    .bucket("images")
                    .contentType(file.getContentType())
                    .stream(file.getInputStream(), file.getSize(), 0)
                    .build();
            //partsize要不要分块传，用于特别大的文件。不分块容易断，导致重传。
            minioClient.putObject(args);
            img = new Img(md5,size,suffix,builder.toString());
            this.save(img);
            return builder.toString();
        }

    }
}
