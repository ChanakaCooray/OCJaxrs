package org.wso2.oc.internal.impl;

import javax.ws.rs.core.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.oc.data.Cluster;
import org.wso2.oc.data.DataHolder;
import org.wso2.oc.data.Node;
import org.wso2.oc.data.OCAgentMessage;
import org.wso2.oc.internal.OCInternal;

import java.util.HashMap;
import java.util.Map;

public class OCInternalService implements OCInternal {
	private static final Log log = LogFactory.getLog(OCInternalService.class);

	public Response registerServer(OCAgentMessage ocAgentMessage) {
		String serverID = registerCluster(ocAgentMessage);
		Map<String, String> response = new HashMap<String, String>();
		response.put("serverId", serverID);
		return Response.status(201).entity(response).build();
	}

	public Response synchronizeServer(String serverId, OCAgentMessage ocAgentMessage) {
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
		String tempArray[];
		if (node.getCommands() != null) {
			tempArray = node.getCommands().toArray(new String[node.getCommands().size()]);
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
		String serverIp = ocAgentMessage.getAdminServiceUrl().substring(8, 20);
		String serverPort = ocAgentMessage.getAdminServiceUrl().substring(21, 25);
		String serverId = serverIp.replaceAll("[.]", "") + serverPort;
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
}