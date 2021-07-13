package com.lyl.pojo;

import com.lyl.pojo.BasePojo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 商品sku值
 * </p>
 *
 * @author lyl
 * @since 2021-07-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PmsSkuValue extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * 关联商品id
     */
    private Long productId;

    /**
     * 属性名
     */
    private String name;

    /**
     * 属性值
     */
    private String value;


}
