package org.serverMonitor.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.serverMonitor.entity.DiskInfo;
import org.springframework.stereotype.Service;


@Service
public class DiskService {
	public static final int MBPARAM = 1048576;
	public List<DiskInfo> getDiskInfo(){
		List<DiskInfo> diskInfos = new ArrayList<DiskInfo>();
		File root  = new File("/");
		File[] files = root.listRoots();
		for(File file:files){
			DiskInfo info = new DiskInfo();
			info.setDiskName(file.getPath());
			info.setFreeSpace(file.getFreeSpace()/MBPARAM);
			info.setTotalSpace(file.getTotalSpace()/MBPARAM);
			info.setUsedSpace((file.getTotalSpace()-file.getFreeSpace())/MBPARAM);
			diskInfos.add(info);
		}
		return diskInfos;
	}
}
