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
 * @Description: 环卫信息汇总表
 * @Author: jeecg-boot
 * @Date:   2020-02-21
 * @Version: V1.0
 */
@Data
@TableName("sanitation_summary")
public class SanitationSummary implements Serializable {
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
	/**保洁员（操作工）*/
	@Excel(name = "保洁员（操作工）", width = 15)
    private java.lang.Integer grBaojy;
	/**扫洒司机*/
	@Excel(name = "扫洒司机", width = 15)
    private java.lang.Integer grSijiSais;
	/**垃圾运输司机*/
	@Excel(name = "垃圾运输司机", width = 15)
    private java.lang.Integer grSijiLajysh;
	/**男*/
	@Excel(name = "男", width = 15)
    private java.lang.Integer grNan;
	/**女*/
	@Excel(name = "女", width = 15)
    private java.lang.Integer grNv;
	/**45岁及以下*/
	@Excel(name = "45岁及以下", width = 15)
    private java.lang.Integer grLessand45;
	/**45岁以上*/
	@Excel(name = "45岁以上", width = 15)
    private java.lang.Integer grMore45;
	/**临时工*/
	@Excel(name = "临时工", width = 15)
    private java.lang.Integer grLingshg;
	/**正式工*/
	@Excel(name = "正式工", width = 15)
    private java.lang.Integer grZhengshg;
	/**环卫管理人员*/
	@Excel(name = "环卫管理人员", width = 15)
    private java.lang.Integer grHuanwgl;
	/**干扫车*/
	@Excel(name = "干扫车", width = 15)
    private java.lang.Integer chlGans;
	/**洗扫车*/
	@Excel(name = "洗扫车", width = 15)
    private java.lang.Integer chlXis;
	/**真空吸尘车*/
	@Excel(name = "真空吸尘车", width = 15)
    private java.lang.Integer chlXich;
	/**扫道小型车*/
	@Excel(name = "扫道小型车", width = 15)
    private java.lang.Integer chlSaodXiao;
	/**扫道大型车*/
	@Excel(name = "扫道大型车", width = 15)
    private java.lang.Integer chlSaodDa;
	/**洒水小型车*/
	@Excel(name = "洒水小型车", width = 15)
    private java.lang.Integer chlSashXiao;
	/**洒水大型车*/
	@Excel(name = "洒水大型车", width = 15)
    private java.lang.Integer chlSashDa;
	/**雾炮车*/
	@Excel(name = "雾炮车", width = 15)
    private java.lang.Integer chlWuc;
	/**垃圾运输车*/
	@Excel(name = "垃圾运输车", width = 15)
    private java.lang.Integer chlLajysh;
	/**电动保洁车*/
	@Excel(name = "电动保洁车", width = 15)
    private java.lang.Integer chlDiandbj;
	/**普通垃圾站*/
	@Excel(name = "普通垃圾站", width = 15)
    private java.lang.Integer shshLajzh;
	/**分类垃圾站*/
	@Excel(name = "分类垃圾站", width = 15)
    private java.lang.Integer shshFenleilajzh;
	/**一类环卫公厕*/
	@Excel(name = "一类环卫公厕", width = 15)
    private java.lang.Integer shshHuanwgcYil;
	/**二类环卫公厕*/
	@Excel(name = "二类环卫公厕", width = 15)
    private java.lang.Integer shshHuanwgcErl;
	/**三类环卫公厕*/
	@Excel(name = "三类环卫公厕", width = 15)
	private java.lang.Integer shshHuanwgcSl;
	/**社会公厕*/
	@Excel(name = "社会公厕", width = 15)
    private java.lang.Integer shshShehgc;
	/**分类果壳箱*/
	@Excel(name = "分类果壳箱", width = 15)
    private java.lang.Integer shshGuokx;
	/**分类垃圾桶*/
	@Excel(name = "分类垃圾桶", width = 15)
    private java.lang.Integer shshLajt;
	/**一类道路数量*/
	@Excel(name = "一类道路数量", width = 15)
    private java.lang.Integer bjYlShul;
	/**一类道路长度*/
	@Excel(name = "一类道路长度", width = 15)
    private java.lang.Integer bjYlChangd;
	/**一类道路面积*/
	@Excel(name = "一类道路面积", width = 15)
    private java.lang.Double bjYlMianj;
	/**一类道路经费标准*/
	@Excel(name = "一类道路经费标准", width = 15)
    private java.lang.Double bjYlJinfbzh;
	/**二类道路数量*/
	@Excel(name = "二类道路数量", width = 15)
    private java.lang.Integer bjErlShul;
	/**二类道路长度*/
	@Excel(name = "二类道路长度", width = 15)
    private java.lang.Integer bjErlChangd;
	/**二类道路面积*/
	@Excel(name = "二类道路面积", width = 15)
    private java.lang.Double bjErlMianj;
	/**二类道路经费标准*/
	@Excel(name = "二类道路经费标准", width = 15)
    private java.lang.Double bjErlJinfbzh;
	/**三四类道路数量*/
	@Excel(name = "三四类道路数量", width = 15)
    private java.lang.Integer bjSslShul;
	/**三四类道路长度*/
	@Excel(name = "三四类道路长度", width = 15)
    private java.lang.Integer bjSslChangd;
	/**三四类道路面积*/
	@Excel(name = "三四类道路面积", width = 15)
    private java.lang.Double bjSslMianj;
	/**三四类道路经费标准*/
	@Excel(name = "三四类道路经费标准", width = 15)
    private java.lang.Double bjSslJinfbzh;
	/**社区数量*/
	@Excel(name = "社区数量", width = 15)
    private java.lang.Integer bjShqShul;
	/**社区面积*/
	@Excel(name = "社区面积", width = 15)
    private java.lang.Double bjShqMianj;
	/**社区经费标准*/
	@Excel(name = "社区经费标准", width = 15)
    private java.lang.Double bjShqJinfbzh;
	/**道路总面积*/
	@Excel(name = "道路总面积", width = 15)
    private java.lang.Double bjZongmj;
	/**服务外包面积*/
	@Excel(name = "服务外包面积", width = 15)
    private java.lang.Double bjWaibmj;
	/**机械化清扫面积*/
	@Excel(name = "机械化清扫面积", width = 15)
    private java.lang.Double bjJixhQingsMianj;
	/**机械化清扫长度*/
	@Excel(name = "机械化清扫长度", width = 15)
    private java.lang.Integer bjJixhQingsCang;
	/**市场化率*/
	@Excel(name = "市场化率", width = 15)
    private java.lang.Double bjShichh;
	/**机械化率*/
	@Excel(name = "机械化率", width = 15)
    private java.lang.Double bjJixieh;
	/**市场化作业单位*/
	@Excel(name = "市场化作业单位", width = 15)
    private java.lang.String bjShichDanwei;
	/**服务外包经费标准*/
	@Excel(name = "服务外包经费标准", width = 15)
    private java.lang.String bjWaibJinfbzh;
	/**环卫市场化类别*/
	@Excel(name = "环卫市场化类别_道路清扫保洁", width = 15)
    private java.lang.String shichhLeib;
	/**环卫市场化作业单位*/
	@Excel(name = "环卫市场化作业单位_道路清扫保洁", width = 15)
    private java.lang.String shichhZuiyeDanwei;
	/**经费标准*/
	@Excel(name = "经费标准_道路清扫保洁", width = 15)
    private java.lang.String shichhJinfeiBiaozh;
	/**单位组织体检人数*/
	@Excel(name = "单位组织体检人数", width = 15)
    private java.lang.Integer fulTijian;
	/**节日福利标准（包括春节、端午节、中秋节及环卫工人节）	*/
	@Excel(name = "节日福利标准（包括春节、端午节、中秋节及环卫工人节）	", width = 15)
    private java.lang.String fulJiejia;
	/**购意外伤害险人数	*/
	@Excel(name = "购意外伤害险人数	", width = 15)
    private java.lang.Integer fulYiwaiBaoxian;
	/**社会保险缴纳人数*/
	@Excel(name = "社会保险缴纳人数", width = 15)
    private java.lang.Integer fulShehuiBaoxian;
	/**意外伤害险购买金额*/
	@Excel(name = "意外伤害险购买金额", width = 15)
    private java.lang.Double fulYiwaiBaoxianJinge;
	/**基础工资标准*/
	@Excel(name = "基础工资标准", width = 15)
    private java.lang.String fulGongzbiaozh;
	/**工龄工资 标准*/
	@Excel(name = "工龄工资 标准", width = 15)
    private java.lang.String fulGonglgzbiaozh;
	/**缴纳公积金人数*/
	@Excel(name = "缴纳公积金人数", width = 15)
    private java.lang.Integer fulGongjijing;

