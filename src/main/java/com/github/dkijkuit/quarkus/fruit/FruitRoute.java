package com.github.dkijkuit.quarkus.fruit;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.builder.RouteBuilder;

import javax.enterprise.context.ApplicationScoped;

@Slf4j
@ApplicationScoped
public class FruitRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        /**
         * This webservice client route calls it's own webservice as if it was hosted somewhere else
         */

        from("timer://fruittimer?fixedRate=true&period=5000")
                .bean("fruitWebServiceClient", "listFruit")
                .log("Timer test route for fruit service, current fruit list: ${body}")
                .end();
    }
}
