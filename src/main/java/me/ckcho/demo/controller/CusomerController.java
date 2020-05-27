package me.ckcho.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.ckcho.demo.domain.Customer;
import me.ckcho.demo.form.CustomerForm;
import me.ckcho.demo.form.CustomerSearch;
import me.ckcho.demo.service.CustomerService;
import me.ckcho.demo.util.LogWriteExeTime;

@Controller
@Slf4j
@RequiredArgsConstructor
public class CusomerController {
    
    private final CustomerService customerService;

    @GetMapping("/customer/single")
    @LogWriteExeTime
    public String openCustomerForm(Model model){
        model.addAttribute("customerForm", new CustomerForm());
        return "customer/customerSingle";
    }

    @GetMapping("/customer/{id}/{custTypeCode}/update")
    @LogWriteExeTime
    public String openCustomerUpdateForm(@PathVariable Long id, @PathVariable String custTypeCode, Model model){       

        model.addAttribute("customerForm", customerService.searchCustomerSingleForUpdate(id, custTypeCode));
        return "customer/customerSingle";
    }

    @PostMapping("/customer/save")
    @LogWriteExeTime
    public String saveCustomer(@Valid CustomerForm form, BindingResult result, Model model){
        log.info("########################## saveCustomer #######################");
        log.info(" id : " + form.getId());
        log.info("########################## saveCustomer #######################");

        if(result.hasErrors()){
            return "customer/customerSingle";
        }

        customerService.saveCustomer(form);

        return "redirect:/customer/searchlist";
    }

    @GetMapping("/customer/list")
    @LogWriteExeTime
    public String openCustomerListGet(Model model){
        model.addAttribute("customerSearch", new CustomerSearch());
        return "customer/customerList";
    }

    @PostMapping("/customer/list")
    @LogWriteExeTime
    public String openCustomerListPost(Model model){
        model.addAttribute("customerSearch", new CustomerSearch());
        return "customer/customerList";
    }

    @GetMapping("/customer/searchlist")
    @LogWriteExeTime
    public String searchCustomerList(@ModelAttribute("customerSearch") CustomerSearch customerSearch, Model model){
        List<Customer> customers = customerService.searchCustomerByCondition(customerSearch);
        
        model.addAttribute("customers", customers);

        return "customer/customerList";
    }

    @GetMapping("/customer/searchlistAfterId")
    @LogWriteExeTime
    public String searchCustomerListAfter(@ModelAttribute("customerSearch") CustomerSearch customerSearch, Model model){
        List<Customer> customers = customerService.searchCustomerByCondition(customerSearch);
        model.addAttribute("customers", customers);

        return "customer/customerList";
    }    
}