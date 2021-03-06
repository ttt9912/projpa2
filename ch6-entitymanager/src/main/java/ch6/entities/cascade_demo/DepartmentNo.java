package ch6.entities.cascade_demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentNo {

    @Id
    private long id;

    @OneToMany
    @JoinColumn(name = "DEPARTMENT_NO_ID")
    private List<EmployeeA> employees;

}
