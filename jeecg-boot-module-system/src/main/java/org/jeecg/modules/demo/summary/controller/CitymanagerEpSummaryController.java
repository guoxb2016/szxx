package org.jeecg.modules.demo.summary.controller;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.net.URLDecoder;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.PermissionData;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.summary.entity.CitymanagerEpSummary;
import org.jeecg.modules.demo.summary.entity.SanitationEpSummary;
import org.jeecg.modules.demo.summary.service.ICitymanagerEpSummaryService;
import org.jeecg.modules.demo.summary.service.ISanitationEpSummaryService;
import org.jeecg.modules.system.entity.SysDepart;
import org.jeecg.modules.system.entity.SysDict;
import org.jeecg.modules.system.service.ISysDepartService;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;

 /**
 * @Description: 城管防疫汇总表
 * @Author: jeecg-boot
 * @Date:   2020-02-21
 * @Version: V1.0
 */
@RestController
	@RequestMapping("/summary/citymanagerEpSummary")
@Slf4j
public class CitymanagerEpSummaryController extends JeecgController<CitymanagerEpSummary, ICitymanagerEpSummaryService> {
	@Autowired
	private ICitymanagerEpSummaryService citymanagerEpSummaryService;
	@Autowired
	private ISanitationEpSummaryService sanitationEpSummaryService;

	@Autowired
    private ISysDepartService sysDepartService;

