package me.ckcho.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import me.ckcho.demo.repository.CustomerRepository;
import me.ckcho.demo.service.CustomerService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CustomerServiceTest {
    
    @Autowired CustomerService customerService;
    @Autowired CustomerRepository customerRepository;

    @Test
    @Transactional
    //@Rollback(false)
    public void newCustomer(){

        //Customer customer = new Customer();
        //customer.setName("ckcho");


        //Long savedId = customerService.insertCustomer(customer);

        //assertEquals(customer, customerRepository.searchSingle(savedId));

    }


    
}