package nl.hva.mobdev.checker.dao.inter;

import nl.hva.mobdev.checker.model.Employee;
import nl.hva.mobdev.checker.model.Status;

public interface IEmployeeDAO {

    Employee getEmployeeByPassId(int passId);

    Employee getEmployeeByEmployeeId(int employeeId);
    boolean addEmployee(Employee employee);

    boolean employeeExists(int employeeId);
    boolean addStatusToEmployee(Employee employee, Status status);
}
