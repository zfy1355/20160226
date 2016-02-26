package org.serverMonitor.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CpuInfo {
	@Id
	private String id;
	@Column
	private long totalSpace;
	@Column
	private long freeSpace;
	@Column
	private double cpuRatio;
	@Column
	private long cpuRatioForWindows;
	
	public double getCpuRatio() {
		return cpuRatio;
	}
	public void setCpuRatio(double cpuRatio) {
		this.cpuRatio = cpuRatio;
	}
	public long getTotalSpace() {
		return totalSpace;
	}
	public void setTotalSpace(long totalSpace) {
		this.totalSpace = totalSpace;
	}
	public long getFreeSpace() {
		return freeSpace;
	}
	public void setFreeSpace(long freeSpace) {
		this.freeSpace = freeSpace;
	}
	public long getCpuRatioForWindows() {
		return cpuRatioForWindows;
	}
	public void setCpuRatioForWindows(long cpuRatioForWindows) {
		cpuRatioForWindows = cpuRatioForWindows;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	
}
