package org.wso2.oc.internal;


import org.wso2.oc.data.ServerData;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("internal/oc/servers/")
public interface OCInternal {
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response registerServer(ServerData serverData);

    @PUT
    @Path("/{server_id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response synchronizeServer(@PathParam("server_id") int server_id,ServerData serverData);
}

