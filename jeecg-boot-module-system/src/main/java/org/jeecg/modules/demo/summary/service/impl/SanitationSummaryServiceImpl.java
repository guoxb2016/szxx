package org.jeecg.modules.demo.summary.service.impl;

import org.jeecg.modules.demo.summary.entity.SanitationSummary;
import org.jeecg.modules.demo.summary.mapper.SanitationSummaryMapper;
import org.jeecg.modules.demo.summary.service.ISanitationSummaryService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 环卫信息汇总表
 * @Author: jeecg-boot
 * @Date:   2020-02-21
 * @Version: V1.0
 */
@Service
public class SanitationSummaryServiceImpl extends ServiceImpl<SanitationSummaryMapper, SanitationSummary> implements ISanitationSummaryService {

    @Override
    public SanitationSummary summaryByOrg() {
        return baseMapper.summaryByOrg();
    }

    @Override
    public List<SanitationSummary> findGroupByOrg() {
        return baseMapper.findGroupByOrg();
    }
}
