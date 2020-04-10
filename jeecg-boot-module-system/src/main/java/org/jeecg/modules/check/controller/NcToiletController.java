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
import org.jeecg.modules.check.entity.NcToilet;
import org.jeecg.modules.check.service.INcToiletService;
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
 * @Description: 公厕
 * @Author: jeecg-boot
 * @Date:   2020-04-08
 * @Version: V1.0
 */
@RestController
@RequestMapping("/check/ncToilet")
@Slf4j
public class NcToiletController extends JeecgController<NcToilet, INcToiletService> {
	@Autowired
	private INcToiletService ncToiletService;
	@Autowired
	private ISysDepartService sysDepartService;
	/**
	 * 分页列表查询
	 *
	 * @param ncToilet
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<?> queryPageList(NcToilet ncToilet,
			@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
			@RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
			HttpServletRequest req) {
		QueryWrapper<NcToilet> queryWrapper = QueryGenerator.initQueryWrapper(ncToilet, req.getParameterMap());
		Page<NcToilet> page = new Page<NcToilet>(pageNo, pageSize);
		IPage<NcToilet> pageList = ncToiletService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 *   添加
	 *
	 * @param ncToilet
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody NcToilet ncToilet) {
		//设置部门
		SysDepart sysDepart = sysDepartService.getOne(new QueryWrapper<SysDepart>().lambda().eq(SysDepart::getOrgCode,ncToilet.getSysOrgCode()));
		if(sysDepart != null) {
			ncToilet.setSysOrgName(sysDepart.getDepartName());
		}
		ncToiletService.save(ncToilet);
		return Result.ok("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param ncToilet
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody NcToilet ncToilet) {
		//设置部门
		SysDepart sysDepart = sysDepartService.getOne(new QueryWrapper<SysDepart>().lambda().eq(SysDepart::getOrgCode,ncToilet.getSysOrgCode()));
		if(sysDepart != null) {
			ncToilet.setSysOrgName(sysDepart.getDepartName());
		}
		ncToiletService.updateById(ncToilet);
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
		ncToiletService.removeById(id);
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
		this.ncToiletService.removeByIds(Arrays.asList(ids.split(",")));
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
		NcToilet ncToilet = ncToiletService.getById(id);
		if(ncToilet==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(ncToilet);
	}

	/**
	 * 导出excel
	 *
	 * @param request
	 * @param ncToilet
	 */
	@RequestMapping(value = "/exportXls")
	public ModelAndView exportXls(HttpServletRequest request, NcToilet ncToilet) {
		return super.exportXls(request, ncToilet, NcToilet.class, "公厕");
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
		return super.importExcel(request, response, NcToilet.class);
	}

}
