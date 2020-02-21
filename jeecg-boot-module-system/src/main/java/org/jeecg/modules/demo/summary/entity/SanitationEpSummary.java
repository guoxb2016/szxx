package org.jeecg.modules.demo.summary.entity;

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
 * @Description: 环卫防疫汇总表
 * @Author: jeecg-boot
 * @Date:   2020-02-21
 * @Version: V1.0
 */
@Data
@TableName("sanitation_ep_summary")
public class SanitationEpSummary implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**主键*/
	@TableId(type = IdType.ID_WORKER_STR)
    private java.lang.String id;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    private java.lang.String createBy;
	/**创建日期*/
	@Excel(name = "创建日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private java.util.Date createTime;
	/**更新人*/
	@Excel(name = "更新人", width = 15)
    private java.lang.String updateBy;
	/**更新日期*/
	@Excel(name = "更新日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private java.util.Date updateTime;
	/**所属部门*/
	@Excel(name = "所属部门", width = 15)
    private java.lang.String sysOrgCode;
	/**环卫作业车辆*/
	@Excel(name = "环卫作业车辆", width = 15)
    private java.lang.Integer vehicle;
	/**环卫作业人员*/
	@Excel(name = "环卫作业人员", width = 15)
    private java.lang.Integer personTime;
	/**生活垃圾无害化处理*/
	@Excel(name = "生活垃圾无害化处理", width = 15)
    private java.lang.Double garbageDisposal;
	/**口罩废弃桶*/
	@Excel(name = "口罩废弃桶", width = 15)
    private java.lang.Integer kzKouzhFeiqt;
	/**运输量*/
	@Excel(name = "运输量", width = 15)
    private java.lang.Integer kzYunsl;
	/**消杀-公厕*/
	@Excel(name = "消杀-公厕", width = 15)
    private java.lang.Integer xsGongc;
	/**消杀-垃圾站*/
	@Excel(name = "消杀-垃圾站", width = 15)
    private java.lang.Integer xsLajz;
	/**消杀-环卫作业车辆*/
	@Excel(name = "消杀-环卫作业车辆", width = 15)
    private java.lang.Integer xsHuanwcc;
	/**消杀-果壳箱与垃圾桶*/
	@Excel(name = "消杀-果壳箱与垃圾桶", width = 15)
    private java.lang.Integer xsGuokx;
	/**生活垃圾无害化处理*/
	@Excel(name = "生活垃圾无害化处理", width = 15)
    private java.lang.Double hjwsLajcl;
	/**单位内部疫情防控消杀处理情况*/
	@Excel(name = "单位内部疫情防控消杀处理情况", width = 15)
    private java.lang.Integer hjwsXiaoscc;
	/**本单位职工涉湖北接触情况*/
	@Excel(name = "本单位职工涉湖北接触情况", width = 15)
    private java.lang.String hjwsHubeiJiecqk;
	/**口罩*/
	@Excel(name = "口罩", width = 15)
    private java.lang.Integer fywzKouzh;
	/**酒精*/
	@Excel(name = "酒精", width = 15)
    private java.lang.Double fywzJiuj;
	/**温度计*/
	@Excel(name = "温度计", width = 15)
    private java.lang.Integer fywzWendj;
	/**消毒液*/
	@Excel(name = "消毒液", width = 15)
    private java.lang.Double fywzXiaody;
	/**人流量，设施消杀及运行情况*/
	@Excel(name = "人流量，设施消杀及运行情况", width = 15)
    private java.lang.String gongyFyqk;
	/**其它*/
	@Excel(name = "其它", width = 15)
    private java.lang.String other;
}