	/**环卫市场化类别*/
	@Excel(name = "环卫市场化类别_公厕管理", width = 15)
	private java.lang.String shichhgcLeib;
	/**环卫市场化作业单位*/
	@Excel(name = "环卫市场化作业单位_公厕管理", width = 15)
	private java.lang.String shichhgcZuiyeDanwei;
	/**经费标准*/
	@Excel(name = "经费标准_公厕管理", width = 15)
	private java.lang.String shichhgcJinfeiBiaozh;

	/**环卫市场化类别*/
	@Excel(name = "环卫市场化类别_垃圾站管理", width = 15)
	private java.lang.String shichhljzLeib;
	/**环卫市场化作业单位*/
	@Excel(name = "环卫市场化作业单位_垃圾站管理", width = 15)
	private java.lang.String shichhljzZuiyeDanwei;
	/**经费标准*/
	@Excel(name = "经费标准_垃圾站管理", width = 15)
	private java.lang.String shichhljzJinfeiBiaozh;

	/**环卫市场化类别*/
	@Excel(name = "环卫市场化类别_垃圾运输管理", width = 15)
	private java.lang.String shichhljysLeib;
	/**环卫市场化作业单位*/
	@Excel(name = "环卫市场化作业单位_垃圾运输管理", width = 15)
	private java.lang.String shichhljysZuiyeDanwei;
	/**经费标准*/
	@Excel(name = "经费标准_垃圾运输管理", width = 15)
	private java.lang.String shichhljysJinfeiBiaozh;

