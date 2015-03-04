package org.wso2.oc.data;

import java.util.ArrayList;

public class Node {

    private String NodeId;
    private String ip;
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
    private String status;
    private ArrayList<Command> commands;

    public String getNodeId() {
        return NodeId;
    }

    public void setNodeId(String nodeId) {
        NodeId = nodeId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getSubDomain() {
        return subDomain;
    }

    public void setSubDomain(String subDomain) {
        this.subDomain = subDomain;
    }

    public String getAdminServiceUrl() {
        return adminServiceUrl;
    }

    public void setAdminServiceUrl(String adminServiceUrl) {
        this.adminServiceUrl = adminServiceUrl;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getTotalMemory() {
        return totalMemory;
    }

    public void setTotalMemory(String totalMemory) {
        this.totalMemory = totalMemory;
    }

    public String getCpuCount() {
        return cpuCount;
    }

    public void setCpuCount(String cpuCount) {
        this.cpuCount = cpuCount;
    }

    public String getCpuSpeed() {
        return cpuSpeed;
    }

    public void setCpuSpeed(String cpuSpeed) {
        this.cpuSpeed = cpuSpeed;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String[] getPatches() {
        return patches;
    }

    public void setPatches(String[] patches) {
        this.patches = patches;
    }

    public String getFreeMemory() {
        return freeMemory;
    }

    public void setFreeMemory(String freeMemory) {
        this.freeMemory = freeMemory;
    }

    public String getIdleCpuUsage() {
        return idleCpuUsage;
    }

    public void setIdleCpuUsage(String idleCpuUsage) {
        this.idleCpuUsage = idleCpuUsage;
    }

    public String getSystemCpuUsage() {
        return systemCpuUsage;
    }

    public void setSystemCpuUsage(String systemCpuUsage) {
        this.systemCpuUsage = systemCpuUsage;
    }

    public String getUserCpuUsage() {
        return userCpuUsage;
    }

    public void setUserCpuUsage(String userCpuUsage) {
        this.userCpuUsage = userCpuUsage;
    }

    public String getServerUpTime() {
        return serverUpTime;
    }

    public void setServerUpTime(String serverUpTime) {
        this.serverUpTime = serverUpTime;
    }

    public String getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(String threadCount) {
        this.threadCount = threadCount;
    }

    public String getSystemLoadAverage() {
        return systemLoadAverage;
    }

    public void setSystemLoadAverage(String systemLoadAverage) {
        this.systemLoadAverage = systemLoadAverage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Command> getCommands() {
        return commands;
    }

    public void setCommands(ArrayList<Command> commands) {
        this.commands = commands;
    }
}
