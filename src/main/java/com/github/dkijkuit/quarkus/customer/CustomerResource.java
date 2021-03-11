package com.github.dkijkuit.quarkus.customer;

import com.example.customerservice.Customer;
import com.example.customerservice.CustomerType;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

public class CustomerResource {
    @Singleton
    @Named("customerListBean")
    public List<Customer> customerList() throws DatatypeConfigurationException {
        Customer customer = new Customer();
        customer.setName("Michael Knight");
        customer.setNumOrders(45);
        customer.setType(CustomerType.BUSINESS);
        customer.setRevenue(1001.20);
        customer.setTest(BigDecimal.valueOf(45.0));

        GregorianCalendar c = new GregorianCalendar();
        c.set(1952,7,17, 0,0,0);
        XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        xmlGregorianCalendar.setFractionalSecond(BigDecimal.ZERO);
        customer.setBirthDate(xmlGregorianCalendar);

        Customer customer2 = new Customer();
        customer2.setName("Dwight Schultz");
        customer2.setNumOrders(14);
        customer2.setType(CustomerType.PRIVATE);
        customer2.setRevenue(20300);
        customer2.setTest(BigDecimal.valueOf(1900.20));

        c.set(1947,11,24);
        xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        customer2.setBirthDate(xmlGregorianCalendar);

        return new ArrayList<>(Arrays.asList(customer, customer2));
    }
}
