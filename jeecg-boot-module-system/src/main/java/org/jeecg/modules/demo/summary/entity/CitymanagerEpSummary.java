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
 * @Description: 城管防疫汇总表
 * @Author: jeecg-boot
 * @Date:   2020-02-21
 * @Version: V1.0
 */
@Data
@TableName("citymanager_ep_summary")
public class CitymanagerEpSummary implements Serializable {
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
	/**出动执法人员次数*/
	@Excel(name = "出动执法人员次数", width = 15)
    private java.lang.Integer personTime;
	/**活禽宰杀流动摊贩管理*/
	@Excel(name = "活禽宰杀流动摊贩管理", width = 15)
    private java.lang.Integer strvendPoultry;
	/**贩卖野生动物流动摊贩管理*/
	@Excel(name = "贩卖野生动物流动摊贩管理", width = 15)
    private java.lang.Integer strvendWildlife;
	/**其它流动摊贩*/
	@Excel(name = "其它流动摊贩", width = 15)
    private java.lang.Integer strvendOther;
	/**备注*/
	@Excel(name = "备注", width = 15)
    private java.lang.String remarks;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getSysOrgCode() {
		return sysOrgCode;
	}

	public void setSysOrgCode(String sysOrgCode) {
		this.sysOrgCode = sysOrgCode;
	}

	public Integer getPersonTime() {
		return personTime;
	}

	public void setPersonTime(Integer personTime) {
		this.personTime = personTime;
	}

	public Integer getStrvendPoultry() {
		return strvendPoultry;
	}

	public void setStrvendPoultry(Integer strvendPoultry) {
		this.strvendPoultry = strvendPoultry;
	}

	public Integer getStrvendWildlife() {
		return strvendWildlife;
	}

	public void setStrvendWildlife(Integer strvendWildlife) {
		this.strvendWildlife = strvendWildlife;
	}

	public Integer getStrvendOther() {
		return strvendOther;
	}

	public void setStrvendOther(Integer strvendOther) {
		this.strvendOther = strvendOther;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
