package jpabook.jpashop.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class Address {

    @Column(length = 10)
    private String city;
    @Column(length = 20)
    private String street;
    @Column(length = 5)
    private String zipcode;

    private String fullAddress() {
        return getCity() + " " + getStreet() + " " + getZipcode();
    }

    public boolean isValid() {

        return false;
    }

    public String getCity() {
        return city;
    }

    private Address setCity(String city) {
        this.city = city;
        return this;
    }

    public String getStreet() {
        return street;
    }

    private Address setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getZipcode() {
        return zipcode;
    }

    private Address setZipcode(String zipcode) {
        this.zipcode = zipcode;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(getCity(), address.getCity()) && Objects.equals(getStreet(), address.getStreet()) && Objects.equals(getZipcode(), address.getZipcode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCity(), getStreet(), getZipcode());
    }
}
