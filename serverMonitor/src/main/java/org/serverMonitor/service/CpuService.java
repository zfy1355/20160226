package org.serverMonitor.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.lang.management.ManagementFactory;
import java.util.StringTokenizer;

import org.serverMonitor.entity.CpuInfo;
import org.springframework.stereotype.Service;

import com.sun.management.OperatingSystemMXBean;

@Service
public class CpuService {

	public CpuInfo getCpuInfo(){
		CpuInfo cpuInfo = new CpuInfo();
		OperatingSystemMXBean osmb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		cpuInfo.setTotalSpace(osmb.getTotalPhysicalMemorySize() / 1024/1024);
		cpuInfo.setFreeSpace(osmb.getFreePhysicalMemorySize() / 1024/1024);
		return cpuInfo;
        
	}
}
