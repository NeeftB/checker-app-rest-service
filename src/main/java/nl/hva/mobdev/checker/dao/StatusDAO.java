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

/**
 * This is the data access object for the status of the employee.
 * Here are all queries defined to access the Status table
 * in the database.
 *
 * @author NeeftB
 */
@Stateless
@Transactional
@Named(ApplicationConfig.STATUS_DAO_NAME)
public class StatusDAO implements IStatusDAO {

    /**
     * This entity manager is doing all the work between the API and the
     * database. The PersistenceContext ensures that each new object will
     * look to the correct persistence unit. This prevents multiple
     * entity managers from working side by side
     */
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
    public boolean checkEmployeeHasStatus(int employeeId) {
        TypedQuery<Status> query = em.createQuery("SELECT s FROM Status s " +
                "WHERE s.employee.employeeId = :employeeId ORDER BY s.id DESC", Status.class);
        query.setMaxResults(1);
        query.setParameter("employeeId", employeeId);

        return query.getResultList().size() > 0;
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
