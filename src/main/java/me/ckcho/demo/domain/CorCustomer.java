package me.ckcho.demo.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("COMPANY")
@Getter @Setter
public class CorCustomer extends Customer{
    
    @Column(name = "corporate_no")
    private String corporateNo;

    @Column(name = "ceo_name")
    private String ceoName;

    @Column(name = "tel_no")
    private String telNo;
}