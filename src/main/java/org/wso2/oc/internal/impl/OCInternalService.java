package org.wso2.oc.internal.impl;


import javax.ws.rs.core.Response;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.oc.data.DataHolder;
import org.wso2.oc.data.OCAgentMessage;
import org.wso2.oc.internal.OCInternal;
import java.util.HashMap;
import java.util.Map;



public class OCInternalService implements OCInternal{

	private static final Log log=LogFactory.getLog(OCInternalService.class);
	public Response registerServer(OCAgentMessage OCAgentMessage){
		String serverID= DataHolder.registerServerData(OCAgentMessage);
		Map<String,String> response=new HashMap<String,String>();
		response.put("serverId",serverID);
		return Response.status(201).entity(response).build();
	}
	public Response synchronizeServer(String server_Id,OCAgentMessage OCAgentMessage){
		String[] response = DataHolder.updateServerData(server_Id, OCAgentMessage);
		return Response.status(200).entity(response).build();
	}

}