package ch7.entity;

import common.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

/*
 * Named Queries improve performance
 *
 * @NamedQuery(name, query)
 * @NamedQueries for more than one @NamedQuery for an Entity
 */

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@NamedQuery(name = "Employee.findByName",
        query = "SELECT e FROM Employee e " +
                "WHERE e.name = :empName")
public class Employee extends BaseEntity {
    private String name;
    private long salary;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Department department;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "EMPL_ID")
    private List<Phone> phones;
}
