import javax.persistence.*;

/*
 * Diese Klasse verwendet mixed access. Per default
 * werden die Variablen mit der DB gemapped (Field Access).
 * Ausnahme: phoneNum verwendet Property Access, da dessen Daten nicht
 * 1:1 mit denen der DB übereinstimmen.
 */

@Entity
@Access(AccessType.FIELD)
public class Employee {

    private static final String LOCAL_AREA_CODE = "613";

    @Id
    private int id;
    private String name;
    private long salary;
    @Transient
    private String phoneNum; // soll ignoriert werden, damit es nicht doppelt persistiert wird

    public Employee() {
    }

    public Employee(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    @Column(name = "PHONE")
    @Access(AccessType.PROPERTY)
    public String getPhoneNum() {
        if (phoneNum.length() == 10)
            return phoneNum;
        else
            return LOCAL_AREA_CODE + phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        if (phoneNum.startsWith(LOCAL_AREA_CODE))
            this.phoneNum = phoneNum.substring(3);
        else
            this.phoneNum = phoneNum;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", phoneNum='" + phoneNum + '\'' +
                '}';
    }
}
