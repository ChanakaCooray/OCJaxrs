package org.wso2.oc.external.impl;

import org.wso2.oc.data.*;
import org.wso2.oc.external.OCExternal;

import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class OCExternalService implements OCExternal {

	public Map<String, Cluster> getAllClustersData() {

		Map<String,Cluster> clusters = DataHolder.getClusters();

		for(Cluster cluster:clusters.values()){
			updateClusterStatus(cluster);
		}

		return clusters;
	}

	public Cluster getClusterData(String clusterId) {

		Cluster cluster = DataHolder.getClusters().get(clusterId);

		updateClusterStatus(cluster);

		return cluster;
	}

	public Map<String, Node> getAllClusterNodesData(String clusterId) {

		Map<String, Node> nodes = DataHolder.getClusters().get(clusterId).getNodes();

		for(Node node:nodes.values()){
			updateNodeStatus(node);
		}

		return nodes;
	}

	public Node getClusterNodeData(String clusterId, String nodeId) {

		Node node = DataHolder.getClusters().get(clusterId).getNodes().get(nodeId);

		updateNodeStatus(node);

		return node;
	}

	public Response executeClusterCommand(String clusterId, String commandId) {
		DataHolder.getClusters().get(clusterId).addCommand(commandId);
		return Response.ok().build();
	}

	public Response executeNodeCommand(String clusterId, String nodeId, String commandId) {
		DataHolder.getClusters().get(clusterId).getNodes().get(nodeId).addCommand(commandId);
		return Response.ok().build();
	}

	public void updateClusterStatus(Cluster cluster){

		Node[] nodes = cluster.getNodes().values().toArray(new Node[cluster.getNodes().size()]);

		boolean allNodesDown = false;

		for(Node node:nodes){
			updateNodeStatus(node);

			if(node.getStatus().equals(ServerConstants.NODE_DOWN))
				allNodesDown = true;
		}

		if(allNodesDown){
			cluster.setStatus(ServerConstants.CLUSTER_DOWN);
		}
		else
			cluster.setStatus(ServerConstants.CLUSTER_RUNNING);
	}

	public void updateNodeStatus(Node node){
		Date currentTime = new Date();

		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");

		Date lastServerUpTime = null;

		try {
			lastServerUpTime = dateFormat.parse(node.getTimestamp());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		long diff = currentTime.getTime() - lastServerUpTime.getTime();

		if(diff>ServerConstants.NODE_DOWN_TIME_INTERVAL){
			node.setStatus(ServerConstants.NODE_DOWN);
		}else if(diff>ServerConstants.NODE_NOT_REPORTING_TIME_INTERVAL){
			node.setStatus(ServerConstants.NODE_NOT_REPORTING);
		}else{
			node.setStatus(ServerConstants.NODE_RUNNING);
		}
	}
}