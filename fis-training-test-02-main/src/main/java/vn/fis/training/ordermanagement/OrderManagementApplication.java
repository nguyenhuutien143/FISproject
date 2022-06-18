package vn.fis.training.ordermanagement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import vn.fis.training.ordermanagement.domain.Order;
import vn.fis.training.ordermanagement.domain.OrderStatus;
import vn.fis.training.ordermanagement.domain.Product;
import vn.fis.training.ordermanagement.repository.CustomerRepository;
import vn.fis.training.ordermanagement.repository.ProductRepository;
import vn.fis.training.ordermanagement.service.CustomerService;
import vn.fis.training.ordermanagement.service.OrderService;

import java.time.LocalDateTime;

@SpringBootApplication
public class OrderManagementApplication {
	private static final Logger log = LoggerFactory.getLogger(OrderManagementApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(OrderManagementApplication.class, args);
	}


	@Autowired
	private OrderService orderService;

	@Autowired
	private CustomerService customerService;
	private ProductRepository productRepository;
	CustomerRepository customerRepository;

	@Bean
	CommandLineRunner commandLineRunner() {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				//em có viết spring boot test ở bên package test, a vào đấy xem thử nhé
				log.info("hello world");
				log.info("delete");
			 	log.info("Total order with wating status : {} order(s)", orderService.findOrdersByOrderStatus(OrderStatus.WAITING_APPROVAL).size());
				log.info(" tong so khach hang : {}",customerService.findAll().size());
				log.info("khach hang sdt 0965154268 la: {}", customerService.findByMobileNumber("0965154268"));
				Order order= new Order();
				order.setStatus(OrderStatus.WAITING_APPROVAL);
				log.info("thêm order :\n {}",orderService.createOrder(order).toString());
				log.info("findWaitingApprovalOrders : {}",orderService.findWaitingApprovalOrders().size());
				log.info("findOrdersByOrderStatus: CREATED : {} ",orderService.findOrdersByOrderStatus(OrderStatus.CREATED).size());
				log.info("findOrdersByCustomer (Tien): {}",orderService.findOrdersByCustomer(customerService.findByMobileNumber("0965154268")).size());
				log.info("order từ ngày 15/6-19/6:{}",orderService.findOrdersBetween(LocalDateTime.of(2022,06,15,12,25,00),LocalDateTime.of(2022,06,19,12,25,00)).size());
			}
		};
	}



}
