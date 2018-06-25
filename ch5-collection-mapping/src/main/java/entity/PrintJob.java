package entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class PrintJob {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    @ManyToOne
    private PrinterQueue printerQueue;

    public PrintJob() {
    }

    public PrintJob(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PrintJob{" +
                "name='" + name + '\'' +
                '}';
    }
}
