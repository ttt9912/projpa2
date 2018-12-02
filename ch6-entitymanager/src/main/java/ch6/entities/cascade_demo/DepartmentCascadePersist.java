package ch6.entities.cascade_demo;

import ch6.entities.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Department")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentCascadePersist {

    @Id
    private long id;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "DEPARTMENT_ID")
    private List<Employee> employees;

}
