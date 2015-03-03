package org.wso2.oc.internal;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.oc.data.DataHolder;
import org.wso2.oc.data.ServerData;

import java.util.HashMap;
import java.util.Map;


@Path("internal/oc/servers/")
public class OCInternal {

	private static final Log log=LogFactory.getLog(OCInternal.class);
	 @POST
	 @Consumes("application/json")
	 @Produces("application/json")
	 public Response registerServer(ServerData serverData){
		 int id= DataHolder.getServerCount();
		 serverData.setServerId(id);
		 DataHolder.getServersData().put(id, serverData);
		 Map<String,Integer> response=new HashMap<String,Integer>();
		 response.put("serverId",id);
	     return Response.status(201).entity(response).build();		
	 }
	 
	 @PUT
	 @Path("/{server_id}")
	 @Consumes("application/json")
	 @Produces("application/json")
	 public Response synchronizeServer(@PathParam("server_id") int server_id,ServerData serverData){
		 String[] response = DataHolder.updateServerData(server_id,serverData);
		 return Response.status(201).entity(response).build();
	 }
	 
}