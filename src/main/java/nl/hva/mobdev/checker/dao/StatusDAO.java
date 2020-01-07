package nl.hva.mobdev.checker.dao;

import nl.hva.mobdev.checker.dao.inter.IStatusDAO;
import nl.hva.mobdev.checker.model.Status;
import nl.hva.mobdev.checker.rest.config.ApplicationConfig;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.Date;

@Stateless
@Transactional
@Named(ApplicationConfig.STATUS_DAO_NAME)
public class StatusDAO implements IStatusDAO {

    @PersistenceContext(unitName = ApplicationConfig.PERSISTENCE_UNIT_NAME)
    private EntityManager em;

    @Override
    public boolean addStatus(Status status) {
        em.persist(status);
        return true;
    }

    @Override
    public Status getStatusByEmployeeId(int employeeId) {
        TypedQuery<Status> query = em.createQuery("SELECT s FROM Status s " +
                "WHERE s.employee.employeeId = :employeeId ORDER BY s.id DESC", Status.class);
        query.setMaxResults(1);
        query.setParameter("employeeId", employeeId);
        return query.getSingleResult();
    }

    @Override
    public boolean changeStatus(Status status) {
        if (status.getStatus().equalsIgnoreCase("in")) {
            status.setStatus("out");
            status.setLastCheckOutDate(new Date());
            em.flush();
            return true;
        } else {
            return false;
        }
    }
}
