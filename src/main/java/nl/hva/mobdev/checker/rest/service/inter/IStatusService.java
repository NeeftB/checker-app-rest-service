package nl.hva.mobdev.checker.rest.service.inter;

import nl.hva.mobdev.checker.model.Status;

public interface IStatusService {
    boolean addStatus(Status status);

    Status getCurrentStatusByEmployeeId(int employeeId);

    boolean changeStatus(int employeeId);
}
