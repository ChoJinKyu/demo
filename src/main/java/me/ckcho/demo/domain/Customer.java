package me.ckcho.demo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;
import me.ckcho.demo.code.CustTypeCode;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "cust_type_code")
@Getter @Setter
public class Customer {
    
    @Id @GeneratedValue
    @Column(name = "cust_id")
    private Long id;

    @Column(name = "cust_nm")
    private String name;

    @Column(name = "line_qty")
    private Long qty;

    @Embedded
    private Address address;

    @Column(name = "cust_type_code", insertable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private CustTypeCode custTypeCode;

    @OneToMany(mappedBy = "customer")
    private List<Receipt> receipts = new ArrayList<>();

}

