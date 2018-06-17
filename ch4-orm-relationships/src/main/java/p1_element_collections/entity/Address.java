package p1_element_collections.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/*
 * @Embeddable: Klasse repräsentiert keine Table sondern einen Ausschnitt eines
 * anderen Entitys.
 *
 * Kein @Entity
 * Keine @Id
 * Kein Eintrag in persistence.xml??
 */

@Embeddable
public class Address {
    private String street;
    private String city;
    @Column(name = "ZIP_CODE")
    private String zip;

    public Address() {
    }

    public Address(String street, String city, String zip) {
        this.street = street;
        this.city = city;
        this.zip = zip;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", zip='" + zip + '\'' +
                '}';
    }
}
