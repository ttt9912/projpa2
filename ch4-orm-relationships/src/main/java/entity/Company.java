package entity;

import javax.persistence.*;

/*
 * @AttributeOverride: optional. Name, den eine Column in der DB haben soll, wenn nicht default.
 * @AttributeOverrides: fasst mehrere @AttributeOverride zusammen
 */

@Entity
public class Company {

    @Id
    private String name;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "TOWN")),
            @AttributeOverride(name = "zip", column = @Column(name = "POSTAL_CODE"))
    })
    private Address address;

    public Company() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}
