package org.jeecg.modules.check.controller;

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
import org.jeecg.modules.check.entity.NcRoad;
import org.jeecg.modules.check.service.INcRoadService;
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
 * @Description: 道路
 * @Author: jeecg-boot
 * @Date:   2020-04-08
 * @Version: V1.0
 */
@RestController
@RequestMapping("/check/ncRoad")
@Slf4j
public class NcRoadController extends JeecgController<NcRoad, INcRoadService> {
	@Autowired
	private INcRoadService ncRoadService;
	@Autowired
	private ISysDepartService sysDepartService;
	
	/**
	 * 分页列表查询
	 *
	 * @param ncRoad
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<?> queryPageList(NcRoad ncRoad,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<NcRoad> queryWrapper = QueryGenerator.initQueryWrapper(ncRoad, req.getParameterMap());
		Page<NcRoad> page = new Page<NcRoad>(pageNo, pageSize);
		IPage<NcRoad> pageList = ncRoadService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param ncRoad
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody NcRoad ncRoad) {
		//设置部门
		SysDepart sysDepart = sysDepartService.getOne(new QueryWrapper<SysDepart>().lambda().eq(SysDepart::getOrgCode,ncRoad.getSysOrgCode()));
		if(sysDepart != null) {
			ncRoad.setSysOrgName(sysDepart.getDepartName());
		}
		ncRoadService.save(ncRoad);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param ncRoad
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody NcRoad ncRoad) {
		//设置部门
		SysDepart sysDepart = sysDepartService.getOne(new QueryWrapper<SysDepart>().lambda().eq(SysDepart::getOrgCode,ncRoad.getSysOrgCode()));
		if(sysDepart != null) {
			ncRoad.setSysOrgName(sysDepart.getDepartName());
		}
		ncRoadService.updateById(ncRoad);
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
		ncRoadService.removeById(id);
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
		this.ncRoadService.removeByIds(Arrays.asList(ids.split(",")));
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
		NcRoad ncRoad = ncRoadService.getById(id);
		if(ncRoad==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(ncRoad);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param ncRoad
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, NcRoad ncRoad) {
        return super.exportXls(request, ncRoad, NcRoad.class, "道路");
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
        return super.importExcel(request, response, NcRoad.class);
    }

}
