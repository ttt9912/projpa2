package entity;

import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@ToString
@Entity
public class PrinterQueue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int capacity;

    public PrinterQueue(int capacity) {
        this.capacity = capacity;
    }
}
