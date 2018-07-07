package ch5.entity;

import ch5.embeddable.Color;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/*
 * @OrderBy ordering a relationship collection by primary key,
 * not-persisted. @OrderBy("attr") ordering by attribute
 *
 * @OrderBy only works if the data is loaded back from DB in a new rum of the program.
 * Therefore, the drop-and-create-tables property has to be removed from the persistence.xml.
 */

@Getter
@Setter
@Entity
public class Printer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    // Relationship collection
    @OneToMany(mappedBy = "printer")
    @OrderBy(value = "capacity")
    private List<PrinterQueue> printerQueues;

    // Element collection
    @ElementCollection
    @OrderBy(value = "name")
    private Collection<Color> colors;

    public Printer() {
    }

    @Override
    public String toString() {
        return "Printer{" +
                "\n\tprinterQueues=" + printerQueues +
                "\n\tcolors=" + colors +
                '}';
    }
}
