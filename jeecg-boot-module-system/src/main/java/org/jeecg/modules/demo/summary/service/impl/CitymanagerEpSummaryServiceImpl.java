package org.jeecg.modules.demo.summary.service.impl;

import org.jeecg.modules.demo.summary.entity.CitymanagerEpSummary;
import org.jeecg.modules.demo.summary.mapper.CitymanagerEpSummaryMapper;
import org.jeecg.modules.demo.summary.service.ICitymanagerEpSummaryService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 城管防疫汇总表
 * @Author: jeecg-boot
 * @Date:   2020-02-21
 * @Version: V1.0
 */
@Service
public class CitymanagerEpSummaryServiceImpl extends ServiceImpl<CitymanagerEpSummaryMapper, CitymanagerEpSummary> implements ICitymanagerEpSummaryService {

    @Override
    public CitymanagerEpSummary summaryByOrg() {
        return baseMapper.summaryByOrg();
    }

    @Override
    public List<CitymanagerEpSummary> findGroupBy() {
        return baseMapper.findGroupByOrg();
    }
}
