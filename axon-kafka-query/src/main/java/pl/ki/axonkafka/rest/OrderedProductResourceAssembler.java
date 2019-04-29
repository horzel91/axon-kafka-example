/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.ki.axonkafka.rest;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import pl.ki.axonkafka.api.queries.OrderedProductData;

/**
 *
 * @author Komputer
 */
public class OrderedProductResourceAssembler implements ResourceAssembler<OrderedProductData, Resource<OrderedProductData>>{

    @Override
    public Resource<OrderedProductData> toResource(OrderedProductData t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
}
