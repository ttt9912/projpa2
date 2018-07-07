package ch3.j2ee.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
public class Employee implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @PrePersist
    private void onCreate() {
        created = new Date();
    }
}
