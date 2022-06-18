package vn.fis.training.ordermanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.fis.training.ordermanagement.domain.Customer;
import vn.fis.training.ordermanagement.repository.CustomerRepository;
import vn.fis.training.ordermanagement.service.CustomerService;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(Customer customer) {
        customerRepository.save(customer);
        return customer;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        customerRepository.save(customer);
        return customer;
    }

    @Override
    public void deleteCustomerById(Long customerId) {
    customerRepository.deleteById(customerId);
    }

    @Override
    public List<Customer> findAll() {

        return customerRepository.findAll();
    }

    @Override
    public Customer findByMobileNumber(String mobileNumber) {
        Optional<Customer> customer= customerRepository.findAll().stream().filter(c-> mobileNumber.equals(c.getMobile())).findFirst();
        if(customer.isPresent()) return customer.get();
        return null;
    }


}
