package org.wso2.oc.internal.impl;


import javax.ws.rs.core.Response;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.oc.data.DataHolder;
import org.wso2.oc.data.ServerData;
import org.wso2.oc.internal.OCInternal;
import java.util.HashMap;
import java.util.Map;



public class OCInternalService implements OCInternal{

	private static final Log log=LogFactory.getLog(OCInternalService.class);
	public Response registerServer(ServerData serverData){
		int id= DataHolder.incrementServerCount();
		serverData.setServerId(id);
		DataHolder.getServersData().put(id, serverData);
		Map<String,Integer> response=new HashMap<String,Integer>();
		response.put("serverId",id);
		return Response.status(201).entity(response).build();
	}

	public Response synchronizeServer(int server_id,ServerData serverData){
		String[] response = DataHolder.updateServerData(server_id,serverData);
		return Response.status(201).entity(response).build();
	}

}