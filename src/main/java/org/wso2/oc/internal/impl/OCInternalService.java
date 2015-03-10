package org.wso2.oc.internal.impl;

import javax.ws.rs.core.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.oc.data.*;
import org.wso2.oc.external.impl.OCExternalService;
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
		executeCommandsOnNodes(nodeId,cluster);
		String tempArray[];
		List<String> commandNames=new ArrayList<String>();
		if (node.getCommands() != null) {
			for(Command command:node.getCommands()){
				commandNames.add(command.getCommandName());
			}
			tempArray =commandNames.toArray(new String[commandNames.size()]);
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
	private void executeCommandsOnNodes(String nodeId,Cluster cluster){
		Iterator<Map.Entry<String,Boolean>> iterator;
		if(cluster.getCommands().size()>0){
			Command currentCommand=cluster.getCommands().get(0);
			OCExternalService externalService=new OCExternalService();
			externalService.updateClusterStatus(cluster);
            Node currentNode=cluster.getNodes().get(nodeId);
			ClusterCommand clusterCommand= cluster.getCommands().get(0);
			if(clusterCommand.getExecutedNodes().size()==0){
				Map<String,Node> nodeList=cluster.getNodes();
				for(Node temp:nodeList.values()){
					if(temp.getStatus().equals(ServerConstants.NODE_RUNNING)){
						clusterCommand.getExecutedNodes().put(temp.getNodeId(), false);
					}
				}
				iterator=clusterCommand.getExecutedNodes().entrySet().iterator();
				String nextNodeId=iterator.next().getKey();
				clusterCommand.setNextNode(cluster.getNodes().get(nextNodeId));
				clusterCommand.setPreviousNode(null);

			}else if(clusterCommand.getNextNode().equals(currentNode) && (clusterCommand.isPreviousNodeUp() ||clusterCommand.getPreviousNode()==null)){
				currentNode.getCommands().clear();
				currentNode.addCommand(currentCommand.getCommandName());
				clusterCommand.getExecutedNodes().put(nodeId, true);
				Map<String,Boolean> temp=new HashMap<String, Boolean>();
				iterator = clusterCommand.getExecutedNodes().entrySet().iterator();
				while (iterator.hasNext()) {
					 Map.Entry<String,Boolean> tempEntry= iterator.next();
					if(tempEntry.getValue()!=true){
						temp.put(tempEntry.getKey(), tempEntry.getValue());
					}
				}
				if(temp.size()>0){
					iterator=temp.entrySet().iterator();
					String nextNodeId=iterator.next().getKey();
					clusterCommand.setPreviousNode(clusterCommand.getNextNode());
					clusterCommand.setPreviousNodeUp(false);
					clusterCommand.setNextNode(cluster.getNodes().get(nextNodeId));
				}else if(temp.size()==0){
					clusterCommand.setExecutedNodes(null);
				}

			}else if(clusterCommand.getPreviousNode().equals(currentNode)){
				clusterCommand.setPreviousNodeUp(true);
			}


		}
	}

}