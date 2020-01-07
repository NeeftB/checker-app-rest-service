package nl.hva.mobdev.checker.dao;

import nl.hva.mobdev.checker.dao.inter.IEmployeeDAO;
import nl.hva.mobdev.checker.model.Employee;
import nl.hva.mobdev.checker.model.Status;
import nl.hva.mobdev.checker.rest.config.ApplicationConfig;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@Stateless
@Transactional
@Named(ApplicationConfig.EMPLOYEE_DAO_NAME)
public class EmployeeDAO implements IEmployeeDAO {

    @PersistenceContext(unitName = ApplicationConfig.PERSISTENCE_UNIT_NAME)
    private EntityManager em;

    @Override
    public Employee getEmployeeByPassId(int passId) {
        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e " +
                "WHERE e.passId = :passId", Employee.class);
        query.setParameter("passId", passId);
        return query.getSingleResult();
    }

    @Override
    public Employee getEmployeeByEmployeeId(int employeeId) {
        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e " +
                "WHERE e.employeeId = :employeeId", Employee.class);
        query.setParameter("employeeId", employeeId);
        return query.getSingleResult();
    }

    @Override
    public boolean addEmployee(Employee employee) {
        em.persist(employee);
        return true;
    }

    @Override
    public boolean employeeExists(int employeeId) {
        Query query = em.createQuery("SELECT e FROM Employee e WHERE e.employeeId = : employeeId");
        query.setParameter("employeeId", employeeId);

        return query.getResultList().size() > 0;
    }

    @Override
    public boolean addStatusToEmployee(Employee employee, Status status) {
        employee.addStatus(status);
        em.flush();
        return true;
    }
}
