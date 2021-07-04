package com.lyl.pojo;

import com.lyl.pojo.BasePojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 图片表
 * </p>
 *
 * @author lyl
 * @since 2021-07-03
 */
@Data
//生成无参构造
@NoArgsConstructor
//生成所有参数构造
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Img extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * 文件md5
     */
    private String md5;

    /**
     * 文件大小
     */
    private Long size;

    /**
     * 文件后缀名
     */
    private String suffix;

    /**
     * 文件路径
     */
    private String url;


}
