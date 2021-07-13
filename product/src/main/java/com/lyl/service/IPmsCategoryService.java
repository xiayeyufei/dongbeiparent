package com.lyl.service;

import com.lyl.pojo.PmsCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 分类表 服务类
 * </p>
 *
 * @author lyl
 * @since 2021-07-12
 */
public interface IPmsCategoryService extends IService<PmsCategory> {
    List<PmsCategory> getByParentId(Long parentId);
    List<PmsCategory> getAll(Long parentId);
}
