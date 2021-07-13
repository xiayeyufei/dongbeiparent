package com.lyl.service;

import com.lyl.pojo.PmsSpu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * spu属性表 服务类
 * </p>
 *
 * @author lyl
 * @since 2021-07-12
 */
public interface IPmsSpuService extends IService<PmsSpu> {

    List<PmsSpu> list(Long categoryId);
    List<PmsSpu> getByCategory(Long[] categoryIds);
}
