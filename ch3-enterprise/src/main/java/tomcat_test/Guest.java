package tomcat_test;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

public class Guest {
    private static final long serialVersionUID = 1L;

    // Persistent Fields:
    @Id
    @GeneratedValue
    Long id;
    private String name;

    // Constructors:
    public Guest() {
    }

    public Guest(String name) {
        this.name = name;
    }

    // String Representation:
    @Override
    public String toString() {
        return name;
    }
}
