package com.lyl.service.impl;

import com.lyl.pojo.PmsStock;
import com.lyl.mapper.PmsStockMapper;
import com.lyl.service.IPmsStockService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 库存表 服务实现类
 * </p>
 *
 * @author lyl
 * @since 2021-07-13
 */
@Service
public class PmsStockServiceImpl extends ServiceImpl<PmsStockMapper, PmsStock> implements IPmsStockService {

}
