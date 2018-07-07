package ch7.data;

import common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@ToString(exclude = "department")
@Entity
public class Employee extends BaseEntity {
    private String name;

    @ManyToOne
    private Department department;

    @OneToMany
    @JoinColumn(name = "EMPL_ID")
    private List<Phone> phones;
}
