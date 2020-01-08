package nl.hva.mobdev.checker.rest.resources;

import nl.hva.mobdev.checker.model.Employee;
import nl.hva.mobdev.checker.rest.service.EmployeeService;
import nl.hva.mobdev.checker.rest.service.inter.IEmployeeService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * The REST resource for the login.
 * This class contains all the REST requests and function concerning
 * the login process.
 *
 * @author NeeftB
 */
public class AuthenticationResource {

    private IEmployeeService employeeService;

    /**
     * Constructor for the resource.
     * The employeeService is chosen as parameter
     * because the login process is used by the employee.
     *
     * @param employeeService is given by the RestController and contains
     *                        all the functions this resource need.
     * @see EmployeeService
     */
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

            // Return OK response if the credentials are correct
            return Response.ok().build();

        } catch (IllegalAccessException e) {
            // Return FORBIDDEN response if an error occurred during the
            // login process.
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    private void authenticate(int passId, String password) throws IllegalAccessException {
        // Authenticate against a database.
        // Throws an Exception if the credentials are invalid.
        if (!employeeService.checkPassword(passId, password)) {
            throw new IllegalAccessException();
        }

    }
}