	/**环卫市场化类别*/
	@Excel(name = "环卫市场化类别_垃圾分类", width = 15)
	private java.lang.String shichhljflLeib;
	/**环卫市场化作业单位*/
	@Excel(name = "环卫市场化作业单位_垃圾分类", width = 15)
	private java.lang.String shichhljflZuiyeDanwei;
	/**经费标准*/
	@Excel(name = "经费标准_垃圾分类", width = 15)
	private java.lang.String shichhljflJinfeiBiaozh;

	/**环卫市场化类别*/
	@Excel(name = "环卫市场化类别_其它", width = 15)
	private java.lang.String shichhqtLeib;
	/**环卫市场化作业单位*/
	@Excel(name = "环卫市场化作业单位_其它", width = 15)
	private java.lang.String shichhqtZuiyeDanwei;
	/**经费标准*/
	@Excel(name = "经费标准_其它", width = 15)
	private java.lang.String shichhqtJinfeiBiaozh;
	//完成进度1、2、3、4、5、6
	private java.lang.String step;

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

	public Integer getGrBaojy() {
		return grBaojy;
	}

	public void setGrBaojy(Integer grBaojy) {
		this.grBaojy = grBaojy;
	}

	public Integer getGrSijiSais() {
		return grSijiSais;
	}

	public void setGrSijiSais(Integer grSijiSais) {
		this.grSijiSais = grSijiSais;
	}

	public Integer getGrSijiLajysh() {
		return grSijiLajysh;
	}

	public void setGrSijiLajysh(Integer grSijiLajysh) {
		this.grSijiLajysh = grSijiLajysh;
	}

