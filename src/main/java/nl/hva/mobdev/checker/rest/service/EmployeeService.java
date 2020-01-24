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

/**
 * This is a service class which is the bridge between
 * the Rest Controller and the DAO of the employee.
 * Here, for example, calculation can take place
 * before it enters the database or vice versa
 *
 * @author NeeftB
 */
@Transactional
@Named(ApplicationConfig.EMPLOYEE_SERVICE_NAME)
public class EmployeeService implements IEmployeeService {

    private IEmployeeDAO employeeDAO;
    private IStatusService statusService;

    /**
     * Constructor for the EmployeeService.
     * If a new EmployeeService object is created the code will look if
     * there are already an employeeDAO object or a statusService object.
     * If so, the new created object will be injected in the already existing one.
     * This ensures that duplicate objects cannot work side by side, which can cause problems.
     *
     * @param employeeDAO
     * @param statusService
     */
    @Inject
    public EmployeeService(
            @Named(ApplicationConfig.EMPLOYEE_DAO_NAME) IEmployeeDAO employeeDAO,
            @Named(ApplicationConfig.STATUS_SERVICE_NAME) IStatusService statusService) {
        this.employeeDAO = employeeDAO;
        this.statusService = statusService;
    }

    @Override
    public Employee getEmployeeByPassId(int passId) {
    try {
        return employeeDAO.getEmployeeByPassId(passId);
    } catch (Exception e) {
        return null;
    }
    }

    @Override
    public Employee getEmployeeByEmployeeId(int employeeId) {
        return employeeDAO.getEmployeeByEmployeeId(employeeId);
    }

    @Override
    public boolean addEmployee(Employee employee) {
        if(employeeDAO.employeeExists(employee.getEmployeeId())){
            return false;
        } else {
            return employeeDAO.addEmployee(employee);
        }
    }

    @Override
    public boolean checkPassword(int passId, String password) {
        try {
            return getEmployeeByPassId(passId).getPassword().equals(password);
        } catch (Exception e) {
            return false;
        }

    }

    /**
     * This function will add a status to an employee.
     * First it will check if the employee exists. The second check
     * is to see if the employee already has a status. If so, and there is no
     * checkout time the user is still checked in. The employee must then first change
     * his status before he can check in again.
     * @param employeeId is the ID of the current employee.
     * @param status is the new status of the employee.
     * @return true if the employee exists and if he has no status yet or if his current status
     * is checked out.
     */
    @Override
    public boolean addStatusToEmployee(int employeeId, Status status) {
        Status currentStatus = statusService.getCurrentStatusByEmployeeId(employeeId);
        if (!employeeDAO.employeeExists(employeeId)
                || (currentStatus != null && currentStatus.getLastCheckOutDate() == null)
        ) {
            return false;
        } else {
            statusService.addStatus(status);
            return employeeDAO.addStatusToEmployee(getEmployeeByEmployeeId(employeeId), status);
        }
    }

}
