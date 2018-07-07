package ch4.relationships.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/*
 * @ManyToOne: dies ist die n-Seite der Beziehung, die einen FK besitst (owner)
 *
 * @JoinColumn: optional. Mit name wird der Name der FK-Column in der
 * EMPLOYEE Table bestimmt. Default wäre DEPARTMENT_ID
 *
 * @JoinTable: optional. Spezifiziert die Assignment Table
 *
 * @OneToMany: 1-Seite der Beziehung. IdR non-Owner Gegenstück zu @ManyToOne.
 * Owner bei Unidirectional Collection Mappings (s. phones)
 *
 * fetch: Collection Beziehungen haben default fetch = Lazy
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

    @ManyToMany
    @JoinTable(name = "EMP_PROJ",
            joinColumns = @JoinColumn(name = "EMP_ID"),
            inverseJoinColumns = @JoinColumn(name = "PROJ_ID"))
    private Collection<Project> projects;

    @OneToMany(fetch = FetchType.LAZY)
    private Collection<Phone> phones;

    @Embedded
    private Address address;

    @OneToMany
    @JoinColumn(name = "EMPLOYEE_ID")
    private List<Badge> badges;

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

    public Collection<Project> getProjects() {
        return projects;
    }

    public void setProjects(Collection<Project> projects) {
        this.projects = projects;
    }

    public Collection<Phone> getPhones() {
        return phones;
    }

    public void setPhones(Collection<Phone> phones) {
        this.phones = phones;
    }

    public List<Badge> getBadges() {
        return badges;
    }

    public void setBadges(final List<Badge> badges) {
        this.badges = badges;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", departments=" + department +
                ", parkingSpace=" + parkingSpace +
                ", projects=" + projects +
                ", phones=" + phones +
                ", badges=" + badges +
                ", name=" + address +
                '}';
    }
}
