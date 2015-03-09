package org.wso2.oc.external;

import org.wso2.oc.data.Cluster;
import org.wso2.oc.data.Node;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

@Path("external/oc/clusters/")
public interface OCExternal {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Cluster> getAllClustersData();

	@GET
	@Path("/{cluster-Id}/")
	@Produces(MediaType.APPLICATION_JSON)
	public Cluster getClusterData(@PathParam("cluster-Id") String clusterId);

	@GET
	@Path("/{cluster-Id}/nodes/")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Node> getAllNodesData(@PathParam("cluster-Id") String clusterId);

	@GET
	@Path("/{cluster-Id}/nodes/{node-Id}/")
	@Produces(MediaType.APPLICATION_JSON)
	public Node getNodeData(@PathParam("cluster-Id") String clusterId,
	                        @PathParam("node-Id") String nodeId);

	@PUT
	@Path("/{cluster-Id}/commands/{command-Id}/")
	public Response executeClusterCommand(@PathParam("cluster-Id") int clusterId,
	                                      @PathParam("command-Id") int commandId);

	@PUT
	@Path("/{cluster-Id}/nodes/{node-Id}/commands/{command-Id}/")
	public Response executeNodeCommand(@PathParam("cluster-Id") int clusterId,
	                                   @PathParam("node-Id") String nodeId,
	                                   @PathParam("command-Id") int commandId);

}
