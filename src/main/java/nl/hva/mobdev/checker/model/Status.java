package nl.hva.mobdev.checker.model;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Represents a parking status of an employee.
 * When created a the status will always be in.
 * Also a new check in date will be created.
 *
 * @author NeeftB
 */
@Entity
@Table(name = "Status")
public class Status implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id")
    private int id;

    @Column(name = "status")
    private String status = "in";

    @Column(name = "last_check_in_date")
    private Date lastCheckInDate = new Date();

    @Column(name = "last_check_out_date")
    private Date lastCheckOutDate;

    @JsonbTransient
    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employee;

    public Status() {
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getLastCheckInDate() {
        return lastCheckInDate;
    }

    public void setLastCheckInDate(Date lastCheckInDate) {
        this.lastCheckInDate = lastCheckInDate;
    }

    public Date getLastCheckOutDate() {
        return lastCheckOutDate;
    }

    public void setLastCheckOutDate(Date lastCheckOutDate) {
        this.lastCheckOutDate = lastCheckOutDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Status status = (Status) o;
        return id == status.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
