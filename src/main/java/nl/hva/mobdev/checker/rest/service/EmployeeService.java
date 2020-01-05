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
    public Employee getEmployeeByWorkerId(int workerId) {
        return employeeDAO.getEmployeeByWorkerId(workerId);
    }

    @Override
    public boolean addEmployee(Employee employee) {
        return employeeDAO.addEmployee(employee);
    }

    @Override
    public boolean checkPassword(int passId, String password) {
        if(getEmployeeByPassId(passId).getPassword().equals(password))
        {  return true;}
        else {
           return false;
        }
    }

    @Override
    public boolean addStatusToEmployee(int workerId, Status status) {
        if(!employeeDAO.employeeExists(workerId)){
            return false;
        } else {
            statusService.addStatus(status);
            return employeeDAO.addStatusToEmployee(getEmployeeByWorkerId(workerId), status);
        }
    }

}
