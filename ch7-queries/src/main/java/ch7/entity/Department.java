package ch7.entity;

import common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@ToString(exclude = "employees")
@Entity
public class Department extends BaseEntity {
    private String name;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees;
}
