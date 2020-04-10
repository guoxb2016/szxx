package org.jeecg.modules.check.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.check.entity.NcDump;
import org.jeecg.modules.check.service.INcDumpService;
import org.jeecg.modules.system.entity.SysDepart;
import org.jeecg.modules.system.service.ISysDepartService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


/**
 * @Description: 垃圾站
 * @Author: jeecg-boot
 * @Date:   2020-04-07
 * @Version: V1.0
 */
@RestController
@RequestMapping("/org.jeecg.modules.check/ncDump")
@Slf4j
public class NcDumpController extends JeecgController<NcDump, INcDumpService> {
	@Autowired
	private INcDumpService ncDumpService;
	@Autowired
	private ISysDepartService sysDepartService;
	/**
	 * 分页列表查询
	 *
	 * @param ncDump
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<?> queryPageList(NcDump ncDump,
			@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
			@RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
			HttpServletRequest req) {
		QueryWrapper<NcDump> queryWrapper = QueryGenerator.initQueryWrapper(ncDump, req.getParameterMap());
		Page<NcDump> page = new Page<NcDump>(pageNo, pageSize);
		IPage<NcDump> pageList = ncDumpService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 *   添加
	 *
	 * @param ncDump
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody NcDump ncDump) {
		//设置部门
		SysDepart sysDepart = sysDepartService.getOne(new QueryWrapper<SysDepart>().lambda().eq(SysDepart::getOrgCode,ncDump.getSysOrgCode()));
		if(sysDepart != null) {
			ncDump.setSysOrgName(sysDepart.getDepartName());
		}
		ncDumpService.save(ncDump);
		return Result.ok("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param ncDump
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody NcDump ncDump) {
		//设置部门
		SysDepart sysDepart = sysDepartService.getOne(new QueryWrapper<SysDepart>().lambda().eq(SysDepart::getOrgCode,ncDump.getSysOrgCode()));
		if(sysDepart != null) {
			ncDump.setSysOrgName(sysDepart.getDepartName());
		}
		ncDumpService.updateById(ncDump);
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
		ncDumpService.removeById(id);
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
		this.ncDumpService.removeByIds(Arrays.asList(ids.split(",")));
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
		NcDump ncDump = ncDumpService.getById(id);
		if(ncDump==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(ncDump);
	}

	/**
	 * 导出excel
	 *
	 * @param request
	 * @param ncDump
	 */
	@RequestMapping(value = "/exportXls")
	public ModelAndView exportXls(HttpServletRequest request, NcDump ncDump) {
		return super.exportXls(request, ncDump, NcDump.class, "垃圾站");
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
		return super.importExcel(request, response, NcDump.class);
	}

}
