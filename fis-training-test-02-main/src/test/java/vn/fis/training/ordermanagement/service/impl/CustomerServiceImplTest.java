package vn.fis.training.ordermanagement.service.impl;

//import org.junit.jupiter.api.Test;

import org.assertj.core.internal.IterableElementComparisonStrategy;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.fis.training.ordermanagement.OrderManagementApplication;
import vn.fis.training.ordermanagement.domain.Customer;
import vn.fis.training.ordermanagement.repository.CustomerRepository;
import vn.fis.training.ordermanagement.repository.OrderRepository;
import vn.fis.training.ordermanagement.service.CustomerService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CustomerServiceImplTest {

    @Autowired
    private  CustomerService customerService;
    private static final Logger log = LoggerFactory.getLogger(CustomerServiceImplTest.class);




//    data khi test: gồm 2 customer có sẵn
    @Test
    @Order(1)
    void createCustomer() {
        //A1
        Customer c1= new Customer();
        c1.setId(3L);
        c1.setAddress("Da Nang");
        c1.setMobile("090909009");
        c1.setName("Thuat");
        //A2
        customerService.createCustomer(c1);
        //A3
        assertEquals(3,customerService.findAll().size());
    }

    @Test
    @Order(2)
    void updateCustomer() {
        //A1
        Customer c1= new Customer();
        c1.setId(4L);
        c1.setAddress("Hai phong");
        c1.setMobile("090909009");
        c1.setName("Thuat");
        //A2
        customerService.updateCustomer(c1);
        //A3
        assertEquals(4, customerService.findAll().size());
    }

    @Test
    @Order(3)
    void deleteCustomerById() {
        //A2
        customerService.deleteCustomerById(4L);
        //A3
        assertEquals(3,customerService.findAll().size());
    }

    @Test
    @Order(4)
    void findAll() {
        assertEquals(3,customerService.findAll().size());

    }



    @Test
    @Order(5)
    void findByMobileNumber() {
        //A2
        Customer c1=customerService.findByMobileNumber("0965154268");
        //A3
        assertEquals(1L, c1.getId());
    }

}