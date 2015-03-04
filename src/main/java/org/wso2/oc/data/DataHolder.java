package org.wso2.oc.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataHolder {
	private static Map<Integer, ServerData> serverStorage = new HashMap<Integer, ServerData>();
	private static Map<Integer, ArrayList<String>> commands =
			new HashMap<Integer, ArrayList<String>>();
	private static int serverCount = 1;

	/**
	 * @return the serversData
	 */
	public static Map<Integer, ServerData> getServersData() {
		return serverStorage;
	}

	/**
	 * @param serversData the serversData to set
	 */
	public static void setServersData(Map<Integer, ServerData> serversData) {
		DataHolder.serverStorage = serversData;
	}

	public static int getServerCount() {
		return serverCount;
	}

	/**
	 * @return the serverCount
	 */
	public static int incrementServerCount() {
		return serverCount++;
	}

	/**
	 * @param serverCount the serverCount to set
	 */
	public static void setServerCount(int serverCount) {
		DataHolder.serverCount = serverCount;
	}

	public static void addCommand(int serverId, int commandId) {

		ArrayList<String> serverCommands;

		if (commands.get(serverId) == null) {
			serverCommands = new ArrayList<String>();
			commands.put(serverId, serverCommands);
		} else
			serverCommands = commands.get(serverId);

		switch (commandId) {
			case 1:
				serverCommands.add(ServerConstants.FORCE_RESTART);
				break;
			case 2:
				serverCommands.add(ServerConstants.FORCE_SHUTDOWN);
				break;
			case 3:
				serverCommands.add(ServerConstants.GRACEFUL_RESTART);
				break;
			case 4:
				serverCommands.add(ServerConstants.GRACEFUL_SHUTDOWN);
				break;
		}
	}

	public static String[] updateServerData(int serverId, ServerData data) {
		ServerData temp = serverStorage.get(serverId);
		temp.setFreeMemory(data.getFreeMemory());
		temp.setIdleCpuUsage(data.getIdleCpuUsage());
		temp.setSystemCpuUsage(data.getSystemCpuUsage());
		temp.setUserCpuUsage(data.getUserCpuUsage());
		temp.setAdminServiceUrl(data.getServerUpTime());
		temp.setServerUpTime(data.getServerUpTime());
		temp.setThreadCount(data.getThreadCount());
		temp.setSystemLoadAverage(data.getSystemLoadAverage());
		temp.setTimestamp(data.getTimestamp());
		temp.setTenants(data.getTenants());
		serverStorage.put(serverId, temp);

		String tempArray[];

		if (commands.get(serverId) != null) {
			tempArray = commands.get(serverId).toArray(new String[commands.get(serverId).size()]);
			commands.get(serverId).clear();
		} else {
			tempArray = new String[0];
		}

		return tempArray;
	}

}
