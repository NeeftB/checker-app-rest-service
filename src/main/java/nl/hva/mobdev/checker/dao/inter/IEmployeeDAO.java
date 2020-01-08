package nl.hva.mobdev.checker.dao.inter;

import nl.hva.mobdev.checker.model.Employee;
import nl.hva.mobdev.checker.model.Status;

/**
 * Interface for the EmployeeDao.
 * This interface contains all functions the employee dao must implement.
 *
 * @author NeeftB
 */
public interface IEmployeeDAO {

    Employee getEmployeeByPassId(int passId);

    Employee getEmployeeByEmployeeId(int employeeId);

    boolean addEmployee(Employee employee);

    boolean employeeExists(int employeeId);

    boolean addStatusToEmployee(Employee employee, Status status);
}
