package nl.hva.mobdev.checker.rest.resources;

import nl.hva.mobdev.checker.model.Employee;
import nl.hva.mobdev.checker.rest.service.inter.IEmployeeService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;


public class AuthenticationResource {

    private IEmployeeService employeeService;

    public AuthenticationResource(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authenticateUser(Employee employee, @Context UriInfo uri) {
        int passId = employee.getPassId();
        String password = employee.getPassword();

        try {

            // Authenticate the user using the credentials provided
            authenticate(passId, password);

            // Return the token on the response
            return Response.ok().build();

        } catch (IllegalAccessException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    private void authenticate(int passId, String password) throws IllegalAccessException {
        // Authenticate against a database, LDAP, file or whatever
        // Throw an Exception if the credentials are invalid
        if (!employeeService.checkPassword(passId, password)) {
            throw new IllegalAccessException();
        }

    }
}
