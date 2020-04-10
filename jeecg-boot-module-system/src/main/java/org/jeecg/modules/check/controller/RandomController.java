package org.jeecg.modules.check.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.check.entity.NcCommunity;
import org.jeecg.modules.check.entity.NcDump;
import org.jeecg.modules.check.entity.NcRoad;
import org.jeecg.modules.check.entity.NcToilet;
import org.jeecg.modules.check.service.INcCommunityService;
import org.jeecg.modules.check.service.INcDumpService;
import org.jeecg.modules.check.service.INcRoadService;
import org.jeecg.modules.check.service.INcToiletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import lombok.extern.slf4j.Slf4j;
/**
 * 随机抽查
 * @author guoxb
 *
 */
@RestController
@RequestMapping("/check/random")
@Slf4j
public class RandomController {

	@Autowired
	private INcToiletService ncToiletService;

	@Autowired
	private INcDumpService ncDumpService;

	@Autowired
	private INcRoadService ncRoadService;

	@Autowired
	private INcCommunityService ncCommunityService;

	/**
	 * 根据区县、设施类别、道路类别和数量随机抽取
	 * @param sysOrgName
	 * @param type
	 * @param roadType
	 * @param count
	 * @return
	 */
	@PostMapping(value = "/random")
	public Result<?> random(@RequestParam(name="sysOrgName",required=true) String sysOrgName,@RequestParam(name="type",required=true) String type,
			@RequestParam(name="roadType",required=false) String roadType,@RequestParam(name="count",required=true) int count){
		Random random = new Random();
		Map<String,Object> map = new HashMap<>();
		//用逗号分隔
		String[] types = type.split(",");
		for(String tp:types) {
			if(tp.equals("主干道")) {
				List<Integer> randoms = new ArrayList<>();
				//查询该区道路总条数
				LambdaQueryWrapper<NcRoad> wrapper = new QueryWrapper<NcRoad>().lambda().eq(NcRoad::getSysOrgName,sysOrgName).eq(NcRoad::getType, "主干道");
				int num = ncRoadService.count(wrapper);
				//如果输入的值>库中的值，则全显示
				if(num < count) {
					count = num;
				}
				List<NcRoad> ncRoads = ncRoadService.list(wrapper);
				List<NcRoad> list = new ArrayList<>();
				boolean[] bool = new boolean[num];
				int r = 0;
				for(int i=0;i<count;i++) {
					do {
						// 如果产生的数相同继续循环度
						r = random.nextInt(num);
					} while(bool[r]);
					bool[r] = true;
					randoms.add(r);
					log.info("您抽取了第"+r+"条道路");
				}
				for(int j=0;j<num;j++) {
					if(randoms.contains(j)) {
						list.add(ncRoads.get(j));
					}
				}
				map.put(tp, list);
			}
			if(tp.equals("次干道")) {
				List<Integer> randoms = new ArrayList<>();
				//查询该区道路总条数
				LambdaQueryWrapper<NcRoad> wrapper = new QueryWrapper<NcRoad>().lambda().eq(NcRoad::getSysOrgName,sysOrgName).eq(NcRoad::getType, "次干道");
				int num = ncRoadService.count(wrapper);
				//如果输入的值>库中的值，则全显示
				if(num < count) {
					count = num;
				}
				List<NcRoad> ncRoads = ncRoadService.list(wrapper);
				List<NcRoad> list = new ArrayList<>();
				boolean[] bool = new boolean[num];
				int r = 0;
				for(int i=0;i<count;i++) {
					do {
						// 如果产生的数相同继续循环度
						r = random.nextInt(num);
					} while(bool[r]);
					bool[r] = true;
					randoms.add(r);
					log.info("您抽取了第"+r+"条道路");
				}
				for(int j=0;j<num;j++) {
					if(randoms.contains(j)) {
						list.add(ncRoads.get(j));
					}
				}
				map.put(tp, list);
			}
			if(tp.equals("背街小巷")) {
				List<Integer> randoms = new ArrayList<>();
				//查询该区道路总条数
				LambdaQueryWrapper<NcRoad> wrapper = new QueryWrapper<NcRoad>().lambda().eq(NcRoad::getSysOrgName,sysOrgName).eq(NcRoad::getType, "背街小巷");
				int num = ncRoadService.count(wrapper);
				//如果输入的值>库中的值，则全显示
				if(num < count) {
					count = num;
				}
				List<NcRoad> ncRoads = ncRoadService.list(wrapper);
				List<NcRoad> list = new ArrayList<>();
				boolean[] bool = new boolean[num];
				int r = 0;
				for(int i=0;i<count;i++) {
					do {
						// 如果产生的数相同继续循环度
						r = random.nextInt(num);
					} while(bool[r]);
					bool[r] = true;
					randoms.add(r);
					log.info("您抽取了第"+r+"条道路");
				}
				for(int j=0;j<num;j++) {
					if(randoms.contains(j)) {
						list.add(ncRoads.get(j));
					}
				}
				map.put(tp, list);
			}
			if("开放社区".equals(tp)) {
				List<Integer> randoms = new ArrayList<>();
				//查询该开放社区
				LambdaQueryWrapper<NcCommunity> wrapper = new QueryWrapper<NcCommunity>().lambda().eq(NcCommunity::getSysOrgName,sysOrgName);
				int num = ncCommunityService.count(wrapper);
				//如果输入的值>库中的值，则全显示
				if(num < count) {
					count = num;
				}
				List<NcCommunity> ncCommunitys = ncCommunityService.list(wrapper);
				List<NcCommunity> list = new ArrayList<>();
				boolean[] bool = new boolean[num];
				int r = 0;
				for(int i=0;i<count;i++) {
					do {
						// 如果产生的数相同继续循环度
						r = random.nextInt(num);
					} while(bool[r]);
					bool[r] = true;
					randoms.add(r);
					log.info("您抽取了第"+r+"个开放社区");
				}
				for(int j=0;j<num;j++) {
					if(randoms.contains(j)) {
						list.add(ncCommunitys.get(j));
					}
				}
				map.put(tp, list);
			}
			if("公厕".equals(tp)) {
				List<Integer> randoms = new ArrayList<>();
				//查询该区公厕总条数
				LambdaQueryWrapper<NcToilet> wrapper = new QueryWrapper<NcToilet>().lambda().eq(NcToilet::getSysOrgName,sysOrgName);
				int num = ncToiletService.count(wrapper);
				//如果输入的值>库中的值，则全显示
				if(num < count) {
					count = num;
				}
				List<NcToilet> ncToilets = ncToiletService.list(wrapper);
				List<NcToilet> list = new ArrayList<>();
				boolean[] bool = new boolean[num];
				int r = 0;
				for(int i=0;i<count;i++) {
					do {
						// 如果产生的数相同继续循环度
						r = random.nextInt(num);
					} while(bool[r]);
					bool[r] = true;
					randoms.add(r);
					log.info("您抽取了第"+r+"个公厕");
				}
				for(int j=0;j<num;j++) {
					if(randoms.contains(j)) {
						list.add(ncToilets.get(j));
					}
				}
				map.put(tp, list);
			}
			if("垃圾站".equals(tp)) {
				List<Integer> randoms = new ArrayList<>();
				//查询该区垃圾站总条数
				LambdaQueryWrapper<NcDump> wrapper = new QueryWrapper<NcDump>().lambda().eq(NcDump::getSysOrgName,sysOrgName);
				int num = ncDumpService.count(wrapper);
				//如果输入的值>库中的值，则全显示
				if(num < count) {
					count = num;
				}
				List<NcDump> ncDumps = ncDumpService.list(wrapper);
				List<NcDump> list = new ArrayList<>();
				boolean[] bool = new boolean[num];
				int r = 0;
				for(int i=0;i<count;i++) {
					do {
						// 如果产生的数相同继续循环度
						r = random.nextInt(num);
					} while(bool[r]);
					bool[r] = true;
					randoms.add(r);
					log.info("您抽取了第"+r+"个垃圾站");
				}
				for(int j=0;j<num;j++) {
					if(randoms.contains(j)) {
						list.add(ncDumps.get(j));
					}
				}
				map.put(tp, list);
			}
		}
		return Result.ok(map);
	}

}
