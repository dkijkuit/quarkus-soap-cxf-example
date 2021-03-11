package com.github.dkijkuit.quarkus.fruit;

import javax.jws.WebService;
import javax.xml.ws.BindingType;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;

@WebService(endpointInterface = "com.github.dkijkuit.quarkus.fruit.FruitWebService")
@BindingType(javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)
public class FruitWebServiceImpl implements FruitWebService {

    private Set<Fruit> fruits = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

    public FruitWebServiceImpl() {
        fruits.add(new Fruit("Apple", "Winter fruit"));
        fruits.add(new Fruit("Pineapple", "Tropical fruit"));
    }

    @Override
    public Set<Fruit> list() {
        return fruits;
    }

    @Override
    public Set<Fruit> add(Fruit fruit) {
        fruits.add(fruit);
        return fruits;
    }

    @Override
    public Set<Fruit> delete(Fruit fruit) {
        fruits.remove(fruit);
        return fruits;
    }
}