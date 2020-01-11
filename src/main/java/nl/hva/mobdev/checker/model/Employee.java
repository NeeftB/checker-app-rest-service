package nl.hva.mobdev.checker.model;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents an employee that works at the company.
 * The object only contains the necessary information
 * about the employee that the mobile app will use.
 *
 * @author NeeftB
 */
@Entity
@Table(name = "Employee")
public class Employee implements Serializable {

    @Id
    @Column(name = "employee_id")
    private int employeeId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "pass_id")
    private int passId;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Status> statuses = new HashSet<>();

    /**
     * Creates a new Employee with no values.
     * This constructor will be used by Hibernate.
     */
    public Employee(){
    }

    /**
     * Creates a new Employee.
     * An Employee contains:
     *
     * @param employeeId This is the employeeId that is given by the company.
     * @param firstName  Contains the first name of the employee.
     * @param lastName   Contains the sur name with prefix of the employee.
     * @param passId     This is the id of the parking pass of the employee.
     * @param password   This contains the password that an employee has chosen.
     */
    public Employee(int employeeId, String firstName, String lastName, int passId, String password) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.passId = passId;
        this.password = password;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPassId() {
        return passId;
    }

    public void setPassId(int passId) {
        this.passId = passId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Status> getStatus() {
        return statuses;
    }

    /**
     * Adds a status to the employee
     * @param status is a Status object.
     * @see Status
     */
    public void addStatus(Status status) {
        getStatus().add(status);
        status.setEmployee(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return employeeId == employee.employeeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId);
    }

}
