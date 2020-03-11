package org.jeecg.modules.demo.summary.controller;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.net.URLDecoder;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.PermissionData;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.summary.entity.SanitationEpSummary;
import org.jeecg.modules.demo.summary.entity.SanitationSummary;
import org.jeecg.modules.demo.summary.service.ISanitationSummaryService;
import org.jeecg.modules.system.entity.SysDepart;
import org.jeecg.modules.system.service.ISysDepartService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;

 /**
 * @Description: 环卫信息汇总表
 * @Author: jeecg-boot
 * @Date:   2020-02-21
 * @Version: V1.0
 */
@RestController
@RequestMapping("/summary/sanitationSummary")
@Slf4j
public class SanitationSummaryController extends JeecgController<SanitationSummary, ISanitationSummaryService> {
	@Autowired
	private ISanitationSummaryService sanitationSummaryService;
	@Autowired
    private ISysDepartService sysDepartService;
	/**
	 * 分页列表查询
	 *
	 * @param sanitationSummary
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SanitationSummary sanitationSummary,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SanitationSummary> queryWrapper = QueryGenerator.initQueryWrapper(sanitationSummary, req.getParameterMap());
		Page<SanitationSummary> page = new Page<SanitationSummary>(pageNo, pageSize);
		IPage<SanitationSummary> pageList = sanitationSummaryService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 增加数据权限
	 *
	 * @param sanitationSummary
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/listByPerm")
	@PermissionData(pageComponent="summary/SanitationSummaryList")
	public Result<?> queryByPermPageList(SanitationSummary sanitationSummary,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SanitationSummary> queryWrapper = QueryGenerator.initQueryWrapper(sanitationSummary, req.getParameterMap());
		Page<SanitationSummary> page = new Page<SanitationSummary>(pageNo, pageSize);
		IPage<SanitationSummary> pageList = sanitationSummaryService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 *   添加
	 *
	 * @param sanitationSummary
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SanitationSummary sanitationSummary,HttpServletRequest req) {
		SysDepart sd = new SysDepart();
		sd.setOrgCode(sanitationSummary.getSysOrgCode());
		QueryWrapper<SysDepart> queryWrapper = QueryGenerator.initQueryWrapper(sd, req.getParameterMap());
		queryWrapper.last("order by create_time desc limit 1");
		SysDepart sysDepart = sysDepartService.getOne(queryWrapper);
		if (sysDepart != null ) {
			sanitationSummary.setSysOrgCode(sysDepart.getOrgCode());
			sanitationSummary.setSysOrgName(sysDepart.getDepartName());
		}
		sanitationSummaryService.save(sanitationSummary);
		return Result.ok(sanitationSummary.getId());
	}

	/**
	 *  编辑
	 *
	 * @param sanitationSummary
	 * @return
	 */
	@PostMapping(value = "/edit")
	public Result<?> edit(@RequestBody SanitationSummary sanitationSummary) {
		sanitationSummaryService.updateById(sanitationSummary);
		return Result.ok(sanitationSummary.getId());
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		sanitationSummaryService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.sanitationSummaryService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SanitationSummary sanitationSummary = sanitationSummaryService.getById(id);
		if(sanitationSummary==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(sanitationSummary);
	}
	/**
	 * 查询最新上报的数据
	 * @return
	 */
	@GetMapping(value = "/queryByNew")
	public Result<?> queryByNew(SanitationSummary cs,HttpServletRequest req){
		QueryWrapper<SanitationSummary> queryWrapper = QueryGenerator.initQueryWrapper(cs, req.getParameterMap());
		queryWrapper.last("order by create_time desc limit 1");
		SanitationSummary sanitationSummary = sanitationSummaryService.getOne(queryWrapper);
		return  Result.ok(sanitationSummary);
	}
    /**
    * 导出excel
    *
    * @param request
    * @param sanitationSummary
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, SanitationSummary sanitationSummary) {
        return super.exportXls(request, sanitationSummary, SanitationSummary.class, "环卫信息汇总表");
    }

	 @RequestMapping(value = "/exportXls2")
	 public void exportXls2(HttpServletRequest request, SanitationSummary sanitationSummary, HttpServletResponse response) {
		 //return super.exportXls(request, sanitationSummary, SanitationSummary.class, "环卫信息汇总表");
		 SanitationSummary summary = sanitationSummaryService.summaryByOrg();
		 List<SanitationSummary> list = sanitationSummaryService.list();

		 String title = "城管防疫汇总表";
		 String template = "C:/szxxTemplate/sanitationTemplate.xls";
		 InputStream in;
		 try {
			 in = new FileInputStream(new File(template));
			 HSSFWorkbook book = null;
			 book = new HSSFWorkbook(in);
			 HSSFSheet sheet = book.getSheetAt(0);
			 sheet.getRow(5).getCell(1).setCellValue(summary.getGrBaojy());
			 sheet.getRow(5).getCell(2).setCellValue(summary.getGrSijiSais());
			 sheet.getRow(5).getCell(3).setCellValue(summary.getGrSijiLajysh());
			 sheet.getRow(5).getCell(4).setCellValue(summary.getGrNan());
			 sheet.getRow(5).getCell(5).setCellValue(summary.getGrNv());
			 sheet.getRow(5).getCell(6).setCellValue(summary.getGrLessand45());
			 sheet.getRow(5).getCell(7).setCellValue(summary.getGrMore45());
			 sheet.getRow(5).getCell(8).setCellValue(summary.getGrLingshg());
			 sheet.getRow(5).getCell(9).setCellValue(summary.getGrZhengshg());

			 sheet.getRow(11).getCell(1).setCellValue(summary.getChlGans());
			 sheet.getRow(11).getCell(2).setCellValue(summary.getChlXis());
			 sheet.getRow(11).getCell(3).setCellValue(summary.getChlXich());
			 sheet.getRow(11).getCell(4).setCellValue(summary.getChlSaodXiao());
			 sheet.getRow(11).getCell(5).setCellValue(summary.getChlSaodDa());
			 sheet.getRow(11).getCell(6).setCellValue(summary.getChlSashXiao());
			 sheet.getRow(11).getCell(7).setCellValue(summary.getChlSashDa());
			 sheet.getRow(11).getCell(8).setCellValue(summary.getChlWuc());
			 sheet.getRow(11).getCell(9).setCellValue(summary.getChlLajysh());
			 sheet.getRow(11).getCell(10).setCellValue(summary.getChlDiandbj());

			 sheet.getRow(18).getCell(1).setCellValue(summary.getShshLajzh());
			 sheet.getRow(18).getCell(2).setCellValue(summary.getShshFenleilajzh());
			 sheet.getRow(18).getCell(3).setCellValue(summary.getShshHuanwgcYil());
			 sheet.getRow(18).getCell(4).setCellValue(summary.getShshHuanwgcErl());
			 sheet.getRow(18).getCell(5).setCellValue(summary.getShshHuanwgcSl());
			 sheet.getRow(18).getCell(6).setCellValue(summary.getShshShehgc());
			 sheet.getRow(18).getCell(7).setCellValue(summary.getShshGuokx());
			 sheet.getRow(18).getCell(8).setCellValue(summary.getShshFenleilajzh());

			 sheet.getRow(24).getCell(1).setCellValue(summary.getBjYlShul());
			 sheet.getRow(24).getCell(2).setCellValue(summary.getBjYlChangd());
			 sheet.getRow(24).getCell(3).setCellValue(summary.getBjYlMianj());
			 sheet.getRow(24).getCell(4).setCellValue(summary.getBjYlJinfbzh());
			 sheet.getRow(24).getCell(5).setCellValue(summary.getBjErlShul());
			 sheet.getRow(24).getCell(6).setCellValue(summary.getBjErlChangd());
			 sheet.getRow(24).getCell(7).setCellValue(summary.getBjErlMianj());
			 sheet.getRow(24).getCell(8).setCellValue(summary.getBjErlJinfbzh());
			 sheet.getRow(24).getCell(9).setCellValue(summary.getBjSslShul());
			 sheet.getRow(24).getCell(11).setCellValue(summary.getBjSslChangd());
			 sheet.getRow(24).getCell(12).setCellValue(summary.getBjSslMianj());
			 sheet.getRow(24).getCell(13).setCellValue(summary.getBjSslJinfbzh());
			 sheet.getRow(24).getCell(14).setCellValue(summary.getBjShqShul());
			 sheet.getRow(24).getCell(15).setCellValue(summary.getBjShqMianj());
			 sheet.getRow(24).getCell(16).setCellValue(summary.getBjShqJinfbzh());
			 sheet.getRow(24).getCell(17).setCellValue(summary.getBjZongmj());
			 sheet.getRow(24).getCell(18).setCellValue(summary.getBjWaibmj());
			 sheet.getRow(24).getCell(19).setCellValue(summary.getBjJixhQingsMianj());
			 sheet.getRow(24).getCell(20).setCellValue(summary.getBjJixhQingsCang());
			 sheet.getRow(24).getCell(21).setCellValue(summary.getBjShichh());
			 sheet.getRow(24).getCell(22).setCellValue(summary.getBjJixieh());
			 sheet.getRow(24).getCell(23).setCellValue(summary.getBjShichDanwei());
			 sheet.getRow(24).getCell(24).setCellValue(summary.getBjWaibJinfbzh());

			 sheet.getRow(30).getCell(1).setCellValue(summary.getFulTijian());
			 sheet.getRow(30).getCell(2).setCellValue(summary.getFulJiejia());
			 sheet.getRow(30).getCell(4).setCellValue(summary.getFulYiwaiBaoxian());
			 sheet.getRow(30).getCell(6).setCellValue(summary.getFulShehuiBaoxian());
			 sheet.getRow(30).getCell(7).setCellValue(summary.getFulYiwaiBaoxianJinge());
			 sheet.getRow(30).getCell(8).setCellValue(summary.getFulGongzbiaozh());
			 sheet.getRow(30).getCell(9).setCellValue(summary.getFulGonglgzbiaozh());
			 sheet.getRow(30).getCell(10).setCellValue(summary.getFulGongjijing());

			 for(int i=0; i < list.size(); i++){
				 SanitationSummary san = list.get(i);
				 HSSFRow[] rows = new HSSFRow[6];
				 for(int j=0; j<6; j++){
					 if(sheet.getRow(34+i*6+j) == null){
						 rows[j] = sheet.createRow(34+i*6+j);
					 }else{
						 rows[j] = sheet.getRow(34+i*6+j);
					 }
				 }
				 rows[0].createCell(1).setCellValue(san.getSysOrgCode() + "道路清扫保洁");
				 rows[0].createCell(2).setCellValue(san.getShichhZuiyeDanwei());
				 rows[0].createCell(3).setCellValue(san.getShichhgcJinfeiBiaozh());

				 rows[1].createCell(1).setCellValue(san.getSysOrgCode() + "公厕管理");
				 rows[1].createCell(2).setCellValue(san.getShichhgcZuiyeDanwei());
				 rows[1].createCell(3).setCellValue(san.getShichhgcJinfeiBiaozh());

				 rows[2].createCell(1).setCellValue(san.getSysOrgCode() + "垃圾站管理");
				 rows[2].createCell(2).setCellValue(san.getShichhljzZuiyeDanwei());
				 rows[2].createCell(3).setCellValue(san.getShichhljzJinfeiBiaozh());

				 rows[3].createCell(1).setCellValue(san.getSysOrgCode() + "垃圾运输管理");
				 rows[3].createCell(2).setCellValue(san.getShichhljysZuiyeDanwei());
				 rows[3].createCell(3).setCellValue(san.getShichhljysJinfeiBiaozh());

				 rows[4].createCell(1).setCellValue(san.getSysOrgCode() + "垃圾分类");
				 rows[4].createCell(2).setCellValue(san.getShichhljflZuiyeDanwei());
				 rows[4].createCell(3).setCellValue(san.getShichhljflJinfeiBiaozh());

				 rows[5].createCell(1).setCellValue(san.getSysOrgCode() + "其它");
				 rows[5].createCell(2).setCellValue(san.getShichhqtZuiyeDanwei());
				 rows[5].createCell(3).setCellValue(san.getShichhqtJinfeiBiaozh());
			 }

			 response.setContentType("application/vnd.ms-excel");
			 response.setHeader("content-disposition", "attachment;filename=" + title);
			 ServletOutputStream out = response.getOutputStream();
			 book.write(out);
			 out.flush();
		 } catch (IOException e) {
			 e.printStackTrace();
		 }
	 }

	 @RequestMapping(value = "/exportXls3")
	 public void exportXls3(HttpServletRequest request, SanitationSummary sanitationSummary, HttpServletResponse response) {
		 String title = "环卫信息汇总表";
		 String template = "C:/szxxTemplate/sanitationTemplate1.1.xls";
		 List<SanitationSummary> list = sanitationSummaryService.findGroupByOrg();
		 InputStream in;
		 try {
			 in = new FileInputStream(new File(template));
			 HSSFWorkbook book = null;
			 book = new HSSFWorkbook(in);
			 HSSFSheet sheet[] = {
			 		 book.getSheet("环卫一线工人"),
					 book.getSheet("环卫车辆"),
					 book.getSheet("环卫设施"),
					 book.getSheet("道路清扫保洁"),
					 book.getSheet("环卫工人福利"),
					 book.getSheet("环卫市场化情况")
			 };
			 for (int i=0; i<list.size(); i++){
				 SanitationSummary s = list.get(i);
				 Row row[] = new Row[6];

                 this.createCell(sheet[0], 5+i, 0, 5).setCellValue(i+1);
                 this.createCell(sheet[0], 5+i, 1, 5).setCellValue(s.getSysOrgName()==null? "" :s.getSysOrgName());
                 this.createCell(sheet[0], 5+i, 2, 5).setCellValue(s.getGrBaojy()==null? 0 :s.getGrBaojy());
                 this.createCell(sheet[0], 5+i, 3, 5).setCellValue(s.getGrSijiSais()==null? 0 :s.getGrSijiSais());
                 this.createCell(sheet[0], 5+i, 4, 5).setCellValue(s.getGrSijiLajysh()==null? 0 :s.getGrSijiLajysh());
                 this.createCell(sheet[0], 5+i, 5, 5).setCellValue(s.getGrNan()==null? 0 :s.getGrNan());
                 this.createCell(sheet[0], 5+i, 6, 5).setCellValue(s.getGrNv()==null? 0 :s.getGrNv());
                 this.createCell(sheet[0], 5+i, 7, 5).setCellValue(s.getGrLessand45()==null? 0 :s.getGrLessand45());
                 this.createCell(sheet[0], 5+i, 8, 5).setCellValue(s.getGrMore45()==null? 0 :s.getGrMore45());
                 this.createCell(sheet[0], 5+i, 9, 5).setCellValue(s.getGrLingshg()==null? 0 :s.getGrLingshg());
                 this.createCell(sheet[0], 5+i, 10, 5).setCellValue(s.getGrZhengshg()==null? 0 :s.getGrZhengshg());
                 this.createCell(sheet[0], 5+i, 11, 5).setCellValue(s.getHwglry()==null? "" :s.getHwglry());

                 this.createCell(sheet[1], 4+i, 0, 4).setCellValue(i+1);
                 this.createCell(sheet[1], 4+i, 1, 4).setCellValue(s.getSysOrgName()==null? "" :s.getSysOrgName());
                 this.createCell(sheet[1], 4+i, 2, 4).setCellValue(s.getChlGans()==null? 0 :s.getChlGans());
                 this.createCell(sheet[1], 4+i, 3, 4).setCellValue(s.getChlXis()==null? 0 :s.getChlXis());
                 this.createCell(sheet[1], 4+i, 4, 4).setCellValue(s.getChlXich()==null? 0 :s.getChlXich());
                 this.createCell(sheet[1], 4+i, 5, 4).setCellValue(s.getChlSaodXiao()==null? 0 :s.getChlSaodXiao());
                 this.createCell(sheet[1], 4+i, 6, 4).setCellValue(s.getChlSaodDa()==null? 0 :s.getChlSaodDa());
                 this.createCell(sheet[1], 4+i, 7, 4).setCellValue(s.getChlSashXiao()==null? 0 :s.getChlSashXiao());
                 this.createCell(sheet[1], 4+i, 8, 4).setCellValue(s.getChlSashDa()==null? 0 :s.getChlSashDa());
                 this.createCell(sheet[1], 4+i, 9, 4).setCellValue(s.getChlWuc()==null? 0 :s.getChlWuc());
                 this.createCell(sheet[1], 4+i, 10, 4).setCellValue(s.getChlLajysh()==null? 0 :s.getChlLajysh());
                 this.createCell(sheet[1], 4+i, 11, 4).setCellValue(s.getChlDiandbj()==null? 0 :s.getChlDiandbj());
                 this.createCell(sheet[1], 4+i, 12, 4).setCellValue(s.getYdcsc()==null? "" :s.getYdcsc());
                 this.createCell(sheet[1], 4+i, 13, 4).setCellValue(s.getHlqxc()==null? "" :s.getHlqxc());

                 this.createCell(sheet[2], 5+i, 0, 5).setCellValue(i+1);
                 this.createCell(sheet[2], 5+i, 1, 5).setCellValue(s.getSysOrgName()==null? "" :s.getSysOrgName());
                 this.createCell(sheet[2], 5+i, 2, 5).setCellValue(s.getShshLajzh()==null? 0 :s.getShshLajzh());
                 this.createCell(sheet[2], 5+i, 3, 5).setCellValue(s.getShshFenleilajzh()==null? 0 :s.getShshFenleilajzh());
                 this.createCell(sheet[2], 5+i, 4, 5).setCellValue(s.getShshHuanwgcYil()==null? 0 :s.getShshHuanwgcYil());
                 this.createCell(sheet[2], 5+i, 5, 5).setCellValue(s.getShshHuanwgcErl()==null? 0 :s.getShshHuanwgcErl());
                 this.createCell(sheet[2], 5+i, 6, 5).setCellValue(s.getShshHuanwgcSl()==null? "" :s.getShshHuanwgcSl());
                 this.createCell(sheet[2], 5+i, 7, 5).setCellValue(s.getShshShehgc()==null? 0 :s.getShshShehgc());
                 this.createCell(sheet[2], 5+i, 8, 5).setCellValue(s.getShshGuokx()==null? 0 :s.getShshGuokx());
                 this.createCell(sheet[2], 5+i, 9, 5).setCellValue(s.getShshFenleilajzh()==null? 0 :s.getShshFenleilajzh());

                 this.createCell(sheet[3], 4+i, 0, 4).setCellValue(i+1);
                 this.createCell(sheet[3], 4+i, 1, 4).setCellValue(s.getSysOrgName()==null? "" :s.getSysOrgName());
                 this.createCell(sheet[3], 4+i, 2, 4).setCellValue(s.getBjYlShul()==null? 0 :s.getBjYlShul());
                 this.createCell(sheet[3], 4+i, 3, 4).setCellValue(s.getBjYlChangd()==null? 0 :s.getBjYlChangd());
                 this.createCell(sheet[3], 4+i, 4, 4).setCellValue(s.getBjYlMianj()==null? 0 :s.getBjYlMianj());
                 this.createCell(sheet[3], 4+i, 5, 4).setCellValue(s.getBjYlJinfbzh()==null? 0 :s.getBjYlJinfbzh());
                 this.createCell(sheet[3], 4+i, 6, 4).setCellValue(s.getBjErlShul()==null? 0 :s.getBjErlShul());
                 this.createCell(sheet[3], 4+i, 7, 4).setCellValue(s.getBjErlChangd()==null? 0 :s.getBjErlChangd());
                 this.createCell(sheet[3], 4+i, 8, 4).setCellValue(s.getBjErlMianj()==null? 0 :s.getBjErlMianj());
                 this.createCell(sheet[3], 4+i, 9, 4).setCellValue(s.getBjErlJinfbzh()==null? 0 :s.getBjErlJinfbzh());
                 this.createCell(sheet[3], 4+i, 10, 4).setCellValue(s.getBjSslShul()==null? 0 :s.getBjSslShul());
                 this.createCell(sheet[3], 4+i, 11, 4).setCellValue(s.getBjSslChangd()==null? 0 :s.getBjSslChangd());
                 this.createCell(sheet[3], 4+i, 12, 4).setCellValue(s.getBjSslMianj()==null? 0 :s.getBjSslMianj());
                 this.createCell(sheet[3], 4+i, 13, 4).setCellValue(s.getBjSslJinfbzh()==null? 0 :s.getBjSslJinfbzh());
                 this.createCell(sheet[3], 4+i, 14, 4).setCellValue(s.getBjShqShul()==null? 0 :s.getBjShqShul());
                 this.createCell(sheet[3], 4+i, 15, 4).setCellValue(s.getBjShqMianj()==null? 0 :s.getBjShqMianj());
                 this.createCell(sheet[3], 4+i, 16, 4).setCellValue(s.getBjShqJinfbzh()==null? 0 :s.getBjShqJinfbzh());
                 this.createCell(sheet[3], 4+i, 17, 4).setCellValue(s.getBjZongmj()==null? 0 :s.getBjZongmj());
                 this.createCell(sheet[3], 4+i, 18, 4).setCellValue(s.getBjWaibmj()==null? 0 :s.getBjWaibmj());
                 this.createCell(sheet[3], 4+i, 19, 4).setCellValue(s.getBjJixhQingsMianj()==null? 0 :s.getBjJixhQingsMianj());
                 this.createCell(sheet[3], 4+i, 20, 4).setCellValue(s.getBjJixhQingsCang()==null? 0 :s.getBjJixhQingsCang());
                 this.createCell(sheet[3], 4+i, 21, 4).setCellValue(s.getBjShichh()==null? 0 :s.getBjShichh());
                 this.createCell(sheet[3], 4+i, 22, 4).setCellValue(s.getBjJixieh()==null? 0 :s.getBjJixieh());
                 this.createCell(sheet[3], 4+i, 23, 4).setCellValue(s.getBjShichDanwei()==null? "" :s.getBjShichDanwei());
                 this.createCell(sheet[3], 4+i, 24, 4).setCellValue(s.getBjWaibJinfbzh()==null? "" :s.getBjWaibJinfbzh());

                 this.createCell(sheet[4], 3+i, 0, 3).setCellValue(i+1);
                 this.createCell(sheet[4], 3+i, 1, 3).setCellValue(s.getSysOrgName()==null? "" :s.getSysOrgName());
                 this.createCell(sheet[4], 3+i, 2, 3).setCellValue(s.getFulTijian()==null? 0 :s.getFulTijian());
                 this.createCell(sheet[4], 3+i, 3, 3).setCellValue(s.getFulJiejia()==null? "" :s.getFulJiejia());
                 this.createCell(sheet[4], 3+i, 4, 3).setCellValue(s.getFulYiwaiBaoxian()==null? 0 :s.getFulYiwaiBaoxian());
                 this.createCell(sheet[4], 3+i, 5, 3).setCellValue(s.getFulShehuiBaoxian()==null? 0 :s.getFulShehuiBaoxian());
                 this.createCell(sheet[4], 3+i, 6, 3).setCellValue(s.getFulYiwaiBaoxianJinge()==null? 0 :s.getFulYiwaiBaoxianJinge());
                 this.createCell(sheet[4], 3+i, 7, 3).setCellValue(s.getFulGongzbiaozh()==null? "" :s.getFulGongzbiaozh());
                 this.createCell(sheet[4], 3+i, 8, 3).setCellValue(s.getFulGonglgzbiaozh()==null? "" :s.getFulGonglgzbiaozh());
                 this.createCell(sheet[4], 3+i, 9, 3).setCellValue(s.getFulGongjijing()==null? 0 :s.getFulGongjijing());


                 this.createCell(sheet[5], 3+i*6, 0, 3).setCellValue(i*6+1);
                 this.createCell(sheet[5], 3+i*6, 1, 3).setCellValue(s.getSysOrgName()==null? "":s.getSysOrgName());
                 this.createCell(sheet[5], 3+i*6, 2, 3).setCellValue("道路清扫保洁");
                 this.createCell(sheet[5], 3+i*6, 3, 3).setCellValue(s.getShichhZuiyeDanwei()==null? "":s.getShichhZuiyeDanwei());
                 this.createCell(sheet[5], 3+i*6, 4, 3).setCellValue(s.getShichhgcJinfeiBiaozh()==null? "":s.getShichhgcJinfeiBiaozh());

                 this.createCell(sheet[5], 4+i*6, 0, 3).setCellValue(i*6+2);
                 this.createCell(sheet[5], 4+i*6, 1, 3).setCellValue(s.getSysOrgName()==null? "":s.getSysOrgName());
                 this.createCell(sheet[5], 4+i*6, 2, 3).setCellValue("公厕管理");
                 this.createCell(sheet[5], 4+i*6, 3, 3).setCellValue(s.getShichhgcZuiyeDanwei()==null? "":s.getShichhgcZuiyeDanwei());
                 this.createCell(sheet[5], 4+i*6, 4, 3).setCellValue(s.getShichhgcJinfeiBiaozh()==null? "":s.getShichhgcJinfeiBiaozh());

                 this.createCell(sheet[5], 5+i*6, 0, 3).setCellValue(i*6+3);
                 this.createCell(sheet[5], 5+i*6, 1, 3).setCellValue(s.getSysOrgName()==null? "":s.getSysOrgName());
                 this.createCell(sheet[5], 5+i*6, 2, 3).setCellValue("垃圾站管理");
                 this.createCell(sheet[5], 5+i*6, 3, 3).setCellValue(s.getShichhljzZuiyeDanwei()==null? "":s.getShichhljzZuiyeDanwei());
                 this.createCell(sheet[5], 5+i*6, 4, 3).setCellValue(s.getShichhljzJinfeiBiaozh()==null? "":s.getShichhljzJinfeiBiaozh());

                 this.createCell(sheet[5], 6+i*6, 0, 3).setCellValue(i*6+4);
                 this.createCell(sheet[5], 6+i*6, 1, 3).setCellValue(s.getSysOrgName()==null? "":s.getSysOrgName());
                 this.createCell(sheet[5], 6+i*6, 2, 3).setCellValue("垃圾运输管理");
                 this.createCell(sheet[5], 6+i*6, 3, 3).setCellValue(s.getShichhljysZuiyeDanwei()==null? "":s.getShichhljysZuiyeDanwei());
                 this.createCell(sheet[5], 6+i*6, 4, 3).setCellValue(s.getShichhljysJinfeiBiaozh()==null? "":s.getShichhljysJinfeiBiaozh());

                 this.createCell(sheet[5], 7+i*6, 0, 3).setCellValue(i*6+5);
                 this.createCell(sheet[5], 7+i*6, 1, 3).setCellValue(s.getSysOrgName()==null? "":s.getSysOrgName());
                 this.createCell(sheet[5], 7+i*6, 2, 3).setCellValue("垃圾分类");
                 this.createCell(sheet[5], 7+i*6, 3, 3).setCellValue(s.getShichhljflZuiyeDanwei()==null? "":s.getShichhljflZuiyeDanwei());
                 this.createCell(sheet[5], 7+i*6, 4, 3).setCellValue(s.getShichhljflJinfeiBiaozh()==null? "":s.getShichhljflJinfeiBiaozh());

                 this.createCell(sheet[5], 8+i*6, 0, 3).setCellValue(i*6+6);
                 this.createCell(sheet[5], 8+i*6, 1, 3).setCellValue(s.getSysOrgName()==null? "":s.getSysOrgName());
                 this.createCell(sheet[5], 8+i*6, 2, 3).setCellValue("其它");
                 this.createCell(sheet[5], 8+i*6, 3, 3).setCellValue(s.getShichhqtZuiyeDanwei()==null? "":s.getShichhqtZuiyeDanwei());
                 this.createCell(sheet[5], 8+i*6, 4, 3).setCellValue(s.getShichhqtJinfeiBiaozh()==null? "":s.getShichhqtJinfeiBiaozh());
			 }
			 response.setContentType("application/vnd.ms-excel");
			 response.setHeader("content-disposition", "attachment;filename=" + title);
			 ServletOutputStream out = response.getOutputStream();
			 book.write(out);
			 out.flush();
		 } catch (IOException e){
		 	e.printStackTrace();
		 }
	 }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, SanitationSummary.class);
    }

    public Cell createCell(Sheet sheet, int rowNum, int column, int styleRow){
        int rowIndex = sheet.getLastRowNum() + 1;
        Row row = sheet.getRow(rowNum) == null?sheet.createRow(rowIndex):sheet.getRow(rowNum);
		Cell cell=row.getCell(column)==null?row.createCell(column):row.getCell(column);  // 创建单元格

        CellStyle cellStyle=sheet.getRow(styleRow).getCell(column).getCellStyle();
		cell.setCellStyle(cellStyle);
		return cell;
	}

}
