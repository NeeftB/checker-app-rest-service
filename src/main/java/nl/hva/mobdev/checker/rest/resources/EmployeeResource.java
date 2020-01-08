package nl.hva.mobdev.checker.rest.resources;

import nl.hva.mobdev.checker.model.Employee;
import nl.hva.mobdev.checker.model.Status;
import nl.hva.mobdev.checker.rest.model.ClientApproval;
import nl.hva.mobdev.checker.rest.model.ClientError;
import nl.hva.mobdev.checker.rest.service.inter.IEmployeeService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * The REST resource with all the REST requests for the employee.
 *
 * @author NeeftB
 */
public class EmployeeResource {

    private IEmployeeService employeeService;

    public EmployeeResource(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GET
    @Path("/{passId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeeByPassId(@PathParam("passId") int passId){
        Employee employee = employeeService.getEmployeeByPassId(passId);

        if(employee != null){
            return Response.status(Response.Status.OK).entity(employee)
                    .build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ClientError("Username doesn't exist"))
                    .build();
        }
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addEmployee(Employee employee){
        if(employeeService.addEmployee(employee)){
            return Response.status(Response.Status.CREATED).entity(new ClientApproval("Account added successfully"))
                    .build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ClientError("Account already exist"))
                    .build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{employeeId}/addstatus")
    public Response addStatusToEmployee(@PathParam("employeeId") int employeeId, Status status) {
        if (employeeService.addStatusToEmployee(employeeId, status)) {
            return Response.status(Response.Status.OK).entity(new ClientApproval("Status successfully added to employee" +
                    "with employeeId " + employeeId)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ClientError("Status isn't added due an error"))
                    .build();
        }
    }
}
