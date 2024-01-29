package hellojpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Inheritance(strategy =  InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn    // DTYPE이 생기고 ENTITY 명이 들어감
public abstract class Item {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private int price;
}


