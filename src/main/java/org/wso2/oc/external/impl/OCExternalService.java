package org.wso2.oc.external.impl;

import org.wso2.oc.data.DataHolder;
import org.wso2.oc.data.ServerConstants;
import org.wso2.oc.data.ServerData;
import org.wso2.oc.external.OCExternal;

import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class OCExternalService implements OCExternal {

	public OCExternalService() {
		//		init();
	}

	public Response getAllServersData() {

		Map<Integer, ServerData> map = DataHolder.getServersData();

		ServerData[] servers = map.values().toArray(new ServerData[map.values().size()]);

		updateServersStatus(servers);

		return Response.ok(servers).build();
	}

	public Response getServerData(int id) {

		Map<Integer, ServerData> map = DataHolder.getServersData();

		if (map.get(id) != null) {
			updateServerStatus(map.get(id));
			return Response.ok(map.get(id)).build();
		}
		else
			return Response.ok().build();
	}

	public Response executeCommand(int serverId, int commandId) {
		DataHolder.addCommand(serverId, commandId);
		return Response.ok().build();
	}

	public void updateServersStatus(ServerData[] servers){

		for(ServerData server:servers){
			updateServerStatus(server);
		}
	}

	public void updateServerStatus(ServerData server){
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

	//	public void init() {
	//		ServerData s = new ServerData("https://10.100.4.113:9455/", "Application Server", "5.2.1",
	//		                              "wso2.as.domain", "worker", "https://10.100.4.113:9455/",
	//		                              "1970-01-01 05:30:00", "wind", "12345", "4", "1234", "123.23",
	//		                              new String[] { "patch0", "patch1" });
	//		s.setFreeMemory("123");
	//		s.setIdleCpuUsage("51");
	//		s.setSystemCpuUsage("11");
	//		s.setUserCpuUsage("234");
	//		s.setServerUpTime("123");
	//		s.setThreadCount("24");
	//		s.setSystemLoadAverage("123");
	//		s.setTenants(new String[] { "abc", "asd" });
	//
	//		ServerData s1 = new ServerData("https://10.100.4.113:9462/", "Application Server", "5.2.1",
	//		                               "wso2.as.domain", "worker", "https://10.100.4.113:9462/",
	//		                               "1970-01-01 05:30:00", "wind", "12345", "4", "1234",
	//		                               "123.23", new String[] { "patch0", "patch1" });
	//		s1.setFreeMemory("123");
	//		s1.setIdleCpuUsage("51");
	//		s1.setSystemCpuUsage("11");
	//		s1.setUserCpuUsage("234");
	//		s1.setServerUpTime("123");
	//		s1.setThreadCount("24");
	//		s1.setSystemLoadAverage("123");
	//		s1.setTenants(new String[] { "abc", "asd" });
	//
	//		DataHolder.getServersData().put(1, s1);
	//		DataHolder.getServersData().put(2, s1);
	//	}

}