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
 * @Description: 新建公厕统计明细表
 * @Author: jeecg-boot
 * @Date:   2020-02-21
 * @Version: V1.0
 */
@Data
@TableName("toilet_summary")
public class ToiletSummary implements Serializable {
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
	/**县区*/
	@Excel(name = "县区", width = 15)
    private java.lang.String xianqu;
	/**公厕类型*/
	@Excel(name = "公厕类型", width = 15)
    private java.lang.String leixing;
	/**公厕编号*/
	@Excel(name = "公厕编号", width = 15)
    private java.lang.String bianhao;
	/**公厕名称*/
	@Excel(name = "公厕名称", width = 15)
    private java.lang.String mingcheng;
	/**地址*/
	@Excel(name = "地址", width = 15)
    private java.lang.String dizhi;
	/**公厕类别*/
	@Excel(name = "公厕类别", width = 15)
    private java.lang.String leibie;
	/**启用年月*/
	@Excel(name = "启用年月", width = 15, format = "yyyy-MM")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM")
    @DateTimeFormat(pattern="yyyy-MM")
    private java.util.Date qiyongNianyue;
	/**开工年月*/
	@Excel(name = "开工年月", width = 15, format = "yyyy-MM")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM")
    @DateTimeFormat(pattern="yyyy-MM")
    private java.util.Date kaigongNianyue;
	/**建筑面积（㎡)*/
	@Excel(name = "建筑面积（㎡)", width = 15)
    private java.lang.Double mianji;
	/**独立*/
	@Excel(name = "独立", width = 15)
    private java.lang.String jianzhuDuli;
	/**附属*/
	@Excel(name = "附属", width = 15)
    private java.lang.String jianzhuFushu;
	/**土建式*/
	@Excel(name = "土建式", width = 15)
    private java.lang.String jiegouTujian;
	/**装配式*/
	@Excel(name = "装配式", width = 15)
    private java.lang.String jiegouZhuangpei;
	/**男蹲位数量*/
	@Excel(name = "男蹲位数量", width = 15)
    private java.lang.Integer nan;
	/**女蹲位数量*/
	@Excel(name = "女蹲位数量", width = 15)
    private java.lang.Integer nv;
	/**通用蹲位数量*/
	@Excel(name = "通用蹲位数量", width = 15)
    private java.lang.Integer tongyong;
	/**无障碍蹲位数量*/
	@Excel(name = "无障碍蹲位数量", width = 15)
    private java.lang.Integer wuzhangai;
	/**小便斗数量*/
	@Excel(name = "小便斗数量", width = 15)
    private java.lang.Integer xiaobiandou;
	/**附设功能（驿站、垃圾站、环卫管理用房等）*/
	@Excel(name = "附设功能（驿站、垃圾站、环卫管理用房等）", width = 15)
    private java.lang.String fushe;
	/**是否安装空调*/
	@Excel(name = "是否安装空调", width = 15)
    private java.lang.String kongtiao;
	/**是否安装智慧系统*/
	@Excel(name = "是否安装智慧系统", width = 15)
    private java.lang.String zhihui;
	/**备注（进展情况）*/
	@Excel(name = "备注（进展情况）", width = 15)
    private java.lang.String remarks;
}
