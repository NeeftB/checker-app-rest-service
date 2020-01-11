package nl.hva.mobdev.checker.rest.service;

import nl.hva.mobdev.checker.dao.inter.IStatusDAO;
import nl.hva.mobdev.checker.model.Status;
import nl.hva.mobdev.checker.rest.config.ApplicationConfig;
import nl.hva.mobdev.checker.rest.service.inter.IEmployeeService;
import nl.hva.mobdev.checker.rest.service.inter.IStatusService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

/**
 * This is a service class which is the bridge between
 * the Rest Controller and the DAO of the status of the employee.
 * Here, for example, calculation can take place
 * before it enters the database or vice versa
 *
 * @author NeeftB
 */

@Transactional
@Named(ApplicationConfig.STATUS_SERVICE_NAME)
public class StatusService implements IStatusService {

    private IStatusDAO statusDAO;

    /**
     * Constructor for the StatusService.
     * If a new StatusService object is created the code will look if
     * there are already a StatusDao object.
     * If so, the new created object will be injected in the already existing one.
     * This ensures that duplicate objects cannot work side by side, which can cause problems.
     *
     * @param statusDAO
     */
    @Inject
    public StatusService(
            @Named(ApplicationConfig.STATUS_DAO_NAME) IStatusDAO statusDAO) {
        this.statusDAO = statusDAO;
    }

    @Override
    public boolean addStatus(Status status) {
        return statusDAO.addStatus(status);
    }



    @Override
    public Status getCurrentStatusByEmployeeId(int employeeId) {
        if(statusDAO.checkEmployeeHasStatus(employeeId)){
            return statusDAO.getStatusByEmployeeId(employeeId);
        } else {
            return null;
        }
    }

    @Override
    public boolean changeStatus(int employeeId) {
        Status status = getCurrentStatusByEmployeeId(employeeId);
        return statusDAO.changeStatus(status);
    }
}
