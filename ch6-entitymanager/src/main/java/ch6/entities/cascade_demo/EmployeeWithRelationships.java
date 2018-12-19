package ch6.entities.cascade_demo;

import ch6.entities.Address;
import ch6.entities.ParkingSpace;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class EmployeeWithRelationships {
    public EmployeeWithRelationships(final long id) {
        this.id = id;
    }

    @Id
    private long id;

    @OneToOne // unidirectional: EmployeeC is the owner
    private ParkingSpace parkingSpace;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.PERSIST) // unidirectional: EmployeeC is the owner
    private List<Phone> phones;

    @ManyToOne(cascade = CascadeType.ALL) // many-to-one: EmployeeC is the owner
    private Address adress;
}
