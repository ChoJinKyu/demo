package me.ckcho.demo.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Receipt {

    @Id @GeneratedValue
    @Column(name = "receipt_id")
    private Long id;    

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cust_id")
    private Customer customer;

    @Column(name = "receipt_date")
    private LocalDateTime date;

    @Column(name = "content")
    private String content;

    @Column(name = "state_code")
    private String stateCode;

    @Column(name = "processing_cost")
    private String cost;

    @OneToOne(mappedBy = "receipt")
    private Reservation reservation;
    

}