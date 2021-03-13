package com.github.dkijkuit.quarkus.fruit;

import io.quarkus.runtime.annotations.RegisterForReflection;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Set;

@ApplicationScoped
@Named("fruitWebServiceClient")
@RegisterForReflection
public class FruitWebServiceClient {
    @Inject
    FruitWebService clientService;

    public Set<Fruit> listFruit() {
        return clientService.list();
    }
}
