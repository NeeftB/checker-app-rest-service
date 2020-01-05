package nl.hva.mobdev.checker.model;


import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Employee")
public class Employee implements Serializable {

    @Id
    @Column(name = "worker_id")
    private int workerId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "pass_id")
    private int passId;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonbTransient
    private Set<Status> statuses = new HashSet<>();

    public Employee(){

    }

    public Employee(int workerId, String firstName, String lastName, int passId, String password) {
        this.workerId = workerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.passId = passId;
        this.password = password;
    }

    public int getWorkerId() {
        return workerId;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
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

    public void addStatus(Status status) {
        getStatus().add(status);
        status.setEmployee(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return workerId == employee.workerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(workerId);
    }

}
