package ch6.entities.cascade_demo;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@ToString(exclude = "employee")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String number;

    @ManyToOne
    private EmployeeWithRelationships employee;
}
