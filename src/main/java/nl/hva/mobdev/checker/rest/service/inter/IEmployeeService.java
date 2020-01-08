package nl.hva.mobdev.checker.rest.service.inter;

import nl.hva.mobdev.checker.model.Employee;
import nl.hva.mobdev.checker.model.Status;

/**
 * Interface for the EmployeeService.
 * This interface contains all functions the employee service must implement.
 *
 * @author NeeftB
 */
public interface IEmployeeService {

    Employee getEmployeeByPassId(int passId);

    Employee getEmployeeByEmployeeId(int employeeId);

    boolean addEmployee(Employee employee);

    boolean checkPassword(int passId, String password);

    boolean addStatusToEmployee(int workerId, Status status);
}
