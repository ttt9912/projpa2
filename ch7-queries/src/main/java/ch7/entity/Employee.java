package ch7.entity;

import common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

/*
 * Named Queries improve performance
 *
 * @NamedQuery(city, query)
 * @NamedQueries for more than one @NamedQuery for an Entity
 */

@Getter
@Setter
@ToString(exclude = {"department", "phones"})
@Entity
@NamedQuery(name = "Employee.findByName",
        query = "SELECT e FROM Employee e " +
                "WHERE e.city = :empName")
public class Employee extends BaseEntity {
    private String name;
    private long salary;

    @ManyToOne
    private Department department;

    @OneToMany
    @JoinColumn(name = "EMPL_ID")
    private List<Phone> phones;


}
