package entity;

import javax.persistence.*;

/*
 * @OneToOne mit mappedBy: dies ist nicht die owner Seite
 * der Beziehung und besitzt keine FK Column
 */

@Entity
public class ParkingSpace {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String location;

    @OneToOne(mappedBy = "parkingSpace")
    private Employee employee;

    public ParkingSpace() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "ParkingSpace{" +
                "id=" + id +
                ", location='" + location + '\'' +
                '}';
    }
}
