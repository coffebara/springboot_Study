package jpabook.jpashop.domain;

import jakarta.persistence.*;

import static jakarta.persistence.FetchType.LAZY;

@Entity
public class Delivery{

    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private Address address;

    private DeliveryStatus status;

    @OneToOne(mappedBy = "delivery", fetch = LAZY)
    private Order order;

    public Long getId() {
        return id;
    }

    public Delivery setId(Long id) {
        this.id = id;
        return this;
    }

    public Address getAddress() {
        return address;
    }

    public Delivery setAddress(Address address) {
        this.address = address;
        return this;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    public Delivery setStatus(DeliveryStatus status) {
        this.status = status;
        return this;
    }

    public Order getOrder() {
        return order;
    }

    public Delivery setOrder(Order order) {
        this.order = order;
        return this;
    }
}
