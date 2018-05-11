package entity;

import javax.persistence.*;

/*
 * @ManyToOne: dies ist die n-Seite der Beziehung, die einen FK besitst (owner)
 *
 * @JoinColumn: optional. Mit name wird der Name der FK-Column in der
 * EMPLOYEE Table bestimmt. Default wäre DEPARTMENT_ID
 */

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "DEPT_ID")
    private Department department;

    @OneToOne
    @JoinColumn(name = "PSPACE_ID")
    private ParkingSpace parkingSpace;

    public Employee() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public ParkingSpace getParkingSpace() {
        return parkingSpace;
    }

    public void setParkingSpace(ParkingSpace parkingSpace) {
        this.parkingSpace = parkingSpace;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department=" + department +
                ", parkingSpace=" + parkingSpace +
                '}';
    }
}
