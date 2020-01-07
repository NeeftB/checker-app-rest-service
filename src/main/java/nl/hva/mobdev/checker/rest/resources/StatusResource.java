package nl.hva.mobdev.checker.rest.resources;

import nl.hva.mobdev.checker.model.Employee;
import nl.hva.mobdev.checker.model.Status;
import nl.hva.mobdev.checker.rest.model.ClientApproval;
import nl.hva.mobdev.checker.rest.model.ClientError;
import nl.hva.mobdev.checker.rest.service.inter.IStatusService;
import org.hibernate.annotations.UpdateTimestamp;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class StatusResource {

    private IStatusService statusService;

    public StatusResource(IStatusService statusService) {
        this.statusService = statusService;
    }

    @GET
    @Path("{employeeId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCurrentStatusByWorkerId(@PathParam("employeeId") int employeeId) {
        Status status = statusService.getCurrentStatusByEmployeeId(employeeId);

        if(status != null){
            return Response.status(Response.Status.OK).entity(status).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ClientError(
                    "This employee does not have a current status"
            )).build();
        }
    }

    @PUT
    @Path("/{employeeId}/change")
    @Produces(MediaType.APPLICATION_JSON)
    public Response changeStatus(@PathParam("employeeId") int employeeId) {
        if (statusService.changeStatus(employeeId)) {
            return Response.status(Response.Status.OK).entity(new ClientApproval("Status changed")).build();
        } else {
            return Response.status(Response.Status.CONFLICT).entity(new ClientError("You are already checked out"))
                    .build();
        }
    }
}