	public Integer getGrNan() {
		return grNan;
	}

	public void setGrNan(Integer grNan) {
		this.grNan = grNan;
	}

	public Integer getGrNv() {
		return grNv;
	}

	public void setGrNv(Integer grNv) {
		this.grNv = grNv;
	}

	public Integer getGrLessand45() {
		return grLessand45;
	}

	public void setGrLessand45(Integer grLessand45) {
		this.grLessand45 = grLessand45;
	}

	public Integer getGrMore45() {
		return grMore45;
	}

	public void setGrMore45(Integer grMore45) {
		this.grMore45 = grMore45;
	}

	public Integer getGrLingshg() {
		return grLingshg;
	}

	public void setGrLingshg(Integer grLingshg) {
		this.grLingshg = grLingshg;
	}

	public Integer getGrZhengshg() {
		return grZhengshg;
	}

	public void setGrZhengshg(Integer grZhengshg) {
		this.grZhengshg = grZhengshg;
	}

	public Integer getGrHuanwgl() {
		return grHuanwgl;
	}

	public void setGrHuanwgl(Integer grHuanwgl) {
		this.grHuanwgl = grHuanwgl;
	}

	public Integer getChlGans() {
		return chlGans;
	}

	public void setChlGans(Integer chlGans) {
		this.chlGans = chlGans;
	}

	public Integer getChlXis() {
		return chlXis;
	}

	public void setChlXis(Integer chlXis) {
		this.chlXis = chlXis;
	}

	public Integer getChlXich() {
		return chlXich;
	}

	public void setChlXich(Integer chlXich) {
		this.chlXich = chlXich;
	}

	public Integer getChlSaodXiao() {
		return chlSaodXiao;
	}

	public void setChlSaodXiao(Integer chlSaodXiao) {
		this.chlSaodXiao = chlSaodXiao;
	}

	public Integer getChlSaodDa() {
		return chlSaodDa;
	}

	public void setChlSaodDa(Integer chlSaodDa) {
		this.chlSaodDa = chlSaodDa;
	}

	public Integer getChlSashXiao() {
		return chlSashXiao;
	}

	public void setChlSashXiao(Integer chlSashXiao) {
		this.chlSashXiao = chlSashXiao;
	}

	public Integer getChlSashDa() {
		return chlSashDa;
	}

	public void setChlSashDa(Integer chlSashDa) {
		this.chlSashDa = chlSashDa;
	}

	public Integer getChlWuc() {
		return chlWuc;
	}

	public void setChlWuc(Integer chlWuc) {
		this.chlWuc = chlWuc;
	}

	public Integer getChlLajysh() {
		return chlLajysh;
	}

	public void setChlLajysh(Integer chlLajysh) {
		this.chlLajysh = chlLajysh;
	}

	public Integer getChlDiandbj() {
		return chlDiandbj;
	}

	public void setChlDiandbj(Integer chlDiandbj) {
		this.chlDiandbj = chlDiandbj;
	}

	public Integer getShshLajzh() {
		return shshLajzh;
	}

	public void setShshLajzh(Integer shshLajzh) {
		this.shshLajzh = shshLajzh;
	}

	public Integer getShshFenleilajzh() {
		return shshFenleilajzh;
	}

	public void setShshFenleilajzh(Integer shshFenleilajzh) {
		this.shshFenleilajzh = shshFenleilajzh;
	}

	public Integer getShshHuanwgcYil() {
		return shshHuanwgcYil;
	}

	public void setShshHuanwgcYil(Integer shshHuanwgcYil) {
		this.shshHuanwgcYil = shshHuanwgcYil;
	}

	public Integer getShshHuanwgcErl() {
		return shshHuanwgcErl;
	}

	public void setShshHuanwgcErl(Integer shshHuanwgcErl) {
		this.shshHuanwgcErl = shshHuanwgcErl;
	}

	public Integer getShshShehgc() {
		return shshShehgc;
	}

