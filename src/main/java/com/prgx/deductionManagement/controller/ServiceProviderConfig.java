package com.prgx.deductionManagement.controller;

import com.prgx.deductionManagement.model.ServiceProviderConfigModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/ServiceProviderConfig")
public class ServiceProviderConfig {
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ServiceProviderConfigModel getServiceProviderConfig(){
        return new ServiceProviderConfigModel();
    }
}
