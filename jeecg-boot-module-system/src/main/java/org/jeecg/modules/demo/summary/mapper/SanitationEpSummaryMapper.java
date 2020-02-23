package org.jeecg.modules.demo.summary.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.demo.summary.entity.SanitationEpSummary;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 环卫防疫汇总表
 * @Author: jeecg-boot
 * @Date:   2020-02-21
 * @Version: V1.0
 */
public interface SanitationEpSummaryMapper extends BaseMapper<SanitationEpSummary> {
    public SanitationEpSummary sumarry();
}
