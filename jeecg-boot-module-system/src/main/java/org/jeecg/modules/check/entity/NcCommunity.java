package org.jeecg.modules.check.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;

/**
 * @Description: 开放社区
 * @Author: jeecg-boot
 * @Date:   2020-04-08
 * @Version: V1.0
 */
@Data
@TableName("nc_community")
public class NcCommunity implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**主键*/
	@TableId(type = IdType.ID_WORKER_STR)
    private java.lang.String id;
	/**创建人*/
	//@Excel(name = "创建人", width = 15)
    private java.lang.String createBy;
	/**创建日期*/
	//@Excel(name = "创建日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private java.util.Date createTime;
	/**更新人*/
	//@Excel(name = "更新人", width = 15)
    private java.lang.String updateBy;
	/**更新日期*/
	//@Excel(name = "更新日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private java.util.Date updateTime;
	/**所属部门*/
	//@Excel(name = "所属部门", width = 15)
    private java.lang.String sysOrgCode;
	/**部门名称*/
	@Excel(name = "区县名称", width = 15)
    private java.lang.String sysOrgName;
	/**社区名称*/
	@Excel(name = "社区名称", width = 15)
    private java.lang.String name;
	/**社区地址*/
	@Excel(name = "社区地址", width = 15)
    private java.lang.String address;
	/**备注*/
	@Excel(name = "备注", width = 15)
    private java.lang.String remark;
}
