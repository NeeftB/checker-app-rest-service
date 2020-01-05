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

@Path("rest")
public class RestController {

    private IEmployeeService employeeService;
    private IStatusService statusService;


    @Inject
    public RestController(
            @Named(ApplicationConfig.EMPLOYEE_SERVICE_NAME)IEmployeeService employeeService,
            @Named(ApplicationConfig.STATUS_SERVICE_NAME)IStatusService statusService
    ){
        this.employeeService = employeeService;
        this.statusService = statusService;
    }

    @Path("/employee")
    public EmployeeResource getEmployeeResource(){
        return new EmployeeResource(employeeService);
    }

    @Path("/status")
    public StatusResource getStatusResource(){
        return new StatusResource(statusService);
    }

    @Path("/authentication")
    public AuthenticationResource getAuthenticationResource(){
        return new AuthenticationResource(employeeService);
    }
}
