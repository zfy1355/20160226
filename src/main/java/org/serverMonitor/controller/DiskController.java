package org.serverMonitor.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.serverMonitor.entity.CpuInfo;
import org.serverMonitor.entity.DiskInfo;
import org.serverMonitor.service.CpuService;
import org.serverMonitor.service.DiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/disk")
public class DiskController {
	@Autowired
	private DiskService diskService;
	@Autowired
	private CpuService cpuService;
	
	@RequestMapping(value = "/listDisk")
	@ResponseBody
	public ModelAndView listDisk(HttpServletRequest request,HttpServletResponse response,Map<String, Object> model){
		List<DiskInfo> diskinfos = diskService.getDiskInfo();
		CpuInfo cpuInfo = cpuService.getCpuInfo();
		model.put("diskInfos", diskinfos);
		model.put("cpu", cpuInfo);
		return new ModelAndView("monitor/disk");
	}
}
