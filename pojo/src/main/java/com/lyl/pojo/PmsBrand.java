package com.lyl.pojo;

import com.lyl.pojo.BasePojo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 品牌表
 * </p>
 *
 * @author lyl
 * @since 2021-07-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PmsBrand extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * 品牌名
     */
    private String name;

    /**
     * 首字母
     */
    private String firstLetter;

    /**
     * 品牌Logo
     */
    private String logo;

    /**
     * 是否有效
     */
    private Integer active;


}
