package me.ckcho.demo.repository;

import me.ckcho.demo.code.CustTypeCode;
import me.ckcho.demo.domain.Customer;
import me.ckcho.demo.form.CustomerSearch;

import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import java.util.List;

@Repository
@Slf4j
public class CustomerRepository {

    @PersistenceContext
    private EntityManager em;

    public Long insert(Customer customer) {

        em.persist(customer);

        return customer.getId();

    }

    public Customer searchSingle(Long id) {

        return em.find(Customer.class, id);

    }

    public List<Customer> searchAll() {

        String qeuString = "SELECT C from Customer C";

        return em.createQuery(qeuString, Customer.class).getResultList();

    }

    public List<Customer> searchByCondition(CustomerSearch customerSearch) {

        String name = customerSearch.getName();
        CustTypeCode type = customerSearch.getCustTypeCode();

        log.info("################### name: " + name );
        log.info("################### type: " + type );

        String qeuString = "select C from Customer C where 1=1 ";
        if (name != null && !"".equals(name)) {
            qeuString = qeuString + " and CUST_NM like '%' || :cust_nm || '%'";
        }

        if (type != null) {
            qeuString = qeuString + " and CUST_TYPE_CODE = :cust_type_code";
        }

        TypedQuery<Customer> query = em.createQuery(qeuString, Customer.class);

        if (name != null && !"".equals(name)) {
            query.setParameter("cust_nm", name);
        }

        if (type != null) {
            query.setParameter("cust_type_code", type.name());
        }

        return query.getResultList();

    }

}