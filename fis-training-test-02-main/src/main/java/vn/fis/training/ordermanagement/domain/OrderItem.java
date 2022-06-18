package vn.fis.training.ordermanagement.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="tbl_order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="order_id", nullable = false)
    private Order order;

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", order=" + order +
                ", product=" + product +
                ", quantity=" + quantity +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(id, orderItem.id) && Objects.equals(order, orderItem.order) && Objects.equals(product, orderItem.product) && Objects.equals(quantity, orderItem.quantity) && Objects.equals(amount, orderItem.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, order, product, quantity, amount);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    @Column(name="quantity")
    private Integer quantity;

    @Column(name="amount")
    private Double amount;


}
