package org.jeecg.modules.demo.summary.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.demo.summary.entity.SanitationSummary;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 环卫信息汇总表
 * @Author: jeecg-boot
 * @Date:   2020-02-21
 * @Version: V1.0
 */
public interface SanitationSummaryMapper extends BaseMapper<SanitationSummary> {
	
    public SanitationSummary summaryByOrg();

    public List<SanitationSummary> findGroupByOrg();
    
    public SanitationSummary sumQuery(@Param("type") List<String> types);
}
