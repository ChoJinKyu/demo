package me.ckcho.demo.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("PERSON")
@Getter @Setter
public class PerCustomer extends Customer{
    
    @Column(name = "security_no")
    private  String securityNo;

    @Column(name = "mobile_no")
    private  String mobileNo;
}