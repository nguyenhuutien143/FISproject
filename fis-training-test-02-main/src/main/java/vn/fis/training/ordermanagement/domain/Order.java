package vn.fis.training.ordermanagement.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="tbl_order")
@NamedQuery(name = "Order.findByOrderDateTimeBetween",
        query = "select o from Order o where o.orderDateTime between ?1 and ?2")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="order_datetime")
    private LocalDateTime orderDateTime;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderDateTime=" + orderDateTime +
                ", customer=" + customer +
                ", orderItems=" + orderItems +
                ", totalAmount=" + totalAmount +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(orderDateTime, order.orderDateTime) && Objects.equals(customer, order.customer) && Objects.equals(orderItems, order.orderItems) && Objects.equals(totalAmount, order.totalAmount) && status == order.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderDateTime, customer, orderItems, totalAmount, status);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(LocalDateTime orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    @Column(name="total_amount")
    private Double totalAmount;

    /**
     * Bao gom cac trang thai duoc dinh nghia trong OrderStatus Class
     */
    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
