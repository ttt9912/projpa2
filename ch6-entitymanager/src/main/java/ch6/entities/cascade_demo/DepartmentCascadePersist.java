package ch6.entities.cascade_demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentCascadePersist {

    @Id
    private long id;

    @OneToMany(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "DEPARTMENT_CASCADE_PERSIST_ID")
    private List<EmployeeA> employees;

}
