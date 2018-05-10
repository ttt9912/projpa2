package p1_basic_entity_annotations;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PERS")
@Access(AccessType.FIELD) // mixed access
public class Person {

    private static final String LOCAL_AREA_CODE = "613";

    @Id
    private int id;
    private String name;
    private long salary;
    @Transient
    private String phoneNum; // property access: soll nicht doppelt persistiert werden
    @Column(name = "COMM")
    private String comment;
    @Basic(fetch = FetchType.LAZY)  // only fetched when requred
    @Lob                            // Lob: marker annotation for a large object
    private transient byte[] picture;   // not persistet
    @Enumerated(EnumType.STRING)    // save Enum by its value instead of ordinal
    private PersonType type;
    private java.sql.Date dateOfBirth;
    @Temporal(TemporalType.DATE)    // non-sql date types need to be mapped to sql types
    private Date startDate;


    public Person() {
    }

    public Person(int id) {
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public PersonType getType() {
        return type;
    }

    public void setType(PersonType type) {
        this.type = type;
    }

    public java.sql.Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(java.sql.Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "p1_basic_entity_annotations.Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", phoneNum='" + phoneNum + '\'' +
                '}';
    }
}