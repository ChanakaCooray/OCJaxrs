package org.wso2.oc.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Cluster {
    private Map<String,Node> nodes;
    private String serverName;
    private String serverVersion;
    private String domain;
    private String[] tenants;
    private String status;
    private ArrayList<Command> commands;


    public int getNumberOfNodes(){
        int numberOfNodes=0;
        return numberOfNodes;
    }
    public int getNumberOfActiveNodes(){
        int numberOfActiveNodes=0;
        return numberOfActiveNodes;
    }
    public Map<String, Node> getNodes() {
        return nodes;
    }

    public void setNodes(Map<String, Node> nodes) {
        this.nodes = nodes;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getServerVersion() {
        return serverVersion;
    }

    public void setServerVersion(String serverVersion) {
        this.serverVersion = serverVersion;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String[] getTenants() {
        return tenants;
    }

    public void setTenants(String[] tenants) {
        this.tenants = tenants;
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
