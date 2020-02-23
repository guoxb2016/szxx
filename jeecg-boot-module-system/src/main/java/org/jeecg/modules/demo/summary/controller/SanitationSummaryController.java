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

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.summary.entity.SanitationEpSummary;
import org.jeecg.modules.demo.summary.entity.SanitationSummary;
import org.jeecg.modules.demo.summary.service.ISanitationSummaryService;

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
	 *   添加
	 *
	 * @param sanitationSummary
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SanitationSummary sanitationSummary) {
		sanitationSummaryService.save(sanitationSummary);
		return Result.ok("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param sanitationSummary
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SanitationSummary sanitationSummary) {
		sanitationSummaryService.updateById(sanitationSummary);
		return Result.ok("编辑成功!");
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
		 String template = "C:/szxxTemplate/环卫信息统计表.xls";
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

}
