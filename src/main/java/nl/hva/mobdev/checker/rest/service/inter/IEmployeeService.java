package nl.hva.mobdev.checker.rest.service.inter;

import nl.hva.mobdev.checker.model.Employee;
import nl.hva.mobdev.checker.model.Status;

public interface IEmployeeService {

    Employee getEmployeeByPassId(int passId);

    Employee getEmployeeByEmployeeId(int employeeId);
    boolean addEmployee(Employee employee);
    boolean checkPassword(int passId, String password);
    boolean addStatusToEmployee(int workerId, Status status);
}
