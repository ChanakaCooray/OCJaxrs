package org.wso2.oc.internal.impl;

import javax.ws.rs.core.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.oc.data.*;
import org.wso2.oc.internal.OCInternal;

import java.util.*;

public class OCInternalService implements OCInternal {
	private static final Log log = LogFactory.getLog(OCInternalService.class);

	public Response registerServer(OCAgentMessage ocAgentMessage) {
		String serverID = registerCluster(ocAgentMessage);
		Map<String, String> response = new HashMap<String, String>();
		response.put("serverId", serverID);
		return Response.status(201).entity(response).build();
	}

	public synchronized Response synchronizeServer(String serverId, OCAgentMessage ocAgentMessage) {
		String[] response = updateCluster(serverId, ocAgentMessage);
		return Response.status(200).entity(response).build();
	}

	private String[] updateCluster(String nodeId, OCAgentMessage ocAgentMessage) {
		Cluster cluster = DataHolder.getClusters().get(ocAgentMessage.getDomain());
		Node node = cluster.getNodes().get(nodeId);
		node.setFreeMemory(ocAgentMessage.getFreeMemory());
		node.setIdleCpuUsage(ocAgentMessage.getIdleCpuUsage());
		node.setSystemCpuUsage(ocAgentMessage.getSystemCpuUsage());
		node.setUserCpuUsage(ocAgentMessage.getUserCpuUsage());
		node.setServerUpTime(ocAgentMessage.getServerUpTime());
		node.setThreadCount(ocAgentMessage.getThreadCount());
		node.setSystemLoadAverage(ocAgentMessage.getSystemLoadAverage());
		node.setTimestamp(ocAgentMessage.getTimestamp());
		cluster.setTenants(ocAgentMessage.getTenants());
		DataHolder.addNode(cluster.getClusterId(), node);
		executeClusterCommand(nodeId, cluster);
		String tempArray[];
		List<String> commandNames = new ArrayList<String>();
		if (node.getCommands() != null) {
			for (Command command : node.getCommands()) {
				commandNames.add(command.getCommandName());
			}
			tempArray = commandNames.toArray(new String[commandNames.size()]);
			node.getCommands().clear();
		} else {

			tempArray = new String[0];
		}
		return tempArray;
	}

	private String registerCluster(OCAgentMessage ocAgentMessage) {

		Cluster tempCluster = DataHolder.getClusters().get(ocAgentMessage.getDomain());

		if (tempCluster == null) {
			Cluster cluster = new Cluster();
			cluster.setClusterId(ocAgentMessage.getDomain());
			cluster.setClusterName(ocAgentMessage.getServerName());
			cluster.setClusterVersion(ocAgentMessage.getServerVersion());
			cluster.setDomain(ocAgentMessage.getDomain());
			cluster.setTenants(ocAgentMessage.getTenants());
			DataHolder.addCluster(cluster);
			tempCluster = cluster;
		}
		String temp[]=ocAgentMessage.getAdminServiceUrl().split("//");
		String tempUrl[]=temp[1].split("/");
		String serverIpPort[]=tempUrl[0].replaceAll("[//]", "").split(":");
		String serverId = serverIpPort[0].replaceAll("[.]", "") + serverIpPort[1].replaceAll("[.]", "");
		ocAgentMessage.setServerId(serverId);
		Node node = new Node();
		node.setNodeId(ocAgentMessage.getServerId());
		node.setIp(ocAgentMessage.getIp());
		node.setSubDomain(ocAgentMessage.getSubDomain());
		node.setAdminServiceUrl(ocAgentMessage.getAdminServiceUrl());
		node.setStartTime(ocAgentMessage.getStartTime());
		node.setOs(ocAgentMessage.getOs());
		node.setTotalMemory(ocAgentMessage.getTotalMemory());
		node.setCpuCount(ocAgentMessage.getCpuCount());
		node.setCpuSpeed(ocAgentMessage.getCpuSpeed());
		node.setTimestamp(ocAgentMessage.getTimestamp());
		node.setPatches(ocAgentMessage.getPatches());
		DataHolder.addNode(tempCluster.getClusterId(), node);
		return serverId;
	}

