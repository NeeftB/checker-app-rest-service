package nl.hva.mobdev.checker.dao.inter;

import nl.hva.mobdev.checker.model.Status;

import java.util.Set;

/**
 * Interface for the StatusDao.
 * This interface contains all functions the status dao must implement.
 *
 * @author NeeftB
 */
public interface IStatusDAO {

    boolean addStatus(Status status);

    Status getStatusByEmployeeId(int employeeId);

    boolean checkEmployeeHasStatus(int employeeId);

    boolean changeStatus(Status status);

    Set<Status> getStatusHistoryOfEmployee(int employeeId, int limit);
}
