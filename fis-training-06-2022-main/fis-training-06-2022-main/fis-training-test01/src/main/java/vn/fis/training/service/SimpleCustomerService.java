package vn.fis.training.service;

import vn.fis.training.domain.Customer;
import vn.fis.training.exception.CustomerNotFoundException;
import vn.fis.training.exception.DuplicateCustomerException;
import vn.fis.training.store.InMemoryCustomerStore;
import vn.fis.training.exception.*;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

import static jdk.internal.joptsimple.internal.Strings.isNullOrEmpty;

public class SimpleCustomerService implements CustomerService {

    private InMemoryCustomerStore customerStore;

    @Override
    public Customer findById(String id) {
        //TODO: Implement method tho dung dac ta cua CustomerService interface
        List<Customer> lst= customerStore.findAll();
        if (id == null || id == "")
            throw new IllegalArgumentException();
        else {
            Optional<Customer> optionalIsbn = lst.stream()
                    .filter(e -> e.getId().equals(id))
                    .findFirst();
            if (optionalIsbn.isPresent()) return optionalIsbn.get();
            else
                throw new CustomerNotFoundException("Customer not found");

        }


    }

    @Override
    public Customer createCustomer(Customer customer) {
        //TODO: Implement method tho dung dac ta cua CustomerService interface
        checkData(customer);
        List<Customer> lst= customerStore.findAll();
        if (lst.stream().filter(e -> e.getMobile().equals(customer.getMobile())).count()>=1)
        {
            customerStore.insertOrUpdate(customer);
            throw new DuplicateCustomerException( customer, "customer nay da ton tai");
        };
        if(lst.stream().filter(e -> e.getMobile().equals(customer.getMobile())).count()==0)
            throw new CustomerNotFoundException("customer not found");
        return  customer;
    }


    @Override
    public Customer updateCustomer(Customer customer) {
        //TODO: Implement method tho dung dac ta cua CustomerService interface
        checkData(customer);
        this.findById(customer.getId());
        return customerStore.insertOrUpdate(customer);
    }

    @Override
    public void deleteCustomerById(String id) throws InvalidCustomerStatusException  {
        //TODO: Implement method tho dung dac ta cua CustomerService interface
        List<Customer> lst= customerStore.findAll();
        Customer cus = this.findById(id);
        if(cus.getStatus().equals("ACTIVE")) {
            throw new InvalidCustomerStatusException(cus, "customer dang active");
        }
        else customerStore.deleteById(id);
    }

    @Override
    public List<Customer> findAllOrderByNameAsc() {
        //TODO: Implement method tho dung dac ta cua CustomerService interface
        List<Customer> lst= customerStore.findAll();
        lst.stream().sorted(Comparator.comparing(Customer::getName)).collect(Collectors.toList());
        if (lst.size()==0) return Collections.emptyList();
        else return lst;
    }

    @Override
    public List<Customer> findAllOrderByNameLimit(int limit) {
        //TODO: Implement method tho dung dac ta cua CustomerService interface
        List<Customer> lst= customerStore.findAll();
        lst.stream().sorted(Comparator.comparing(Customer::getName));
        return lst.stream().limit(limit).collect(Collectors.toList());
    }

    @Override
    public List<Customer> findAllCustomerByNameLikeOrderByNameAsc(String custName, String limit) {
        //TODO: Implement method tho dung dac ta cua CustomerService interface
        List<Customer> lst= customerStore.findAll();
        int number = Integer.parseInt(limit);
        lst.stream().filter(e->e.getName().contains(custName)).limit(number);
        if (lst.size()==0) return Collections.emptyList();
        else return lst;
    }

    @Override
    public List<SummaryCustomerByAgeDTO> summaryCustomerByAgeOrderByAgeDesc() {
        //TODO: Implement method tho dung dac ta cua CustomerService interface

        List<SummaryCustomerByAgeDTO> listSummaryCustomerByAgeDTO = new ArrayList<>();
        Comparator<SummaryCustomerByAgeDTO> compaSumCusByAge = Comparator.comparingInt(t->t.getAge());
        List<SummaryCustomerByAgeDTO> lst = listSummaryCustomerByAgeDTO.stream().sorted(compaSumCusByAge.reversed())
                                                                        .collect(Collectors.toList());
        if(lst.size()==0) return Collections.emptyList();
        else return lst;
    }
    public void checkData(Customer cus){
        if(isNullOrEmpty(cus.getName())){
            throw new DuplicateCustomerException(cus,"Customer name not valid");
        }
        if(isNullOrEmpty(cus.getMobile())){
            throw new DuplicateCustomerException(cus,"Customer mobile not valid");
        }
        if(isNullOrEmpty(cus.getBirthDay().toString())){
            throw new DuplicateCustomerException(cus,"Customer DOB not valid");
        }
        if(isNullOrEmpty(cus.getStatus().toString())){
            throw new DuplicateCustomerException(cus,"Customer Status not valid");
        }
    }

}
