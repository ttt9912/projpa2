package ch5.entity;

import ch5.embeddable.VacationEntry;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/*
 * @ElementCollection: collection of non-Entity Objects (but rather
 * 1.) Embeddables or
 * 2.) basic types linke String, Integer, etc.)
 *
 * @ElementCollection attributes:
 * - name: optional, name of Element Collection Table
 * - joinColumns: optional, FK reference from Collection table to Entity table
 *
 * @CollectionTable (optional): define name and join column for Element Collection
 * @AttributeOverride (optional): define column names for Element Collection of Embeddables
 * @Column (optional): define column name for Element Collection of basic types
 *
 * Maps:
 * @MapKeyColumn: name der column, die den basic type key speichert
 */
@Getter
@Setter
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    // Element Collection of Embeddables
    @ElementCollection(targetClass = VacationEntry.class) // targetClass: alternative for Generic
    @CollectionTable(name = "VACATION", joinColumns = @JoinColumn(name = "EMP_ID"))
    // optional: define Element Collection Table
    @AttributeOverride(name = "daysTaken", column = @Column(name = "DAYS_ABS")) // optional: rename column names
    private Collection vacationBookings;

    // Element Collection of basic types
    @ElementCollection
    @Column(name = "NICKNAME") // optional: rename column name
    private Collection<String> nickNames;

    // Map with key = basic type
    @ElementCollection
    @CollectionTable(name = "EMP_PHONE")
    @MapKeyColumn(name = "PHONE_TYPE")
    @Column(name = "PHONE_NUM")
    private Map<String, String> phoneNumbers;

    @ManyToOne
    private Department department;

    @ManyToMany(mappedBy = "employeesByCubicle")
    private List<Department> departments;

    public Employee() {
    }

    public Employee(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
//                ", vacationBookings=" + vacationBookings +
//                ", nickNames=" + nickNames +
//                ", phoneNumbers=" + phoneNumbers +
//                ", departments=" + departments.stream().map(d -> d.getDepartmentName()).collect(Collectors.joining(", ")) +
                '}';
    }
}
