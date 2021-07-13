package com.lyl.pojo;

import com.lyl.pojo.BasePojo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * spu属性表
 * </p>
 *
 * @author lyl
 * @since 2021-07-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PmsSpu extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * 关联类别
     */
    private Long categoryId;

    /**
     * 属性名称
     */
    private String name;


}
