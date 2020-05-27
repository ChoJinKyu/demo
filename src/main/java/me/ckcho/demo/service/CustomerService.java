package me.ckcho.demo.service;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.ckcho.demo.code.CustTypeCode;
import me.ckcho.demo.domain.Address;
import me.ckcho.demo.domain.CorCustomer;
import me.ckcho.demo.domain.Customer;
import me.ckcho.demo.domain.PerCustomer;
import me.ckcho.demo.form.CustomerForm;
import me.ckcho.demo.form.CustomerSearch;
import me.ckcho.demo.repository.CustomerRepository;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    // 고객저장
    @Transactional
    public Long saveCustomer(CustomerForm form) {
        log.info("########################## saveCustomer #######################");
        log.info(" id : " + form.getId());
        log.info("########################## saveCustomer #######################");
        if(Objects.isNull(form.getId()) || form.getId() == 0){
            return insertCustomer(form);
        }else{
            return updateCustomer(form);
        }
        
    }

    // 고객수정   
    @Transactional
    public Long updateCustomer(CustomerForm form) {
        // thymeleaf bug : radio disable 하면 값이 안 넘어 옴..
        //String custTypeCode = form.getCustTypeCode();

        Customer cust = customerRepository.searchSingle(form.getId());
        CustTypeCode custTypeCode = cust.getCustTypeCode();

        switch(custTypeCode){
            case PERSON :
                return updateCustomerPer(form, cust);
            case COMPANY : 
                return updateCustomerCor(form, cust);
            default :
                return updateCustomerDefault(form, cust);
        }
        
    }

    private Long updateCustomerDefault(CustomerForm form, Customer cust){
        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());
        
        //Customer cust = customerRepository.searchSingle(form.getId());
        cust.setName(form.getName());
        cust.setQty(form.getQty());
        cust.setAddress(address);

        log.info("############# updateCustomerDefault ##############");
        return form.getId();
    }

    private Long updateCustomerPer(CustomerForm form, Customer customer){
        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

        //PerCustomer cust = (PerCustomer)customerRepository.searchSingle(form.getId());
        PerCustomer cust = (PerCustomer)customer;

        cust.setName(form.getName());
        cust.setQty(form.getQty());        
        cust.setMobileNo(form.getMobileNo());
        cust.setSecurityNo(form.getSecurityNo());
        cust.setAddress(address);

        log.info("################## updateCustomerPer ######################");
        return form.getId();
    }

    private Long updateCustomerCor(CustomerForm form, Customer customer){
        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

        //CorCustomer cust = (CorCustomer)customerRepository.searchSingle(form.getId());
        CorCustomer cust = (CorCustomer)customer;

        cust.setName(form.getName());
        cust.setQty(form.getQty());        
        cust.setCeoName(form.getCeoName());
        cust.setTelNo(form.getTelNo());
        cust.setCorporateNo(form.getCorporateNo());
        cust.setAddress(address);

        log.info("############# updateCustomerCor ##############");
        return form.getId();
    }

    // 고객등록    
    @Transactional
    public Long insertCustomer(CustomerForm form) {

        CustTypeCode custTypeCode = form.getCustTypeCode();

        switch(custTypeCode){
            case PERSON :
                return insertCustomerPer(form);
            case COMPANY : 
                return InsertCustomerCor(form);
            default :
                return insertCustomerDefault(form);
        }
        
    }

    private Long insertCustomerDefault(CustomerForm form){
        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

        Customer cust = new Customer();
        cust.setName(form.getName());
        cust.setQty(form.getQty());
        cust.setAddress(address);

        log.info("############# insertCustomerDefault ##############");
        return customerRepository.insert(cust);
    }

    private Long insertCustomerPer(CustomerForm form){
        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

        PerCustomer cust = new PerCustomer();
        cust.setName(form.getName());
        cust.setQty(form.getQty());        
        cust.setMobileNo(form.getMobileNo());
        cust.setSecurityNo(form.getSecurityNo());
        cust.setAddress(address);

        log.info("################## insertCustomerPer ######################");
        return customerRepository.insert(cust);
    }

    private Long InsertCustomerCor(CustomerForm form){
        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

        CorCustomer cust = new CorCustomer();

        cust.setName(form.getName());
        cust.setQty(form.getQty());        
        cust.setCeoName(form.getCeoName());
        cust.setTelNo(form.getTelNo());
        cust.setCorporateNo(form.getCorporateNo());
        cust.setAddress(address);

        log.info("############# InsertCustomerCor ##############");
        return customerRepository.insert(cust);
    }


    // 고객 데이터 Check
    public void validateDuplicateCustomer(CustomerForm form) {

        // 필수값 check
        // List<Customer> findCustomers = customerRepository.find

    }

    // 고객 전체 조회
    public List<Customer> searchCustomerAll() {
        return customerRepository.searchAll();
    }

    // 고객 단건 조회
    public Customer searchCustomerSingle(Long id) {
        return customerRepository.searchSingle(id);
    }

    // 조회 조건으로 고객 조회
    public List<Customer> searchCustomerByCondition(CustomerSearch customerSearch) {
        return customerRepository.searchByCondition(customerSearch);
    }

    // 업데이트 용 고객 단건 조회
    public CustomerForm searchCustomerSingleForUpdate(Long id, String custTypeCode) {
        switch(custTypeCode){
            case "PERSON" :
                return searchCustomerSingleForUpdatePer(id);
            case "COMPANY" : 
                return searchCustomerSingleForUpdateDeCor(id);
            default :
                return searchCustomerSingleForUpdateDefault(id);
        }
    }

    private CustomerForm searchCustomerSingleForUpdateDefault(Long id){
        CustomerForm form  =  new CustomerForm();
        Customer cust = customerRepository.searchSingle(id);
        form.setId(cust.getId());
        form.setName(cust.getName());
        form.setQty(cust.getQty());
        form.setCustTypeCode(cust.getCustTypeCode());
        form.setCity(cust.getAddress().getCity());
        form.setStreet(cust.getAddress().getStreet());
        form.setZipcode(cust.getAddress().getZipcode());
        return form;
    }
 
    private CustomerForm searchCustomerSingleForUpdatePer(Long id){
        CustomerForm form  =  new CustomerForm();
        PerCustomer cust = (PerCustomer)customerRepository.searchSingle(id);
        form.setId(cust.getId());
        form.setName(cust.getName());
        form.setQty(cust.getQty());
        form.setCustTypeCode(cust.getCustTypeCode());
        form.setCity(cust.getAddress().getCity());
        form.setStreet(cust.getAddress().getStreet());
        form.setZipcode(cust.getAddress().getZipcode());
        form.setSecurityNo(cust.getSecurityNo());
        form.setMobileNo(cust.getMobileNo());
        return form;
    }
    
    private CustomerForm searchCustomerSingleForUpdateDeCor(Long id){
        CustomerForm form  =  new CustomerForm();
        CorCustomer cust = (CorCustomer)customerRepository.searchSingle(id);
        form.setId(cust.getId());
        form.setName(cust.getName());
        form.setQty(cust.getQty());
        form.setCustTypeCode(cust.getCustTypeCode());
        form.setCity(cust.getAddress().getCity());
        form.setStreet(cust.getAddress().getStreet());
        form.setZipcode(cust.getAddress().getZipcode());
        form.setCorporateNo(cust.getCorporateNo());
        form.setCeoName(cust.getCeoName());
        form.setTelNo(cust.getTelNo());
        return form;
    }

}