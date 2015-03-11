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
			cluster.updateClusterStatus();
		}

		return clusters;
	}

	public Cluster getClusterData(String clusterId) {

		Cluster cluster = DataHolder.getClusters().get(clusterId);
		cluster.updateClusterStatus();


		return cluster;
	}

	public Map<String, Node> getAllClusterNodesData(String clusterId) {

		Map<String, Node> nodes = DataHolder.getClusters().get(clusterId).getNodes();

		for(Node node:nodes.values()){
			node.updateNodeStatus();
		}

		return nodes;
	}

	public Node getClusterNodeData(String clusterId, String nodeId) {

		Node node = DataHolder.getClusters().get(clusterId).getNodes().get(nodeId);

		node.updateNodeStatus();

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

}