	public void setShshShehgc(Integer shshShehgc) {
		this.shshShehgc = shshShehgc;
	}

	public Integer getShshGuokx() {
		return shshGuokx;
	}

	public void setShshGuokx(Integer shshGuokx) {
		this.shshGuokx = shshGuokx;
	}

	public Integer getShshLajt() {
		return shshLajt;
	}

	public void setShshLajt(Integer shshLajt) {
		this.shshLajt = shshLajt;
	}

	public Integer getBjYlShul() {
		return bjYlShul;
	}

	public void setBjYlShul(Integer bjYlShul) {
		this.bjYlShul = bjYlShul;
	}

	public Integer getBjYlChangd() {
		return bjYlChangd;
	}

	public void setBjYlChangd(Integer bjYlChangd) {
		this.bjYlChangd = bjYlChangd;
	}

	public Double getBjYlMianj() {
		return bjYlMianj;
	}

	public void setBjYlMianj(Double bjYlMianj) {
		this.bjYlMianj = bjYlMianj;
	}

	public Double getBjYlJinfbzh() {
		return bjYlJinfbzh;
	}

	public void setBjYlJinfbzh(Double bjYlJinfbzh) {
		this.bjYlJinfbzh = bjYlJinfbzh;
	}

	public Integer getBjErlShul() {
		return bjErlShul;
	}

	public void setBjErlShul(Integer bjErlShul) {
		this.bjErlShul = bjErlShul;
	}

	public Integer getBjErlChangd() {
		return bjErlChangd;
	}

	public void setBjErlChangd(Integer bjErlChangd) {
		this.bjErlChangd = bjErlChangd;
	}

	public Double getBjErlMianj() {
		return bjErlMianj;
	}

	public void setBjErlMianj(Double bjErlMianj) {
		this.bjErlMianj = bjErlMianj;
	}

	public Double getBjErlJinfbzh() {
		return bjErlJinfbzh;
	}

	public void setBjErlJinfbzh(Double bjErlJinfbzh) {
		this.bjErlJinfbzh = bjErlJinfbzh;
	}

	public Integer getBjSslShul() {
		return bjSslShul;
	}

	public void setBjSslShul(Integer bjSslShul) {
		this.bjSslShul = bjSslShul;
	}

	public Integer getBjSslChangd() {
		return bjSslChangd;
	}

	public void setBjSslChangd(Integer bjSslChangd) {
		this.bjSslChangd = bjSslChangd;
	}

	public Double getBjSslMianj() {
		return bjSslMianj;
	}

	public void setBjSslMianj(Double bjSslMianj) {
		this.bjSslMianj = bjSslMianj;
	}

	public Double getBjSslJinfbzh() {
		return bjSslJinfbzh;
	}

	public void setBjSslJinfbzh(Double bjSslJinfbzh) {
		this.bjSslJinfbzh = bjSslJinfbzh;
	}

	public Integer getBjShqShul() {
		return bjShqShul;
	}

	public void setBjShqShul(Integer bjShqShul) {
		this.bjShqShul = bjShqShul;
	}

	public Double getBjShqMianj() {
		return bjShqMianj;
	}

	public void setBjShqMianj(Double bjShqMianj) {
		this.bjShqMianj = bjShqMianj;
	}

	public Double getBjShqJinfbzh() {
		return bjShqJinfbzh;
	}

	public void setBjShqJinfbzh(Double bjShqJinfbzh) {
		this.bjShqJinfbzh = bjShqJinfbzh;
	}

	public Double getBjZongmj() {
		return bjZongmj;
	}

	public void setBjZongmj(Double bjZongmj) {
		this.bjZongmj = bjZongmj;
	}

	public Double getBjWaibmj() {
		return bjWaibmj;
	}

	public void setBjWaibmj(Double bjWaibmj) {
		this.bjWaibmj = bjWaibmj;
	}

	public Double getBjJixhQingsMianj() {
		return bjJixhQingsMianj;
	}

