package pl.javastart.product;

import pl.javastart.order.Order;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
public class Product implements Serializable {
    private static final long serialUID = 123L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private Long id;
    @Column(length = 45,name = "name",nullable = false)
    private String productName;
    @Column(nullable = false)
    private Double price;
    @Column(length = 256)
    private String details;
    @Column(name = "avalaible_quantity",nullable = false)
    private Integer avalaibleQuantity;
    @ManyToMany
    private List<Order> orders = new ArrayList<>();

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Product() {
    }

    public Product(String productName, Double price, String details, Integer avalaibleQuantity) {
        this.productName = productName;
        this.price = price;
        this.details = details;
        this.avalaibleQuantity = avalaibleQuantity;
    }

    public Integer getAvalaibleQuantity() {
        return avalaibleQuantity;
    }

    public void setAvalaibleQuantity(Integer avalaibleQuantity) {
        this.avalaibleQuantity = avalaibleQuantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "Product [id=" + id
                + ", name=" + productName
                + ", price=" + price
                + ", details=" + details + "]";
    }
}
