package org.wso2.oc.data;

import java.util.HashMap;
import java.util.Map;

public class ClusterCommand extends Command{
    public static Map<String,Boolean> executedNodes=new HashMap<String,Boolean>();
    public static Node nextNode=null;
    public static Node previousNode=null;
    public static boolean isPreviousNodeUp=false;
    public ClusterCommand(String commandName) {
        super(commandName);
    }
}
