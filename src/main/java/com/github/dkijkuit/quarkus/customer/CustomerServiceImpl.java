package com.github.dkijkuit.quarkus.customer;

import com.example.customerservice.Customer;
import com.example.customerservice.CustomerService;
import com.example.customerservice.GetAllCustomersResponse;
import com.example.customerservice.SaveCustomer;
import org.apache.camel.ProducerTemplate;

import javax.inject.Inject;
import java.util.List;

/**
 * Actual implementation class which connects the SOAP message to the Camel routes and back.
 */

public class CustomerServiceImpl implements CustomerService {
    @Inject
    ProducerTemplate producerTemplate;

    @Override
    public void saveCustomer(SaveCustomer parameters) {
        producerTemplate.sendBody("direct:save-customer", parameters.getCustomer());
    }

    @Override
    public List<Customer> getCustomersByName(String name) {
        return producerTemplate.requestBody("direct:get-customers-by-name", name, List.class);
    }

    @Override
    public GetAllCustomersResponse getAllCustomers() {
        return producerTemplate.requestBody("direct:get-all-customers", "", GetAllCustomersResponse.class);
    }
}