	/**
	 * 分页列表查询
	 *
	 * @param citymanagerEpSummary
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<?> queryPageList(CitymanagerEpSummary citymanagerEpSummary,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<CitymanagerEpSummary> queryWrapper = QueryGenerator.initQueryWrapper(citymanagerEpSummary, req.getParameterMap());
		Page<CitymanagerEpSummary> page = new Page<CitymanagerEpSummary>(pageNo, pageSize);
		IPage<CitymanagerEpSummary> pageList = citymanagerEpSummaryService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 分页列表查询增加权限
	 *
	 * @param citymanagerEpSummary
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/listByPerm")
	@PermissionData(pageComponent="summary/CitymanagerEpSummaryList")
	public Result<?> queryByPermPageList(CitymanagerEpSummary citymanagerEpSummary,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<CitymanagerEpSummary> queryWrapper = QueryGenerator.initQueryWrapper(citymanagerEpSummary, req.getParameterMap());
		Page<CitymanagerEpSummary> page = new Page<CitymanagerEpSummary>(pageNo, pageSize);
		IPage<CitymanagerEpSummary> pageList = citymanagerEpSummaryService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 *   添加
	 *
	 * @param citymanagerEpSummary
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody CitymanagerEpSummary citymanagerEpSummary,HttpServletRequest req) {
		//增加组织名称
		SysDepart sd = new SysDepart();
		sd.setOrgCode(citymanagerEpSummary.getSysOrgCode());
		QueryWrapper<SysDepart> queryWrapper = QueryGenerator.initQueryWrapper(sd, req.getParameterMap());
		queryWrapper.last("order by create_time desc limit 1");
		SysDepart sysDepart = sysDepartService.getOne(queryWrapper);
		if (sysDepart != null ) {
			citymanagerEpSummary.setSysOrgCode(sysDepart.getOrgCode());
			citymanagerEpSummary.setSysOrgName(sysDepart.getDepartName());
		}
		citymanagerEpSummaryService.save(citymanagerEpSummary);
		return Result.ok(citymanagerEpSummary.getId());
	}

	/**
	 *  编辑
	 *
	 * @param citymanagerEpSummary
	 * @return
	 */
	@PostMapping(value = "/edit")
	public Result<?> edit(@RequestBody CitymanagerEpSummary citymanagerEpSummary) {
		citymanagerEpSummaryService.updateById(citymanagerEpSummary);
		return Result.ok(citymanagerEpSummary.getId());
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		citymanagerEpSummaryService.removeById(id);
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
		this.citymanagerEpSummaryService.removeByIds(Arrays.asList(ids.split(",")));
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
		CitymanagerEpSummary citymanagerEpSummary = citymanagerEpSummaryService.getById(id);
		if(citymanagerEpSummary==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(citymanagerEpSummary);
	}
	/**
	 * 查询最新上报的数据
	 * @return
	 */
	@GetMapping(value = "/queryByNew")
	public Result<?> queryByNew(CitymanagerEpSummary cs,HttpServletRequest req){
		QueryWrapper<CitymanagerEpSummary> queryWrapper = QueryGenerator.initQueryWrapper(cs, req.getParameterMap());
		queryWrapper.last("order by create_time desc limit 1");
		CitymanagerEpSummary citymanagerEpSummary = citymanagerEpSummaryService.getOne(queryWrapper);
		return  Result.ok(citymanagerEpSummary);
	}
    /**
    * 导出excel
    *
    * @param request
    * @param citymanagerEpSummary
    */
    @RequestMapping(value = "/exportXls")
    @PermissionData
    public ModelAndView exportXls(HttpServletRequest request, CitymanagerEpSummary citymanagerEpSummary) {
        return super.exportXls(request, citymanagerEpSummary, CitymanagerEpSummary.class, "城管防疫汇总表");
    }

	@RequestMapping(value = "/exportXls2")
	public void exportXls2(HttpServletRequest request, CitymanagerEpSummary citymanagerEpSummary, HttpServletResponse response) {
		//return super.exportXls(request, citymanagerEpSummary, CitymanagerEpSummary.class, "城管防疫汇总表");
		 // Step.1 组装查询条件
		 String title = "城管防疫汇总表";
		 String template = "C:/szxxTemplate/template.xls";
		 CitymanagerEpSummary citySummary = citymanagerEpSummaryService.summaryByOrg();
		 SanitationEpSummary sanitaSummary = sanitationEpSummaryService.summary();
		 InputStream in;
		 try {
			 in = new FileInputStream(new File(template));
			 HSSFWorkbook book = null;
			 book = new HSSFWorkbook(in);
			 HSSFSheet sheet = book.getSheetAt(0);
			 sheet.getRow(1).getCell(5).setCellValue(citySummary.getPersonTime());
			 sheet.getRow(2).getCell(5).setCellValue(citySummary.getStrvendPoultry());
			 sheet.getRow(3).getCell(5).setCellValue(citySummary.getStrvendWildlife());
			 sheet.getRow(4).getCell(5).setCellValue(citySummary.getStrvendOther());
			 sheet.getRow(5).getCell(5).setCellValue(citySummary.getStrvendOther());

			 sheet.getRow(6).getCell(5).setCellValue(sanitaSummary.getVehicle());
			 sheet.getRow(7).getCell(5).setCellValue(sanitaSummary.getGarbageDisposal());
			 sheet.getRow(8).getCell(5).setCellValue(sanitaSummary.getKzKouzhFeiqt());
			 sheet.getRow(9).getCell(5).setCellValue(sanitaSummary.getKzYunsl());
			 sheet.getRow(10).getCell(5).setCellValue(sanitaSummary.getXsGongc());
			 sheet.getRow(11).getCell(5).setCellValue(sanitaSummary.getXsLajz());
			 sheet.getRow(12).getCell(5).setCellValue(sanitaSummary.getXsHuanwcc());
			 sheet.getRow(13).getCell(5).setCellValue(sanitaSummary.getXsGuokx());
			 sheet.getRow(14).getCell(5).setCellValue(sanitaSummary.getHjwsLajcl());
			 sheet.getRow(15).getCell(5).setCellValue(sanitaSummary.getHjwsXiaoscc());
			 sheet.getRow(16).getCell(4).setCellValue(sanitaSummary.getHjwsHubeiJiecqk());
			 sheet.getRow(17).getCell(5).setCellValue(sanitaSummary.getFywzKouzh());
			 sheet.getRow(18).getCell(5).setCellValue(sanitaSummary.getFywzJiuj());
			 sheet.getRow(20).getCell(5).setCellValue(sanitaSummary.getFywzXiaody());
			 sheet.getRow(19).getCell(5).setCellValue(sanitaSummary.getFywzWendj());
			 sheet.getRow(21).getCell(5).setCellValue(sanitaSummary.getGongyFyqk());
			 sheet.getRow(22).getCell(4).setCellValue(sanitaSummary.getOther());

			 response.setContentType("application/vnd.ms-excel");
			 response.setHeader("content-disposition", "attachment;filename=" + title);
			 ServletOutputStream out = response.getOutputStream();
			 book.write(out);
			 out.flush();
		 } catch (IOException e) {
			 e.printStackTrace();
		 }
		 //return mv;
	 }

	 @RequestMapping(value = "/exportXls3")
	 public void exportXls3(HttpServletRequest request, CitymanagerEpSummary citymanagerEpSummary, HttpServletResponse response) {
		 String title = "城管防疫汇总表";
		 String template = "C:/szxxTemplate/template1.1.xls";
		 List<CitymanagerEpSummary> citys = citymanagerEpSummaryService.findGroupBy();
		 List<SanitationEpSummary> sans = sanitationEpSummaryService.findGroupBy();
		 InputStream in;
		 try {
			 in = new FileInputStream(new File(template));
			 HSSFWorkbook book = null;
			 book = new HSSFWorkbook(in);
			 HSSFSheet sheet1 = book.getSheetAt(0);
			 HSSFSheet sheet2 = book.getSheetAt(1);
			 for(int i=0; i<citys.size(); i++) {
			 	CitymanagerEpSummary citySummary = citys.get(i);
				 this.createCell(sheet1,4+i , 0, 4).setCellValue(i+1);
				 this.createCell(sheet1,4+i , 1, 4).setCellValue(citySummary.getSysOrgName()==null? "" :citySummary.getSysOrgName());
				 this.createCell(sheet1,4+i , 2, 4).setCellValue(citySummary.getPersonTime()==null? 0 :citySummary.getPersonTime());
				 this.createCell(sheet1,4+i , 3, 4).setCellValue(citySummary.getStrvendPoultry()==null? 0 :citySummary.getStrvendPoultry());
				 this.createCell(sheet1,4+i , 4, 4).setCellValue(citySummary.getStrvendWildlife()==null? 0 :citySummary.getStrvendWildlife());
				 this.createCell(sheet1,4+i , 5, 4).setCellValue(citySummary.getStrvendOther()==null? 0 :citySummary.getStrvendOther());
			 }
			 for(int i=0; i<sans.size(); i++){
				 SanitationEpSummary sanitaSummary = sans.get(i);
				 this.createCell(sheet2,3+i , 0, 3).setCellValue(i+1);
				 this.createCell(sheet2,3+i , 1, 3).setCellValue(sanitaSummary.getSysOrgName()==null?"":sanitaSummary.getSysOrgName());
				 this.createCell(sheet2,3+i , 2, 3).setCellValue(sanitaSummary.getVehicle()==null?0:sanitaSummary.getVehicle());
				 this.createCell(sheet2,3+i , 3, 3).setCellValue(sanitaSummary.getPersonTime()==null?0:sanitaSummary.getPersonTime());
				 this.createCell(sheet2,3+i , 4, 3).setCellValue(sanitaSummary.getGarbageDisposal()==null?0:sanitaSummary.getGarbageDisposal());
				 this.createCell(sheet2,3+i , 5, 3).setCellValue(sanitaSummary.getKzKouzhFeiqt()==null?0:sanitaSummary.getKzKouzhFeiqt());
				 this.createCell(sheet2,3+i , 6, 3).setCellValue(sanitaSummary.getKzYunsl()==null?0:sanitaSummary.getKzYunsl());
				 this.createCell(sheet2,3+i , 7, 3).setCellValue(sanitaSummary.getXsGongc()==null?0:sanitaSummary.getXsGongc());
				 this.createCell(sheet2,3+i , 8, 3).setCellValue(sanitaSummary.getXsLajz()==null?0:sanitaSummary.getXsLajz());
				 this.createCell(sheet2,3+i , 9, 3).setCellValue(sanitaSummary.getXsHuanwcc()==null?0:sanitaSummary.getXsHuanwcc());
				 this.createCell(sheet2,3+i , 10, 3).setCellValue(sanitaSummary.getXsGuokx()==null?0:sanitaSummary.getXsGuokx());
				 this.createCell(sheet2,3+i , 11, 3).setCellValue(sanitaSummary.getHjwsLajcl()==null?0:sanitaSummary.getHjwsLajcl());
				 this.createCell(sheet2,3+i , 12, 3).setCellValue(sanitaSummary.getHjwsXiaoscc()==null?0:sanitaSummary.getHjwsXiaoscc());
				 this.createCell(sheet2,3+i , 13, 3).setCellValue(sanitaSummary.getHjwsHubeiJiecqk()==null?"":sanitaSummary.getHjwsHubeiJiecqk());
				 this.createCell(sheet2,3+i , 14, 3).setCellValue(sanitaSummary.getFywzKouzh()==null?0:sanitaSummary.getFywzKouzh());
				 this.createCell(sheet2,3+i , 15, 3).setCellValue(sanitaSummary.getFywzJiuj()==null?0:sanitaSummary.getFywzJiuj());
				 this.createCell(sheet2,3+i , 17, 3).setCellValue(sanitaSummary.getFywzXiaody()==null?0:sanitaSummary.getFywzXiaody());
				 this.createCell(sheet2,3+i , 16, 3).setCellValue(sanitaSummary.getFywzWendj()==null?0:sanitaSummary.getFywzWendj());
				 this.createCell(sheet2,3+i , 18, 3).setCellValue(sanitaSummary.getGongyFyqk()==null?"":sanitaSummary.getGongyFyqk());
				 this.createCell(sheet2,3+i , 19, 3).setCellValue(sanitaSummary.getOther()==null?"":sanitaSummary.getOther());
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

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, CitymanagerEpSummary.class);
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
