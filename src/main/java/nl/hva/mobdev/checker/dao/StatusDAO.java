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
    public Status getStatusByWorkerId(int workerId) {
        TypedQuery<Status> query = em.createQuery("SELECT s FROM Status s " +
                "WHERE s.employee.workerId = :workerId ORDER BY s.id DESC", Status.class);
        query.setMaxResults(1);
        query.setParameter("workerId", workerId);
        return query.getSingleResult();
    }

    @Override
    public boolean changeStatus(Status status) {
        if(status.getStatus().equalsIgnoreCase("in")){
            status.setStatus("out");
        } else {
            status.setStatus("in");
        }
        em.flush();
        return true;
    }
}
