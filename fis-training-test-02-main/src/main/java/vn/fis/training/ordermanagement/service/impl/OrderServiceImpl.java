package vn.fis.training.ordermanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.fis.training.ordermanagement.domain.Customer;
import vn.fis.training.ordermanagement.domain.Order;
import vn.fis.training.ordermanagement.domain.OrderItem;
import vn.fis.training.ordermanagement.domain.OrderStatus;
import vn.fis.training.ordermanagement.repository.OrderRepository;
import vn.fis.training.ordermanagement.service.OrderService;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order createOrder(Order order) {
        orderRepository.save(order);
        return order;
    }

    @Override
    public Order addOrderItem(Long orderId,OrderItem orderItem) {
        Optional<Order> order= orderRepository.findById(orderId);
        order.get().getOrderItems().add(orderItem);
        Double amount;
        amount= order.get().getTotalAmount() + (orderItem.getAmount() * orderItem.getQuantity());
        order.get().setTotalAmount(amount );
        orderRepository.save(order.get());
        return order.get();
    }

    @Override
    public Order removeOrderItem(Long orderId,OrderItem orderItem) {
        Optional<Order> order= orderRepository.findById(orderId);
        order.get().getOrderItems().remove(orderItem);
        Double amount;
        amount= order.get().getTotalAmount() - (orderItem.getAmount() * orderItem.getQuantity());
        order.get().setTotalAmount(amount );
        orderRepository.save(order.get());
        return order.get();
    }

    @Override
    public Order updateOrderStatus(Order order,OrderStatus orderStatus) {
        order.setStatus(orderStatus);
        orderRepository.save(order);
        return order;
    }

    @Override
    public List<Order> findOrdersBetween(LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        return orderRepository.findByOrderDateTimeBetween(fromDateTime, toDateTime);
    }

    @Override
    public List<Order> findWaitingApprovalOrders() {
        List<Order> orderList=orderRepository.findAll().stream().filter(order ->
                OrderStatus.WAITING_APPROVAL.equals(order.getStatus())).collect(Collectors.toList());
        return orderList;
    }

    @Override
    public List<Order> findOrdersByOrderStatus(OrderStatus orderStatus) {
        List<Order> orderList=orderRepository.findAll().stream().filter(order -> orderStatus.equals(order.getStatus()))
                .collect(Collectors.toList());
        return orderList;
    }

    @Override
    public List<Order> findOrdersByCustomer(Customer customer) {
        List<Order> orderList=orderRepository.findAll().stream().filter(order -> customer.getId().equals(order.getId()))
                .collect(Collectors.toList());
        return orderList;
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findOrderById(Long orderId) {
        Optional<Order> order= orderRepository.findAll().stream().filter(order1 -> orderId.equals(order1.getId())).findFirst();
        if(order.isPresent()) return order.get();
        return null;
    }
}
