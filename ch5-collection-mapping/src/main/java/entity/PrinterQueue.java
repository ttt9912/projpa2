package entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class PrinterQueue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int capacity;

    @ManyToOne
    private Printer printer;

    @OneToMany(mappedBy = "printerQueue")
    @OrderColumn(name = "PRINT_ORDER")
    private List<PrintJob> printJobs;

    public PrinterQueue() {
    }

    public PrinterQueue(int capacity, List<PrintJob> printJobs) {
        this.capacity = capacity;
        this.printJobs = printJobs;
    }

    public PrinterQueue(final int capacity, final Printer printer) {
        this.capacity = capacity;
        this.printer = printer;
    }

    @Override
    public String toString() {
        return "PrinterQueue{" +
                "capacity=" + capacity +
                ", printJobs=" + printJobs +
                '}';
    }
}
