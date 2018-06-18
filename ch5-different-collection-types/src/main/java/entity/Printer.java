package entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Data
@Entity
public class Printer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    // Relationship collection
    @OneToMany
    @OrderBy("capacity DESC")
    private List<PrinterQueue> printerQueues;

    // Element collection
    @ElementCollection
    @OrderBy("address ASC")
    private Collection<IPAddress> ipAddresses;
}
