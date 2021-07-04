package com.lyl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyl.pojo.PmsBrand;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 品牌表 服务类
 * </p>
 *
 * @author lyl
 * @since 2021-07-04
 */
public interface IPmsBrandService extends IService<PmsBrand> {
    IPage<PmsBrand> page(Integer pageNo,Integer pageSize,String name);
}
