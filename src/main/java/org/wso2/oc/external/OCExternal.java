package org.wso2.oc.external;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("external/oc/servers/")
public interface OCExternal {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllServersData();

	@GET
	@Path("/{server-id}/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getServerData(@PathParam("server-id") int id);

	@PUT
	@Path("/{server-id}/commands/{command-id}/")
	public Response executeCommand(@PathParam("server-id") int serverId,
	                               @PathParam("command-id") int commandId);

}
