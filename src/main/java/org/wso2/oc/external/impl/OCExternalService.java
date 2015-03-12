package org.wso2.oc.external.impl;

import org.wso2.oc.data.*;
import org.wso2.oc.external.OCExternal;

import javax.ws.rs.WebApplicationException;
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

		Map<String,Cluster> clusters = DataHolder.getClusters();

		if(!clusters.containsKey(clusterId)){
			throw new WebApplicationException(new Throwable("Cluster is not found"),
			                                  Response.Status.BAD_REQUEST);
		}

		Cluster cluster = clusters.get(clusterId);

		cluster.updateClusterStatus();

		return cluster;
	}

	public Map<String, Node> getAllClusterNodesData(String clusterId) {

		Map<String,Cluster> clusters = DataHolder.getClusters();

		if(!clusters.containsKey(clusterId)){
			throw new WebApplicationException(new Throwable("Cluster is not found"),
			                                  Response.Status.BAD_REQUEST);
		}

		Map<String, Node> nodes = clusters.get(clusterId).getNodes();

		for(Node node:nodes.values()){
			node.updateNodeStatus();
		}

		return nodes;
	}

	public Node getClusterNodeData(String clusterId, String nodeId) {

		Map<String,Cluster> clusters = DataHolder.getClusters();

		if(!clusters.containsKey(clusterId)){
			throw new WebApplicationException(new Throwable("Cluster is not found"),
			                                  Response.Status.BAD_REQUEST);
		}

		Map<String, Node> nodes = clusters.get(clusterId).getNodes();

		if(!nodes.containsKey(nodeId)){
			throw new WebApplicationException(new Throwable("Node is not found"),
			                                  Response.Status.BAD_REQUEST);
		}

		Node node = nodes.get(nodeId);

		node.updateNodeStatus();

		return node;
	}

	public Response executeClusterCommand(String clusterId, String commandId) {

		Map<String,Cluster> clusters = DataHolder.getClusters();

		if(!clusters.containsKey(clusterId)){
			throw new WebApplicationException(new Throwable("Cluster is not found"),
			                                  Response.Status.BAD_REQUEST);
		}

		clusters.get(clusterId).addCommand(commandId);

		return Response.ok().build();
	}

	public Response executeNodeCommand(String clusterId, String nodeId, String commandId) {

		Map<String,Cluster> clusters = DataHolder.getClusters();

		if(!clusters.containsKey(clusterId)){
			throw new WebApplicationException(new Throwable("Cluster is not found"),
			                                  Response.Status.BAD_REQUEST);
		}

		Map<String, Node> nodes = clusters.get(clusterId).getNodes();

		if(!nodes.containsKey(nodeId)){
			throw new WebApplicationException(new Throwable("Node is not found"),
			                                  Response.Status.BAD_REQUEST);
		}

		nodes.get(nodeId).addCommand(commandId);

		return Response.ok().build();
	}
}