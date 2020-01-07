package nl.hva.mobdev.checker.rest.config;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Configuration for Rest Endpoint using JAX-RS.
 * Here the application path (start url) is defined.
 *
 * @author NeeftB
 */
@ApplicationPath("services")
public class ApplicationConfig extends Application {

    public final static String PERSISTENCE_UNIT_NAME = "Checker_PU";

    public final static String EMPLOYEE_DAO_NAME = "employee_dao";
    public final static String STATUS_DAO_NAME = "status_dao";

    public final static String EMPLOYEE_SERVICE_NAME = "employee_service";
    public final static String STATUS_SERVICE_NAME = "status_service";
}
