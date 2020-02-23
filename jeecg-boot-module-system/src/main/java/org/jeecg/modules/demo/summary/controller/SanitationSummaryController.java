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
