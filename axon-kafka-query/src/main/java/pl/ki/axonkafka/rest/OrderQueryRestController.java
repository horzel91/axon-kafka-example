/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.ki.axonkafka.rest;

import pl.ki.axonkafka.api.queries.OrderedProductData;
import java.util.List;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.ki.axonkafka.api.queries.FindAllOrderedProductsQuery;
import pl.ki.axonkafka.api.queries.FindOrderedProductByIdQuery;

/**
 *
 * @author Komputer
 */
@RestController
class OrderQueryRestController {
    
    private final QueryGateway queryGateway;

    OrderQueryRestController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }
    
    @GetMapping("/orders/{orderId}")
    OrderedProductData one(@PathVariable String orderId) {
        OrderedProductData orderedProduct = queryGateway.query(new FindOrderedProductByIdQuery(orderId), OrderedProductData.class).join();
        if (orderedProduct == null) {
            throw new OrderNotFoundException(orderId);
        } 
        return orderedProduct;
    }
    
    @GetMapping("/orders")
    List<OrderedProductData> all() {
        return queryGateway.query(new FindAllOrderedProductsQuery(), 
                ResponseTypes.multipleInstancesOf(OrderedProductData.class)).join();
    }
          
}
