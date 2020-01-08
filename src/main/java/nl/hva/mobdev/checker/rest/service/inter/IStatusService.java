package nl.hva.mobdev.checker.rest.service.inter;

import nl.hva.mobdev.checker.model.Status;

/**
 * Interface for the StatusService.
 * This interface contains all functions the status service must implement.
 *
 * @author NeeftB
 */
public interface IStatusService {

    boolean addStatus(Status status);

    Status getCurrentStatusByEmployeeId(int employeeId);

    boolean changeStatus(int employeeId);
}
