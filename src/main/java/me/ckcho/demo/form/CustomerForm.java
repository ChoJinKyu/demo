package me.ckcho.demo.form;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import me.ckcho.demo.code.CustTypeCode;

@Getter @Setter
public class CustomerForm {
    
    @NotBlank(message = "고객명은 필수 입니다.")
    private String name;

    @NotNull(message = "회선수량은 필수 입니다.")
    private Long qty;

    @NotBlank(message = "City는 필수 입니다.")
    private String city;

    @NotBlank(message = "Street는 필수 입니다.")
    private String street;

    @NotBlank(message = "Zipcode는 필수 입니다.")
    private String zipcode;

    @NotNull(message = "고객 구분은 필수 입니다.")
    @Enumerated(EnumType.STRING)
    private CustTypeCode custTypeCode;


    private String corporateNo;
    private String ceoName;
    private String telNo;
    private String securityNo;
    private String mobileNo;

    private long id;

}