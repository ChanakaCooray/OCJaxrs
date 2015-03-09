package org.wso2.oc.data;

import java.util.ArrayList;
import java.util.List;

public class Node {

    private String nodeId;
    private String ip;
    private String subDomain;
    private String adminServiceUrl;
    private String startTime;
    private String os;
    private double totalMemory;
    private int cpuCount;
    private double cpuSpeed;
    private String timestamp;
    private List<String> patches;
    private double freeMemory;
    private double idleCpuUsage;
    private double systemCpuUsage;
    private double userCpuUsage;
    private String serverUpTime;
    private int threadCount;
    private double systemLoadAverage;
    private String status;
    private List<Command> commands;
    public Node(){
        commands=new ArrayList<Command>();
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
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

    public double getTotalMemory() {
        return totalMemory;
    }

    public void setTotalMemory(double totalMemory) {
        this.totalMemory = totalMemory;
    }

    public int getCpuCount() {
        return cpuCount;
    }

    public void setCpuCount(int cpuCount) {
        this.cpuCount = cpuCount;
    }

    public double getCpuSpeed() {
        return cpuSpeed;
    }

    public void setCpuSpeed(double cpuSpeed) {
        this.cpuSpeed = cpuSpeed;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public List<String> getPatches() {
        return patches;
    }

    public void setPatches(List<String> patches) {
        this.patches = patches;
    }

    public double getFreeMemory() {
        return freeMemory;
    }

    public void setFreeMemory(double freeMemory) {
        this.freeMemory = freeMemory;
    }

    public double getIdleCpuUsage() {
        return idleCpuUsage;
    }

    public void setIdleCpuUsage(double idleCpuUsage) {
        this.idleCpuUsage = idleCpuUsage;
    }

    public double getSystemCpuUsage() {
        return systemCpuUsage;
    }

    public void setSystemCpuUsage(double systemCpuUsage) {
        this.systemCpuUsage = systemCpuUsage;
    }

    public double getUserCpuUsage() {
        return userCpuUsage;
    }

    public void setUserCpuUsage(double userCpuUsage) {
        this.userCpuUsage = userCpuUsage;
    }

    public String getServerUpTime() {
        return serverUpTime;
    }

    public void setServerUpTime(String serverUpTime) {
        this.serverUpTime = serverUpTime;
    }

    public int getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(int threadCount) {
        this.threadCount = threadCount;
    }

    public double getSystemLoadAverage() {
        return systemLoadAverage;
    }

    public void setSystemLoadAverage(double systemLoadAverage) {
        this.systemLoadAverage = systemLoadAverage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Command> getCommands() {
        return commands;
    }

    public void setCommands(List<Command> commands) {
        this.commands = commands;
    }
}
