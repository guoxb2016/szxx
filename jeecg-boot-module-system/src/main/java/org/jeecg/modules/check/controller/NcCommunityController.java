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
import org.jeecg.modules.check.entity.NcCommunity;
import org.jeecg.modules.check.service.INcCommunityService;
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
 * @Description: 开放社区
 * @Author: jeecg-boot
 * @Date:   2020-04-08
 * @Version: V1.0
 */
@RestController
@RequestMapping("/check/ncCommunity")
@Slf4j
public class NcCommunityController extends JeecgController<NcCommunity, INcCommunityService> {
	@Autowired
	private INcCommunityService ncCommunityService;
	@Autowired
	private ISysDepartService sysDepartService;
	/**
	 * 分页列表查询
	 *
	 * @param ncCommunity
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<?> queryPageList(NcCommunity ncCommunity,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<NcCommunity> queryWrapper = QueryGenerator.initQueryWrapper(ncCommunity, req.getParameterMap());
		Page<NcCommunity> page = new Page<NcCommunity>(pageNo, pageSize);
		IPage<NcCommunity> pageList = ncCommunityService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param ncCommunity
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody NcCommunity ncCommunity) {
		//设置部门
		SysDepart sysDepart = sysDepartService.getOne(new QueryWrapper<SysDepart>().lambda().eq(SysDepart::getOrgCode,ncCommunity.getSysOrgCode()));
		if(sysDepart != null) {
			ncCommunity.setSysOrgName(sysDepart.getDepartName());
		}
		ncCommunityService.save(ncCommunity);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param ncCommunity
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody NcCommunity ncCommunity) {
		//设置部门
		SysDepart sysDepart = sysDepartService.getOne(new QueryWrapper<SysDepart>().lambda().eq(SysDepart::getOrgCode,ncCommunity.getSysOrgCode()));
		if(sysDepart != null) {
			ncCommunity.setSysOrgName(sysDepart.getDepartName());
		}
		ncCommunityService.updateById(ncCommunity);
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
		ncCommunityService.removeById(id);
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
		this.ncCommunityService.removeByIds(Arrays.asList(ids.split(",")));
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
		NcCommunity ncCommunity = ncCommunityService.getById(id);
		if(ncCommunity==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(ncCommunity);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param ncCommunity
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, NcCommunity ncCommunity) {
        return super.exportXls(request, ncCommunity, NcCommunity.class, "开放社区");
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
        return super.importExcel(request, response, NcCommunity.class);
    }

}
