package nl.hva.mobdev.checker.rest.resources;

import nl.hva.mobdev.checker.model.Status;
import nl.hva.mobdev.checker.rest.model.ClientApproval;
import nl.hva.mobdev.checker.rest.model.ClientError;
import nl.hva.mobdev.checker.rest.service.inter.IStatusService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;

/**
 * The REST resource with all the REST requests for the status
 * of the employee.
 *
 * @author NeeftB
 */
public class StatusResource {

    private IStatusService statusService;

    public StatusResource(IStatusService statusService) {
        this.statusService = statusService;
    }

    @GET
    @Path("{employeeId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCurrentStatusByEmployeeId(@PathParam("employeeId") int employeeId) {
        Status status = statusService.getCurrentStatusByEmployeeId(employeeId);

        if (status != null) {
            return Response.status(Response.Status.OK).entity(status).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ClientError(
                    "This employee does not have a current status"
            )).build();
        }
    }

    /**
     * Function to get the history of the parking statuses of an employee.
     * @param employeeId is the Id of the employee. It is used to find all statuses that
     *                   belongs to the employee with this Id.
     * @param limit is the max number of results that you get back in your request.
     * @return a response with the history of the statuses. Otherwise an error message.
     */
    @GET
    @Path("{employeeId}/history/{limit}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStatusHistoryOfEmployee(@PathParam("employeeId") int employeeId,
                                         @PathParam("limit") int limit) {
        Set<Status> statuses = statusService.getStatusHistoryOfEmployee(employeeId, limit);
        if(statuses.size() > 0){
            return Response.status(Response.Status.OK).entity(statuses).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ClientError("History not available")).build();
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
