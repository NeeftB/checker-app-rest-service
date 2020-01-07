package nl.hva.mobdev.checker.dao.inter;

import nl.hva.mobdev.checker.model.Status;

public interface IStatusDAO {

    boolean addStatus(Status status);
    Status getStatusByWorkerId(int workerId);
    boolean changeStatus(Status status);
}
