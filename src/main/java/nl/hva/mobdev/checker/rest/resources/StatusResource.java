package nl.hva.mobdev.checker.rest.resources;

import nl.hva.mobdev.checker.model.Employee;
import nl.hva.mobdev.checker.model.Status;
import nl.hva.mobdev.checker.rest.model.ClientError;
import nl.hva.mobdev.checker.rest.service.inter.IStatusService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class StatusResource {

    private IStatusService statusService;

    public StatusResource(IStatusService statusService) {
        this.statusService = statusService;
    }

    @GET
    @Path("{workerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCurrentStatusByWorkerId(@PathParam("workerId") int workerId){
        Status status = statusService.getCurrentStatusByWorkerId(workerId);

        if(status != null){
            return Response.status(Response.Status.OK).entity(status).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ClientError(
                    "This employee does not have a current status"
            )).build();
        }
    }
}
