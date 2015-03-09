package org.wso2.oc.external.impl;

import org.wso2.oc.data.*;
import org.wso2.oc.external.OCExternal;

import javax.jws.WebService;
import javax.ws.rs.core.Response;
import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class OCExternalService implements OCExternal {

	public Map<String, Cluster> getAllClustersData() {
		return null;
	}

	public Cluster getClusterData(String clusterId) {
		return null;
	}

	public Map<String, Node> getAllNodesData(String clusterId) {
		return null;
	}

	public Node getNodeData(String clusterId, String nodeId) {
		return null;
	}

	public Response executeClusterCommand(int clusterId, int commandId) {
		return null;
	}

	public Response executeNodeCommand(int clusterId, String nodeId, int commandId) {
		return null;
	}

	public void updateServersStatus(OCAgentMessage[] servers){

		for(OCAgentMessage server:servers){
			updateServerStatus(server);
		}
	}

	public void updateServerStatus(OCAgentMessage server){
		Date currentTime = new Date();

		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");

		Date lastServerUpTime = null;

		try {
			lastServerUpTime = dateFormat.parse(server.getTimestamp());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		long diff = currentTime.getTime() - lastServerUpTime.getTime();

		if(diff>60000){
			server.setStatus(ServerConstants.SERVER_DOWN);
		}else if(diff>20000){
			server.setStatus(ServerConstants.SERVER_NOT_REPORTING);
		}else{
			server.setStatus(ServerConstants.SERVER_RUNNING);
		}
	}
}