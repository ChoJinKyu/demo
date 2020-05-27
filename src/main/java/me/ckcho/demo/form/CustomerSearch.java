package me.ckcho.demo.form;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Getter;
import lombok.Setter;
import me.ckcho.demo.code.CustTypeCode;

@Getter @Setter
public class CustomerSearch {
    
    private String name;

    @Enumerated(EnumType.STRING)
    private CustTypeCode custTypeCode;
    private Long id;

}