package com.github.dkijkuit.quarkus.customer;

import com.example.customerservice.Customer;
import com.example.customerservice.GetAllCustomersResponse;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Simple Camel routes just for demonstrating how to connect the incoming SOAP message to camel.
 */

@ApplicationScoped
public class CustomerRoute extends RouteBuilder {
    @Inject
    @Named("customersHolderBean")
    CustomersHolder customersHolder;

    @Override
    public void configure() throws Exception {
        from("direct:get-all-customers")
                .routeId("get-all-customers")
                .process(this::getAllCustomersResponse)
                .end();

        from("direct:save-customer")
                .bean("customersHolderBean", "saveCustomer")
                .end();

        from("direct:get-customers-by-name")
                .process(this::filterCustomersByName)
                .end();

        /**
         * This webservice client route calls it's own webservice as if it was hosted somewhere else
         */
        from("timer://customertimer?fixedRate=true&period=5000")
                .bean("customerServiceClient", "getAllCustomers")
                .setBody(simple("${body.getReturn}"))
                .split(body())
                .log("Timer test route for customer service, current customer list: ${body.name}")
                .end();
    }

    private void filterCustomersByName(Exchange exchange) {
        String nameFilter = exchange.getMessage().getBody(String.class);
        final List<Customer> customerList = this.customersHolder.stream()
                .filter(customer -> customer.getName().toLowerCase().contains(nameFilter.toLowerCase()))
                .collect(Collectors.toList());
        exchange.getMessage().setBody(customerList);
    }

    void getAllCustomersResponse(Exchange exchange) {
        GetAllCustomersResponse allCustomersResponse = new GetAllCustomersResponse();
        allCustomersResponse.getReturn().addAll(customersHolder);
        exchange.getMessage().setBody(allCustomersResponse);
    }
}
