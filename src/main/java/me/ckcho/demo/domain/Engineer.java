package me.ckcho.demo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Engineer {
    
    @Id @GeneratedValue
    @Column(name = "engineer_id")
    private Long id;

    @Column(name = "engineer_name")
    private String name;

    @Column(name = "engineer_photo_name")
    private String photoName;

    @Column
    private String mobileNo;

    @Column
    private String regionCode;

    @Column
    private String availableYn;

    @OneToMany(mappedBy = "engineer")
    private List<Reservation> receipts = new ArrayList<>();    
}