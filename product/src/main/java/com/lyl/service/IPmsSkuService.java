package com.lyl.service;

import com.lyl.pojo.PmsSku;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * sku属性表 服务类
 * </p>
 *
 * @author lyl
 * @since 2021-07-12
 */
public interface IPmsSkuService extends IService<PmsSku> {
    List<PmsSku> list(Long categoryId);
    List<PmsSku> getByCategory(Long[] categoryIds);
}
