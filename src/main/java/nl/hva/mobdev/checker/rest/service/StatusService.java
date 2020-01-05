package nl.hva.mobdev.checker.rest.service;

import nl.hva.mobdev.checker.dao.inter.IStatusDAO;
import nl.hva.mobdev.checker.model.Status;
import nl.hva.mobdev.checker.rest.config.ApplicationConfig;
import nl.hva.mobdev.checker.rest.service.inter.IEmployeeService;
import nl.hva.mobdev.checker.rest.service.inter.IStatusService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

@Transactional
@Named(ApplicationConfig.STATUS_SERVICE_NAME)
public class StatusService implements IStatusService {

    private IStatusDAO statusDAO;
    private IEmployeeService employeeService;

    @Inject
    public StatusService(
            @Named(ApplicationConfig.STATUS_DAO_NAME) IStatusDAO statusDAO,
            @Named(ApplicationConfig.EMPLOYEE_SERVICE_NAME) IEmployeeService employeeService) {
        this.statusDAO = statusDAO;
        this.employeeService = employeeService;
    }

    @Override
    public boolean addStatus(Status status) {
        return statusDAO.addStatus(status);
    }

}
