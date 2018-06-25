package entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Map;

@Data
@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String departmentName;

    @ManyToMany
    @JoinTable(name = "DEPT_EMP",
            joinColumns = @JoinColumn(name = "DEPT_ID"),
            inverseJoinColumns = @JoinColumn(name = "EMP_ID"))
    @MapKeyColumn(name = "CUB_ID")
    private Map<String, Employee> employeesByCubicle;


    @OneToMany(mappedBy = "department")
    @MapKey(name = "id")
    private Map<Integer, Employee> employeesById;

    public Department() {
    }

    public Department(final String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                ", employeesByCubicle=" + employeesByCubicle +
                '}';
    }
}
