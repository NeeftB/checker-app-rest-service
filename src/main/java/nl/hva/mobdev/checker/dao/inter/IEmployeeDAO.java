package nl.hva.mobdev.checker.dao.inter;

import nl.hva.mobdev.checker.model.Employee;
import nl.hva.mobdev.checker.model.Status;

public interface IEmployeeDAO {

    Employee getEmployeeByPassId(int passId);
    Employee getEmployeeByWorkerId(int workerId);
    boolean addEmployee(Employee employee);
    boolean employeeExists(int workerId);
    boolean addStatusToEmployee(Employee employee, Status status);
}
