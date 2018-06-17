package p1_element_collections.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

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
 */
@Data
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
}
