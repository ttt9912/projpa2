package ch6.entities.merge_demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeC {
    @Id
    private long id;
    private String name;
    @ManyToOne(cascade = CascadeType.MERGE)
    private AddressC address;
    @ManyToOne
    private DepartmentC department;
    @OneToMany(mappedBy = "employee", cascade = CascadeType.MERGE)
    private List<PhoneC> phones;
    @OneToMany(cascade = CascadeType.MERGE)
    private List<BadgeC> badges;
}
