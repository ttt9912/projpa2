package data;

import entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@ToString(exclude = "department")
@Entity
public class Employee extends BaseEntity {
    private String name;

    @ManyToOne
    private Department department;
}