	public void setBjJixhQingsMianj(Double bjJixhQingsMianj) {
		this.bjJixhQingsMianj = bjJixhQingsMianj;
	}

	public Integer getBjJixhQingsCang() {
		return bjJixhQingsCang;
	}

	public void setBjJixhQingsCang(Integer bjJixhQingsCang) {
		this.bjJixhQingsCang = bjJixhQingsCang;
	}

	public Double getBjShichh() {
		return bjShichh;
	}

	public void setBjShichh(Double bjShichh) {
		this.bjShichh = bjShichh;
	}

	public Double getBjJixieh() {
		return bjJixieh;
	}

	public void setBjJixieh(Double bjJixieh) {
		this.bjJixieh = bjJixieh;
	}

	public String getBjShichDanwei() {
		return bjShichDanwei;
	}

	public void setBjShichDanwei(String bjShichDanwei) {
		this.bjShichDanwei = bjShichDanwei;
	}

	public String getBjWaibJinfbzh() {
		return bjWaibJinfbzh;
	}

	public void setBjWaibJinfbzh(String bjWaibJinfbzh) {
		this.bjWaibJinfbzh = bjWaibJinfbzh;
	}

	public String getShichhLeib() {
		return shichhLeib;
	}

	public void setShichhLeib(String shichhLeib) {
		this.shichhLeib = shichhLeib;
	}

	public String getShichhZuiyeDanwei() {
		return shichhZuiyeDanwei;
	}

	public void setShichhZuiyeDanwei(String shichhZuiyeDanwei) {
		this.shichhZuiyeDanwei = shichhZuiyeDanwei;
	}

	public String getShichhJinfeiBiaozh() {
		return shichhJinfeiBiaozh;
	}

	public void setShichhJinfeiBiaozh(String shichhJinfeiBiaozh) {
		this.shichhJinfeiBiaozh = shichhJinfeiBiaozh;
	}

	public Integer getFulTijian() {
		return fulTijian;
	}

	public void setFulTijian(Integer fulTijian) {
		this.fulTijian = fulTijian;
	}

	public String getFulJiejia() {
		return fulJiejia;
	}

	public void setFulJiejia(String fulJiejia) {
		this.fulJiejia = fulJiejia;
	}

	public Integer getFulYiwaiBaoxian() {
		return fulYiwaiBaoxian;
	}

	public void setFulYiwaiBaoxian(Integer fulYiwaiBaoxian) {
		this.fulYiwaiBaoxian = fulYiwaiBaoxian;
	}

	public Integer getFulShehuiBaoxian() {
		return fulShehuiBaoxian;
	}

	public void setFulShehuiBaoxian(Integer fulShehuiBaoxian) {
		this.fulShehuiBaoxian = fulShehuiBaoxian;
	}

	public Double getFulYiwaiBaoxianJinge() {
		return fulYiwaiBaoxianJinge;
	}

	public void setFulYiwaiBaoxianJinge(Double fulYiwaiBaoxianJinge) {
		this.fulYiwaiBaoxianJinge = fulYiwaiBaoxianJinge;
	}

	public String getFulGongzbiaozh() {
		return fulGongzbiaozh;
	}

	public void setFulGongzbiaozh(String fulGongzbiaozh) {
		this.fulGongzbiaozh = fulGongzbiaozh;
	}

	public String getFulGonglgzbiaozh() {
		return fulGonglgzbiaozh;
	}

	public void setFulGonglgzbiaozh(String fulGonglgzbiaozh) {
		this.fulGonglgzbiaozh = fulGonglgzbiaozh;
	}

	public Integer getFulGongjijing() {
		return fulGongjijing;
	}

	public void setFulGongjijing(Integer fulGongjijing) {
		this.fulGongjijing = fulGongjijing;
	}

	public String getShichhgcLeib() {
		return shichhgcLeib;
	}

	public void setShichhgcLeib(String shichhgcLeib) {
		this.shichhgcLeib = shichhgcLeib;
	}

	public String getShichhgcZuiyeDanwei() {
		return shichhgcZuiyeDanwei;
	}

