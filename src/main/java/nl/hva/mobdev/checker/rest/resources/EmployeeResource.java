package nl.hva.mobdev.checker.rest.resources;

import nl.hva.mobdev.checker.model.Employee;
import nl.hva.mobdev.checker.model.Status;
import nl.hva.mobdev.checker.rest.model.ClientApproval;
import nl.hva.mobdev.checker.rest.model.ClientError;
import nl.hva.mobdev.checker.rest.service.inter.IEmployeeService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
    @Path("/{workerId}/addstatus")
    public Response addStatusToEmployee(@PathParam("workerId") int workerId, Status status){
        if(employeeService.addStatusToEmployee(workerId,status)){
            return Response.status(Response.Status.OK).entity(new ClientApproval("Status successfully added to employee" +
                    "with workerId " + workerId)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ClientError("Status isn't added due an error"))
                    .build();
        }
    }
}
