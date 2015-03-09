package org.wso2.oc.internal;


import org.wso2.oc.data.OCAgentMessage;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("internal/oc/servers/")
@Consumes("application/json")
@Produces("application/json")
public interface OCInternal {

    @POST
    public Response registerServer(OCAgentMessage OCAgentMessage);

    @PUT
    @Path("/{server_id}")
    public Response synchronizeServer(@PathParam("server_id") String server_Id,
                                      OCAgentMessage OCAgentMessage);

}

