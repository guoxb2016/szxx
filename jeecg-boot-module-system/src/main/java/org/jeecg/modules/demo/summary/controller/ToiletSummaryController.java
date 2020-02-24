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

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.summary.entity.ToiletSummary;
import org.jeecg.modules.demo.summary.service.IToiletSummaryService;

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
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;

 /**
 * @Description: 新建公厕统计明细表
 * @Author: jeecg-boot
 * @Date:   2020-02-21
 * @Version: V1.0
 */
@RestController
@RequestMapping("/summary/toiletSummary")
@Slf4j
public class ToiletSummaryController extends JeecgController<ToiletSummary, IToiletSummaryService> {
	@Autowired
	private IToiletSummaryService toiletSummaryService;

	/**
	 * 分页列表查询
	 *
	 * @param toiletSummary
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ToiletSummary toiletSummary,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ToiletSummary> queryWrapper = QueryGenerator.initQueryWrapper(toiletSummary, req.getParameterMap());
		Page<ToiletSummary> page = new Page<ToiletSummary>(pageNo, pageSize);
		IPage<ToiletSummary> pageList = toiletSummaryService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 *   添加
	 *
	 * @param toiletSummary
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ToiletSummary toiletSummary) {
		toiletSummaryService.save(toiletSummary);
		return Result.ok(toiletSummary.getId());
	}

	/**
	 *  编辑
	 *
	 * @param toiletSummary
	 * @return
	 */
	@PostMapping(value = "/edit")
	public Result<?> edit(@RequestBody ToiletSummary toiletSummary) {
		toiletSummaryService.updateById(toiletSummary);
		return Result.ok(toiletSummary.getId());
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		toiletSummaryService.removeById(id);
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
		this.toiletSummaryService.removeByIds(Arrays.asList(ids.split(",")));
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
		ToiletSummary toiletSummary = toiletSummaryService.getById(id);
		if(toiletSummary==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(toiletSummary);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param toiletSummary
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ToiletSummary toiletSummary) {
        return super.exportXls(request, toiletSummary, ToiletSummary.class, "新建公厕统计明细表");
    }

	 @RequestMapping(value = "/exportXls2")
	 public void exportXls2(HttpServletRequest request, ToiletSummary toiletSummary, HttpServletResponse response) {
		 //return super.exportXls(request, toiletSummary, ToiletSummary.class, "新建公厕统计明细表");
		 List<ToiletSummary> list = toiletSummaryService.list();

		 String title = "城管防疫汇总表";
		 String template = "C:/szxxTemplate/toiletTemplate.xls";
		 InputStream in;
		 try {
			 in = new FileInputStream(new File(template));
			 HSSFWorkbook book = null;
			 book = new HSSFWorkbook(in);
			 HSSFSheet sheet = book.getSheetAt(0);
			 for(int i=0; i < list.size(); i++){
				 ToiletSummary toilet = list.get(i);
				 sheet.getRow(4+i).getCell(0).setCellValue(i);
				 sheet.getRow(4+i).getCell(1).setCellValue(toilet.getXianqu());
				 sheet.getRow(4+i).getCell(2).setCellValue(toilet.getLeixing());
				 sheet.getRow(4+i).getCell(3).setCellValue(toilet.getBianhao());
				 sheet.getRow(4+i).getCell(4).setCellValue(toilet.getMingcheng());
				 sheet.getRow(4+i).getCell(5).setCellValue(toilet.getDizhi());
				 sheet.getRow(4+i).getCell(6).setCellValue(toilet.getLeibie());
				 sheet.getRow(4+i).getCell(7).setCellValue(toilet.getQiyongNianyue());
				 sheet.getRow(4+i).getCell(8).setCellValue(toilet.getKaigongNianyue());
				 sheet.getRow(4+i).getCell(9).setCellValue(toilet.getMianji());
				 sheet.getRow(4+i).getCell(10).setCellValue(toilet.getJianzhuDuli());
				 sheet.getRow(4+i).getCell(11).setCellValue(toilet.getJianzhuFushu());
				 sheet.getRow(4+i).getCell(12).setCellValue(toilet.getJiegouTujian());
				 sheet.getRow(4+i).getCell(13).setCellValue(toilet.getJiegouZhuangpei());
				 sheet.getRow(4+i).getCell(14).setCellValue(toilet.getNan());
				 sheet.getRow(4+i).getCell(15).setCellValue(toilet.getNv());
				 sheet.getRow(4+i).getCell(16).setCellValue(toilet.getTongyong());
				 sheet.getRow(4+i).getCell(17).setCellValue(toilet.getWuzhangai());
				 sheet.getRow(4+i).getCell(18).setCellValue(toilet.getXiaobiandou());
				 sheet.getRow(4+i).getCell(19).setCellValue(toilet.getFushe());
				 sheet.getRow(4+i).getCell(20).setCellValue(toilet.getKongtiao());
				 sheet.getRow(4+i).getCell(21).setCellValue(toilet.getZhihui());
				 sheet.getRow(4+i).getCell(22).setCellValue(toilet.getRemarks());
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
        return super.importExcel(request, response, ToiletSummary.class);
    }

	 @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void test() {
		 try {
			 String path = ResourceUtils.getURL("classpath:").getPath();
			 String path1 = ClassUtils.getDefaultClassLoader().getResource("").getPath();
			 System.out.println(path);
			 System.out.println(path1);
		 } catch (FileNotFoundException e) {
			 e.printStackTrace();
		 }
	 }

}
