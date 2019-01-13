package ch7.entity;

import common.entity.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "employee")
public class Phone extends BaseEntity {
    private String type;
    private String number;
    @ManyToOne
    private Employee employee;
}
