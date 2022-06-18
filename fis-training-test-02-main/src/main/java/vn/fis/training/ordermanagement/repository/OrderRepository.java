package vn.fis.training.ordermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.fis.training.ordermanagement.domain.Customer;
import vn.fis.training.ordermanagement.domain.Order;

import java.time.LocalDateTime;
import java.util.List;

@Repository

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByOrderDateTimeBetween(LocalDateTime fromDateTime, LocalDateTime toDateTime);
}
