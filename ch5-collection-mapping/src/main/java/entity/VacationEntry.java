package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Embeddable
public class VacationEntry {

    @Temporal(TemporalType.DATE)
    private Calendar startDate;

    @Column(name = "DAYS") // define column name in Element Collection table
    private int daysTaken;

    @Override
    public String toString() {
        return "VacationEntry{" +
                "startDate=" + new SimpleDateFormat("yyyy MMM dd").format(startDate.getTime()) +
                ", daysTaken=" + daysTaken +
                '}';
    }
}
