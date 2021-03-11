package com.github.dkijkuit.quarkus.customer;

import com.example.customerservice.Customer;
import io.quarkus.runtime.annotations.RegisterForReflection;
import org.apache.camel.Body;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Just a simple wrapper, faking a database
 */
@RegisterForReflection
public class CustomersHolder extends ArrayList<Customer> {
    public CustomersHolder(Collection c) {
        super(c);
    }

    public void saveCustomer(@Body Customer customer){
        this.add(customer);
    }

    public List<Customer> getAllCustomers(){
        return this;
    }
}