	private void executeClusterCommand(String nodeId, Cluster cluster) {
		Iterator<Map.Entry<String, Boolean>> iterator;
		if (cluster.getCommands().size() > 0) {
			cluster.updateClusterStatus();
			ClusterCommand clusterCommand = cluster.getCommands().get(0);
			if(clusterCommand.equals(ServerConstants.GRACEFUL_RESTART) || clusterCommand.equals(ServerConstants.FORCE_RESTART)){
				if (clusterCommand.getExecutedNodes().size() == 0) {
					initializeNodeList(cluster, clusterCommand);

				} else if (clusterCommand.getNextNode().getNodeId().equals(nodeId) &&
				           (clusterCommand.isPreviousNodeUp() ||
				            clusterCommand.getPreviousNode() == null)) {

					executeCommandOnNodes(cluster,clusterCommand,nodeId);

				} else if (clusterCommand.getPreviousNode().getNodeId()
				                         .equals(nodeId)) {

					clusterCommand.setPreviousNodeUp(true);
				}
			}else if(clusterCommand.equals(ServerConstants.GRACEFUL_SHUTDOWN) || clusterCommand.equals(ServerConstants.FORCE_SHUTDOWN) ){
				if (clusterCommand.getExecutedNodes().size() == 0) {
					initializeNodeList(cluster, clusterCommand);

				} else if (clusterCommand.getNextNode().getNodeId().equals(nodeId)) {

					executeCommandOnNodes(cluster,clusterCommand,nodeId);

				}
			}
		}
	}
	private void initializeNodeList(Cluster cluster,ClusterCommand clusterCommand){
		Map<String, Node> nodeList = cluster.getNodes();
		for (Node temp : nodeList.values()) {
			if (temp.getStatus().equals(ServerConstants.NODE_RUNNING)) {

				clusterCommand.getExecutedNodes().put(temp.getNodeId(), false);
			}
		}
		if (cluster.getNumberOfActiveNodes() > 0) {
			Iterator<Map.Entry<String, Boolean>> iterator = clusterCommand.getExecutedNodes().entrySet().iterator();
			String nextNodeId = iterator.next().getKey();
			clusterCommand.setNextNode(cluster.getNodes().get(nextNodeId));
			clusterCommand.setPreviousNode(null);

		} else {
			cluster.getCommands().clear();
		}
	}
	private void executeCommandOnNodes(Cluster cluster,ClusterCommand clusterCommand,String nodeId){
		Iterator<Map.Entry<String, Boolean>> iterator;
		Node currentNode = cluster.getNodes().get(nodeId);
		currentNode.getCommands().clear();
		currentNode.addCommand(clusterCommand.getCommandName());
		clusterCommand.getExecutedNodes().put(nodeId, true);
		Map<String, Boolean> temp = new HashMap<String, Boolean>();
		iterator = clusterCommand.getExecutedNodes().entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, Boolean> tempEntry = iterator.next();
			if (tempEntry.getValue() != true) {
				temp.put(tempEntry.getKey(), tempEntry.getValue());
			}
		}
		if (temp.size() > 0) {
		 iterator = temp.entrySet().iterator();
			String nextNodeId = iterator.next().getKey();
			if(clusterCommand.equals(ServerConstants.FORCE_RESTART) || clusterCommand.equals(ServerConstants.GRACEFUL_RESTART)){
				clusterCommand.setPreviousNode(clusterCommand.getNextNode());
				clusterCommand.setPreviousNodeUp(false);
			}
			clusterCommand.setNextNode(cluster.getNodes().get(nextNodeId));

		} else if (temp.size() == 0) {
			clusterCommand.getExecutedNodes().clear();
			cluster.getCommands().remove(clusterCommand);
		}
	}
}