package org.jeecg.modules.demo.summary.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.summary.entity.SanitationEpSummary;
import org.jeecg.modules.demo.summary.service.ISanitationEpSummaryService;

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
 * @Description: 环卫防疫汇总表
 * @Author: jeecg-boot
 * @Date:   2020-02-21
 * @Version: V1.0
 */
@RestController
@RequestMapping("/summary/sanitationEpSummary")
@Slf4j
public class SanitationEpSummaryController extends JeecgController<SanitationEpSummary, ISanitationEpSummaryService> {
	@Autowired
	private ISanitationEpSummaryService sanitationEpSummaryService;
	
	/**
	 * 分页列表查询
	 *
	 * @param sanitationEpSummary
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SanitationEpSummary sanitationEpSummary,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SanitationEpSummary> queryWrapper = QueryGenerator.initQueryWrapper(sanitationEpSummary, req.getParameterMap());
		Page<SanitationEpSummary> page = new Page<SanitationEpSummary>(pageNo, pageSize);
		IPage<SanitationEpSummary> pageList = sanitationEpSummaryService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param sanitationEpSummary
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SanitationEpSummary sanitationEpSummary) {
		sanitationEpSummaryService.save(sanitationEpSummary);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param sanitationEpSummary
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SanitationEpSummary sanitationEpSummary) {
		sanitationEpSummaryService.updateById(sanitationEpSummary);
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
		sanitationEpSummaryService.removeById(id);
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
		this.sanitationEpSummaryService.removeByIds(Arrays.asList(ids.split(",")));
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
		SanitationEpSummary sanitationEpSummary = sanitationEpSummaryService.getById(id);
		if(sanitationEpSummary==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(sanitationEpSummary);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param sanitationEpSummary
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, SanitationEpSummary sanitationEpSummary) {
        return super.exportXls(request, sanitationEpSummary, SanitationEpSummary.class, "环卫防疫汇总表");
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
        return super.importExcel(request, response, SanitationEpSummary.class);
    }

}
