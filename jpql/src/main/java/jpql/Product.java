package jpql;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long id;
    private int price;
    private int stockAmount;

    public Long getId() {
        return id;
    }

    public Product setId(Long id) {
        this.id = id;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public Product setPrice(int price) {
        this.price = price;
        return this;
    }

    public int getStockAmount() {
        return stockAmount;
    }

    public Product setStockAmount(int stockAmount) {
        this.stockAmount = stockAmount;
        return this;
    }
}
