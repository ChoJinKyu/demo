package me.ckcho.demo.controller;

//import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {
    
    @Autowired
    MessageSource messageSource;

    @RequestMapping("/")
    public String home(){
        log.info("############## HomeController home ################");
        //log.info("############## "+ messageSource.getMessage("notempty", null, Locale.KOREA));

        return "home";
    }

    @RequestMapping("/main")
    public String main(){
        log.info("############## HomeController main ################");
        return "main";
    }

}