package com.github.dkijkuit.quarkus.customer;

import com.example.customerservice.CustomerService;
import com.example.customerservice.GetAllCustomersResponse;
import io.quarkus.runtime.annotations.RegisterForReflection;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ApplicationScoped
@Named("customerServiceClient")
@RegisterForReflection
public class CustomerServiceClient {
    @Inject
    CustomerService customerService;

    public GetAllCustomersResponse getAllCustomers(){
        return customerService.getAllCustomers();
    }
}
