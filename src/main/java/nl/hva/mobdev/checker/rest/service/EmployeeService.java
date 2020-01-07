package nl.hva.mobdev.checker.rest.service;

import nl.hva.mobdev.checker.dao.inter.IEmployeeDAO;
import nl.hva.mobdev.checker.model.Employee;
import nl.hva.mobdev.checker.model.Status;
import nl.hva.mobdev.checker.rest.config.ApplicationConfig;
import nl.hva.mobdev.checker.rest.service.inter.IEmployeeService;
import nl.hva.mobdev.checker.rest.service.inter.IStatusService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;


@Transactional
@Named(ApplicationConfig.EMPLOYEE_SERVICE_NAME)
public class EmployeeService implements IEmployeeService {

    private IEmployeeDAO employeeDAO;
    private IStatusService statusService;

    @Inject
    public EmployeeService(
            @Named(ApplicationConfig.EMPLOYEE_DAO_NAME)IEmployeeDAO employeeDAO,
            @Named(ApplicationConfig.STATUS_SERVICE_NAME) IStatusService statusService){
        this.employeeDAO = employeeDAO;
        this.statusService = statusService;
    }

    @Override
    public Employee getEmployeeByPassId(int passId) {
        return employeeDAO.getEmployeeByPassId(passId);
    }

    @Override
    public Employee getEmployeeByEmployeeId(int employeeId) {
        return employeeDAO.getEmployeeByEmployeeId(employeeId);
    }

    @Override
    public boolean addEmployee(Employee employee) {
        return employeeDAO.addEmployee(employee);
    }

    @Override
    public boolean checkPassword(int passId, String password) {
        return getEmployeeByPassId(passId).getPassword().equals(password);
    }

    @Override
    public boolean addStatusToEmployee(int employeeId, Status status) {
        if (!employeeDAO.employeeExists(employeeId)) {
            return false;
        } else {
            statusService.addStatus(status);
            return employeeDAO.addStatusToEmployee(getEmployeeByEmployeeId(employeeId), status);
        }
    }

}
