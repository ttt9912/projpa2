package ch6.entities.merge_demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "employee")
public class PhoneC {
    @Id
    private long id;
    private String number;

    @ManyToOne
    private EmployeeC employee;
}
