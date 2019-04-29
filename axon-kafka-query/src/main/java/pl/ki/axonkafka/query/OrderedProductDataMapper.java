/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.ki.axonkafka.query;

import org.springframework.stereotype.Component;
import pl.ki.axonkafka.api.queries.OrderedProductData;

/**
 *
 * @author Komputer
 */
@Component
public class OrderedProductDataMapper {
    
    public OrderedProductData map(pl.ki.axonkafka.query.OrderedProduct source) {
        if (source == null) {
            return null;
        } else {
            return OrderedProductData.builder()
                    .orderId(source.getOrderId())
                    .product(source.getProduct())
                    .orderStatus(mapStatus(source.getOrderStatus()))
                    .build();
        }
    }
    
    private OrderedProductData.OrderStatus mapStatus(OrderStatus orderStatus) {
        return OrderedProductData.OrderStatus.valueOf(orderStatus.name());
    }
    
}
