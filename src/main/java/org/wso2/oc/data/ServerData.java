package org.wso2.oc.data;

public class ServerData {

	private int serverId;
	private String ip;
	private String serverName;
	private String serverVersion;
	private String domain;
	private String subDomain;
	private String adminServiceUrl;
	private String startTime;
	private String os;
	private String totalMemory;
	private String cpuCount;
	private String cpuSpeed;
	private String timestamp;
	private String[] patches;
	private String freeMemory;
	private String idleCpuUsage;
	private String systemCpuUsage;
	private String userCpuUsage;
	private String serverUpTime;
	private String threadCount;
	private String systemLoadAverage;
	private String[] tenants;
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the serverId
	 */
	public int getServerId() {
		return serverId;
	}

	/**
	 * @param serverId the serverId to set
	 */
	public void setServerId(int serverId) {
		this.serverId = serverId;
	}

	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * @return the serverName
	 */
	public String getServerName() {
		return serverName;
	}

	/**
	 * @param serverName the serverName to set
	 */
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	/**
	 * @return the serverVersion
	 */
	public String getServerVersion() {
		return serverVersion;
	}

	/**
	 * @param serverVersion the serverVersion to set
	 */
	public void setServerVersion(String serverVersion) {
		this.serverVersion = serverVersion;
	}

	/**
	 * @return the domain
	 */
	public String getDomain() {
		return domain;
	}

	/**
	 * @param domain the domain to set
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}

	/**
	 * @return the subDomain
	 */
	public String getSubDomain() {
		return subDomain;
	}

	/**
	 * @param subDomain the subDomain to set
	 */
	public void setSubDomain(String subDomain) {
		this.subDomain = subDomain;
	}

	/**
	 * @return the adminServiceUrl
	 */
	public String getAdminServiceUrl() {
		return adminServiceUrl;
	}

	/**
	 * @param adminServiceUrl the adminServiceUrl to set
	 */
	public void setAdminServiceUrl(String adminServiceUrl) {
		this.adminServiceUrl = adminServiceUrl;
	}

	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the os
	 */
	public String getOs() {
		return os;
	}

	/**
	 * @param os the os to set
	 */
	public void setOs(String os) {
		this.os = os;
	}

	/**
	 * @return the totalMemory
	 */
	public String getTotalMemory() {
		return totalMemory;
	}

	/**
	 * @param totalMemory the totalMemory to set
	 */
	public void setTotalMemory(String totalMemory) {
		this.totalMemory = totalMemory;
	}

	/**
	 * @return the cpuCount
	 */
	public String getCpuCount() {
		return cpuCount;
	}

	/**
	 * @param cpuCount the cpuCount to set
	 */
	public void setCpuCount(String cpuCount) {
		this.cpuCount = cpuCount;
	}

	/**
	 * @return the cpuSpeed
	 */
	public String getCpuSpeed() {
		return cpuSpeed;
	}

	/**
	 * @param cpuSpeed the cpuSpeed to set
	 */
	public void setCpuSpeed(String cpuSpeed) {
		this.cpuSpeed = cpuSpeed;
	}

	/**
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the patches
	 */
	public String[] getPatches() {
		return patches;
	}

	/**
	 * @param patches the patches to set
	 */
	public void setPatches(String[] patches) {
		this.patches = patches;
	}

	/**
	 * @return the freeMemory
	 */
	public String getFreeMemory() {
		return freeMemory;
	}

	/**
	 * @param freeMemory the freeMemory to set
	 */
	public void setFreeMemory(String freeMemory) {
		this.freeMemory = freeMemory;
	}

	/**
	 * @return the idleCpuUsage
	 */
	public String getIdleCpuUsage() {
		return idleCpuUsage;
	}

	/**
	 * @param idleCpuUsage the idleCpuUsage to set
	 */
	public void setIdleCpuUsage(String idleCpuUsage) {
		this.idleCpuUsage = idleCpuUsage;
	}

	/**
	 * @return the systemCpuUsage
	 */
	public String getSystemCpuUsage() {
		return systemCpuUsage;
	}

	/**
	 * @param systemCpuUsage the systemCpuUsage to set
	 */
	public void setSystemCpuUsage(String systemCpuUsage) {
		this.systemCpuUsage = systemCpuUsage;
	}

	/**
	 * @return the userCpuUsage
	 */
	public String getUserCpuUsage() {
		return userCpuUsage;
	}

	/**
	 * @param userCpuUsage the userCpuUsage to set
	 */
	public void setUserCpuUsage(String userCpuUsage) {
		this.userCpuUsage = userCpuUsage;
	}

	/**
	 * @return the serverUpTime
	 */
	public String getServerUpTime() {
		return serverUpTime;
	}

	/**
	 * @param serverUpTime the serverUpTime to set
	 */
	public void setServerUpTime(String serverUpTime) {
		this.serverUpTime = serverUpTime;
	}

	/**
	 * @return the threadCount
	 */
	public String getThreadCount() {
		return threadCount;
	}

	/**
	 * @param threadCount the threadCount to set
	 */
	public void setThreadCount(String threadCount) {
		this.threadCount = threadCount;
	}

	/**
	 * @return the systemLoadAverage
	 */
	public String getSystemLoadAverage() {
		return systemLoadAverage;
	}

	/**
	 * @param systemLoadAverage the systemLoadAverage to set
	 */
	public void setSystemLoadAverage(String systemLoadAverage) {
		this.systemLoadAverage = systemLoadAverage;
	}

	/**
	 * @return the tenants
	 */
	public String[] getTenants() {
		return tenants;
	}

	/**
	 * @param tenants the tenants to set
	 */
	public void setTenants(String[] tenants) {
		this.tenants = tenants;
	}

//	public String printData(String[] data) {
//		String details = "";
//		for (String temp : data) {
//			details += "--" + temp;
//		}
//		return details;
//	}
//
//	public String print() {
//		;
//		String data =
//				this.ip + " : " + this.serverName + " :" + this.serverVersion + ":" + this.domain +
//				":" + this.subDomain + ":" + this.adminServiceUrl + ":" + this.startTime + ":" +
//				this.os + ":" + this.totalMemory + ":" + this.cpuCount + ":" + this.cpuSpeed + ":" +
//				this.timestamp + ":" + this.idleCpuUsage + ":" + this.systemCpuUsage + ":" +
//				this.userCpuUsage + ":" + this.serverUpTime + ":" + this.threadCount + ":" +
//				this.systemLoadAverage + ":" + this.printData(this.patches);
//		return data;
//	}

}