	public void setShichhgcZuiyeDanwei(String shichhgcZuiyeDanwei) {
		this.shichhgcZuiyeDanwei = shichhgcZuiyeDanwei;
	}

	public String getShichhgcJinfeiBiaozh() {
		return shichhgcJinfeiBiaozh;
	}

	public void setShichhgcJinfeiBiaozh(String shichhgcJinfeiBiaozh) {
		this.shichhgcJinfeiBiaozh = shichhgcJinfeiBiaozh;
	}

	public String getShichhljzLeib() {
		return shichhljzLeib;
	}

	public void setShichhljzLeib(String shichhljzLeib) {
		this.shichhljzLeib = shichhljzLeib;
	}

	public String getShichhljzZuiyeDanwei() {
		return shichhljzZuiyeDanwei;
	}

	public void setShichhljzZuiyeDanwei(String shichhljzZuiyeDanwei) {
		this.shichhljzZuiyeDanwei = shichhljzZuiyeDanwei;
	}

	public String getShichhljzJinfeiBiaozh() {
		return shichhljzJinfeiBiaozh;
	}

	public void setShichhljzJinfeiBiaozh(String shichhljzJinfeiBiaozh) {
		this.shichhljzJinfeiBiaozh = shichhljzJinfeiBiaozh;
	}

	public String getShichhljysLeib() {
		return shichhljysLeib;
	}

	public void setShichhljysLeib(String shichhljysLeib) {
		this.shichhljysLeib = shichhljysLeib;
	}

	public String getShichhljysZuiyeDanwei() {
		return shichhljysZuiyeDanwei;
	}

	public void setShichhljysZuiyeDanwei(String shichhljysZuiyeDanwei) {
		this.shichhljysZuiyeDanwei = shichhljysZuiyeDanwei;
	}

	public String getShichhljysJinfeiBiaozh() {
		return shichhljysJinfeiBiaozh;
	}

	public void setShichhljysJinfeiBiaozh(String shichhljysJinfeiBiaozh) {
		this.shichhljysJinfeiBiaozh = shichhljysJinfeiBiaozh;
	}

	public String getShichhljflLeib() {
		return shichhljflLeib;
	}

	public void setShichhljflLeib(String shichhljflLeib) {
		this.shichhljflLeib = shichhljflLeib;
	}

	public String getShichhljflZuiyeDanwei() {
		return shichhljflZuiyeDanwei;
	}

	public void setShichhljflZuiyeDanwei(String shichhljflZuiyeDanwei) {
		this.shichhljflZuiyeDanwei = shichhljflZuiyeDanwei;
	}

	public String getShichhljflJinfeiBiaozh() {
		return shichhljflJinfeiBiaozh;
	}

	public void setShichhljflJinfeiBiaozh(String shichhljflJinfeiBiaozh) {
		this.shichhljflJinfeiBiaozh = shichhljflJinfeiBiaozh;
	}

	public String getShichhqtLeib() {
		return shichhqtLeib;
	}

	public void setShichhqtLeib(String shichhqtLeib) {
		this.shichhqtLeib = shichhqtLeib;
	}

	public String getShichhqtZuiyeDanwei() {
		return shichhqtZuiyeDanwei;
	}

	public void setShichhqtZuiyeDanwei(String shichhqtZuiyeDanwei) {
		this.shichhqtZuiyeDanwei = shichhqtZuiyeDanwei;
	}

	public String getShichhqtJinfeiBiaozh() {
		return shichhqtJinfeiBiaozh;
	}

	public void setShichhqtJinfeiBiaozh(String shichhqtJinfeiBiaozh) {
		this.shichhqtJinfeiBiaozh = shichhqtJinfeiBiaozh;
	}

	public Integer getShshHuanwgcSl() {
		return shshHuanwgcSl;
	}

	public void setShshHuanwgcSl(Integer shshHuanwgcSl) {
		this.shshHuanwgcSl = shshHuanwgcSl;
	}
	
}
