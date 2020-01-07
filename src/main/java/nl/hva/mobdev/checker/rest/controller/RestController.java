package nl.hva.mobdev.checker.rest.controller;

import nl.hva.mobdev.checker.rest.config.ApplicationConfig;
import nl.hva.mobdev.checker.rest.resources.AuthenticationResource;
import nl.hva.mobdev.checker.rest.resources.EmployeeResource;
import nl.hva.mobdev.checker.rest.resources.StatusResource;
import nl.hva.mobdev.checker.rest.service.inter.IEmployeeService;
import nl.hva.mobdev.checker.rest.service.inter.IStatusService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Path;

/**
 * Main controller of all API requests
 * Here all the requests are distributed to the right resource
 * based on the url.
 *
 * @author NeeftB
 */

@Path("rest")
public class RestController {

    private IEmployeeService employeeService;
    private IStatusService statusService;

    /**
     * Constructor for the RestController.
     * If a new RestController object is created the code will look if
     * there are already an employeeService object and a statusService object.
     * If so, the new created object will be injected in the already created ones.
     * This ensures that duplicate objects cannot work side by side, which can cause problems.
     *
     * @param employeeService is a service class which contains functions that are
     *                        needed for the Employee Object. The service is the
     *                        bridge between the REST controller and the DAO.
     * @param statusService   is a service class which contains functions
     *                        that are needed for the Status Object.
     *                        The service is the bridge between the REST controller and the DAO.
     */
    @Inject
    public RestController(
            @Named(ApplicationConfig.EMPLOYEE_SERVICE_NAME)IEmployeeService employeeService,
            @Named(ApplicationConfig.STATUS_SERVICE_NAME)IStatusService statusService
    ){
        this.employeeService = employeeService;
        this.statusService = statusService;
    }

    /**
     * Creates an EmployeeResource which contains all possible REST requests
     * that are related to the employee.
     * @return an EmployeeResource
     */
    @Path("/employee")
    public EmployeeResource getEmployeeResource(){
        return new EmployeeResource(employeeService);
    }

    /**
     * Creates a StatusResource which contains all possible REST requests
     * to retrieve or change the status of an employee.
     * @return a StatusResource
     */
    @Path("/status")
    public StatusResource getStatusResource(){
        return new StatusResource(statusService);
    }

    /**
     * Creates an AuthenticationResource which contains all possible REST requests
     * for the login section.
     * @return an AuthenticationResource
     */
    @Path("/authentication")
    public AuthenticationResource getAuthenticationResource(){
        return new AuthenticationResource(employeeService);
    }
}
