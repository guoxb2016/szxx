package org.jeecg.modules.demo.summary.entity;

import java.io.Serializable;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 环卫信息表
 * @Author: jeecg-boot
 * @Date:   2020-02-26
 * @Version: V1.0
 */
@Data
public class SanitationSummaryVo implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
    private java.lang.String id;
	/**创建人*/
	//@Excel(name = "创建人", width = 15)
    private java.lang.String createBy;
	/**创建日期*/
    @Excel(name = "上报日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
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
	/**所属区县编码*/
	//@Excel(name = "所属区县编码", width = 15)
    private java.lang.String sysOrgCode;
	/**所属区县*/
	@Excel(name = "所属区县", width = 15)
    private java.lang.String sysOrgName;
	/**环卫管理人员*/
	@Excel(name = "环卫管理人员", width = 15)
    private java.lang.String hwglry;
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
	//@Excel(name = "环卫管理人员", width = 15)
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
	/**移动厕所车*/
	@Excel(name = "移动厕所车", width = 15)
    private java.lang.String ydcsc;
	/**护栏清洗车*/
	@Excel(name = "护栏清洗车", width = 15)
    private java.lang.String hlqxc;
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
    private java.lang.String shshHuanwgcSl;
	/**社会公厕*/
	@Excel(name = "社会公厕", width = 15)
    private java.lang.Integer shshShehgc;
	/**分类果壳箱*/
	@Excel(name = "分类果壳箱", width = 15)
    private java.lang.Integer shshGuokx;
	/**分类垃圾桶*/
	@Excel(name = "分类垃圾桶", width = 15)
    private java.lang.Integer shshLajt;
	/**严控区道路数量*/
	@Excel(name = "严控区道路数量", width = 15)
    private java.lang.Integer bjYlShul;
	/**严控区道路长度（m）*/
	@Excel(name = "严控区道路长度（m）", width = 15)
    private java.lang.Double bjYlChangd;
	/**严控区道路面积（m²）*/
	@Excel(name = "严控区道路面积（m²）", width = 15)
    private java.lang.Double bjYlMianj;
	/**严控区道路经费标准（元）*/
	@Excel(name = "严控区道路经费标准（元）", width = 15)
    private java.lang.Double bjYlJinfbzh;
	/**控制区道路数量*/
	@Excel(name = "控制区道路数量", width = 15)
    private java.lang.Integer bjErlShul;
	/**控制区道路长度（m）*/
	@Excel(name = "控制区道路长度（m）", width = 15)
    private java.lang.Double bjErlChangd;
	/**控制区道路面积（m²）*/
	@Excel(name = "控制区道路面积（m²）", width = 15)
    private java.lang.Double bjErlMianj;
	/**控制区道路经费标准（元）*/
	@Excel(name = "控制区道路经费标准（元）", width = 15)
    private java.lang.Double bjErlJinfbzh;
	/**一般区道路数量*/
	@Excel(name = "一般区道路数量", width = 15)
    private java.lang.Integer bjSslShul;
	/**一般区道路长度（m）*/
	@Excel(name = "一般区道路长度（m）", width = 15)
    private java.lang.Double bjSslChangd;
	/**一般区道路面积（m²）*/
	@Excel(name = "一般区道路面积（m²）", width = 15)
    private java.lang.Double bjSslMianj;
	/**一般区道路经费标准（元）*/
	@Excel(name = "一般区道路经费标准（元）", width = 15)
    private java.lang.Double bjSslJinfbzh;
	/**社区数量*/
	@Excel(name = "社区数量", width = 15)
    private java.lang.Integer bjShqShul;
	/**社区面积（m²）*/
	@Excel(name = "社区面积（m²）", width = 15)
    private java.lang.Double bjShqMianj;
	/**社区经费标准（元）*/
	@Excel(name = "社区经费标准（元）", width = 15)
    private java.lang.Double bjShqJinfbzh;
	/**道路总面积（m²）*/
	@Excel(name = "道路总面积（m²）", width = 15)
    private java.lang.Double bjZongmj;
	/**服务外包面积（m²）*/
	@Excel(name = "服务外包面积（m²）", width = 15)
    private java.lang.Double bjWaibmj;
	/**机械化清扫面积（m²）*/
	@Excel(name = "机械化清扫面积（m²）", width = 15)
    private java.lang.Double bjJixhQingsMianj;
	/**机械化清扫长度（m）*/
	@Excel(name = "机械化清扫长度（m）", width = 15)
    private java.lang.Double bjJixhQingsCang;
	/**市场化率*/
	@Excel(name = "市场化率", width = 15)
    private java.lang.Double bjShichh;
	/**机械化率*/
	@Excel(name = "机械化率", width = 15)
    private java.lang.Double bjJixieh;
	/**市场化作业单位*/
	@Excel(name = "市场化作业单位", width = 15)
    private java.lang.String bjShichDanwei;
	/**服务外包经费标准（m）*/
	@Excel(name = "服务外包经费标准（m）", width = 15)
    private java.lang.String bjWaibJinfbzh;
	/**道路清扫保洁环卫市场化类别*/
	//@Excel(name = "道路清扫保洁环卫市场化类别", width = 15)
    private java.lang.String shichhLeib;
	/**环卫市场化作业单位*/
	@Excel(name = "道路清扫保洁环卫市场化作业单位", width = 15)
    private java.lang.String shichhZuiyeDanwei;
	/**经费标准（元）*/
	@Excel(name = "经费标准（元）", width = 15)
    private java.lang.String shichhJinfeiBiaozh;
	/**公厕管理环卫市场化*/
	//@Excel(name = "公厕管理环卫市场化", width = 15)
    private java.lang.String shichhgcLeib;
	/**环卫市场化作业单位*/
	@Excel(name = "公厕管理环卫市场化作业单位", width = 15)
    private java.lang.String shichhgcZuiyeDanwei;
	/**经费标准（元）*/
	@Excel(name = "经费标准（元）", width = 15)
    private java.lang.String shichhgcJinfeiBiaozh;
	/**垃圾站环卫市场化*/
	//@Excel(name = "垃圾站环卫市场化", width = 15)
    private java.lang.String shichhljzLeib;
	/**环卫市场化作业单位*/
	@Excel(name = "垃圾站环卫市场化作业单位", width = 15)
    private java.lang.String shichhljzZuiyeDanwei;
	/**经费标准（元）*/
	@Excel(name = "经费标准（元）", width = 15)
    private java.lang.String shichhljzJinfeiBiaozh;
	/**垃运输环卫市场化*/
	//@Excel(name = "垃运输环卫市场化", width = 15)
    private java.lang.String shichhljysLeib;
	/**环卫市场化作业单位*/
	@Excel(name = "垃圾运输环卫市场化作业单位", width = 15)
    private java.lang.String shichhljysZuiyeDanwei;
	/**经费标准（元）*/
	@Excel(name = "经费标准（元）", width = 15)
    private java.lang.String shichhljysJinfeiBiaozh;
	/**垃圾分类环卫市场化*/
	//@Excel(name = "垃圾分类环卫市场化", width = 15)
    private java.lang.String shichhljflLeib;
	/**环卫市场化作业单位*/
	@Excel(name = "垃圾分类环卫市场化作业单位", width = 15)
    private java.lang.String shichhljflZuiyeDanwei;
	/**经费标准（元）*/
	@Excel(name = "经费标准（元）", width = 15)
    private java.lang.String shichhljflJinfeiBiaozh;
	/**其他环卫市场化*/
	//@Excel(name = "其他环卫市场化", width = 15)
    private java.lang.String shichhqtLeib;
	/**环卫市场化作业单位*/
	@Excel(name = "其他环卫市场化作业单位", width = 15)
    private java.lang.String shichhqtZuiyeDanwei;
	/**经费标准（元）*/
	@Excel(name = "经费标准（元）", width = 15)
    private java.lang.String shichhqtJinfeiBiaozh;
	/**单位组织体检人数*/
	@Excel(name = "单位组织体检人数", width = 15)
    private java.lang.Integer fulTijian;
	/**一年四节节日福利标准*/
	@Excel(name = "一年四节节日福利标准", width = 15)
    private java.lang.String fulJiejia;
	/**购意外伤害险人数	*/
	@Excel(name = "购意外伤害险人数	", width = 15)
    private java.lang.Integer fulYiwaiBaoxian;
	/**社会保险缴纳人数*/
	@Excel(name = "社会保险缴纳人数", width = 15)
    private java.lang.Integer fulShehuiBaoxian;
	/**意外伤害险购买金额（元）*/
	@Excel(name = "意外伤害险购买金额（元）", width = 15)
    private java.lang.Double fulYiwaiBaoxianJinge;
	/**基础工资标准（元）*/
	@Excel(name = "基础工资标准（元）", width = 15)
    private java.lang.String fulGongzbiaozh;
	/**工龄工资标准（元）*/
	@Excel(name = "工龄工资标准（元）", width = 15)
    private java.lang.String fulGonglgzbiaozh;
	/**缴纳公积金人数*/
	@Excel(name = "缴纳公积金人数", width = 15)
    private java.lang.Integer fulGongjijing;
	/**完成步骤*/
	//@Excel(name = "完成步骤", width = 15)
    private java.lang.String step;
    //01整个市区、02城区+公司、03县区、04公司
    private String query;

}
