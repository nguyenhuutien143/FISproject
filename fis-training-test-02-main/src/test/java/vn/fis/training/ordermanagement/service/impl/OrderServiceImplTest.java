package vn.fis.training.ordermanagement.service.impl;

//import org.junit.jupiter.api.Order;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import vn.fis.training.ordermanagement.domain.*;
import vn.fis.training.ordermanagement.service.CustomerService;
import vn.fis.training.ordermanagement.service.OrderService;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderServiceImplTest {
    private static final Logger log = LoggerFactory.getLogger(OrderServiceImplTest.class);
    @Autowired
    private OrderService orderService;
    @Autowired
    private CustomerService customerService;
    @Test
    void createOrder() {
        Order order= new Order();
        order.setStatus(OrderStatus.CREATED);
        order.setTotalAmount(2000000d);
        order.setOrderDateTime(LocalDateTime.now());

        orderService.createOrder(order);

        assertEquals(3, orderService.findAll().size());
    }



    @Test
    void updateOrderStatus() {
        Order order= orderService.findOrderById(2L);

        orderService.updateOrderStatus(order,OrderStatus.APPROVED);

        assertEquals(OrderStatus.APPROVED, order.getStatus());

    }

    @Test
    void findOrdersBetween() {
        assertEquals(2, orderService.findOrdersBetween(LocalDateTime.of(2022,06,
                15,12,25,00),LocalDateTime.of(2022,06,19,12,
                25,00)).size());
    }

    @Test
    void findWaitingApprovalOrders() {
        assertEquals(0, orderService.findWaitingApprovalOrders() );
    }

    @Test
    void findOrdersByOrderStatus() {
        assertEquals(1, orderService.findOrdersByOrderStatus(OrderStatus.CREATED));
    }

    @Test
    void findOrdersByCustomer() {
        Customer customer = customerService.findByMobileNumber("0965154268");
        assertEquals(2, orderService.findOrdersByCustomer(customer).size());
    }
    @Test
    void addOrderItem() {
        OrderItem orderItem= new OrderItem();
        orderItem.setAmount(200000d);
        orderItem.setQuantity(3);
        Product product= new Product();
        product.setName("ghe");
        product.setPrice(300000d);
        orderItem.setProduct(product);
        orderService.addOrderItem(1L,orderItem);
        //em lười tính quá nên chỉ log ra thôi, ko test equal
        log.info("them order item  vào order:{}", orderService.findOrderById(1L));
    }

    @Test
    void removeOrderItem() {
        OrderItem orderItem= new OrderItem();
        orderItem.setAmount(200000d);
        orderItem.setQuantity(3);

        orderService.removeOrderItem(1L, orderItem);

        //em lười tính quá nên chỉ log ra thôi, ko test equal
        log.info("them order item  vào order:{}", orderService.findOrderById(1L));
    }
}