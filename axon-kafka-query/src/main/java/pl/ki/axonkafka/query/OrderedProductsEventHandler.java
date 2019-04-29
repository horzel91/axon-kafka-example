/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.ki.axonkafka.query;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;
import pl.ki.axonkafka.api.events.OrderConfirmedEvent;
import pl.ki.axonkafka.api.events.OrderPlacedEvent;
import pl.ki.axonkafka.api.events.OrderShippedEvent;

/**
 *
 * @author Komputer
 */
@ProcessingGroup("orders")
@Service
public class OrderedProductsEventHandler {
    
    final OrderedProductDAO orderedProductDAO;

    public OrderedProductsEventHandler(OrderedProductDAO orderedProductDAO) {
        this.orderedProductDAO = orderedProductDAO;
    }
     
    @EventHandler
    public void on(OrderPlacedEvent event) {
        final OrderedProduct orderedProduct = new OrderedProduct(event.getOrderId(), event.getProduct());
        orderedProductDAO.save(orderedProduct);
    }
    
    @EventHandler
    public void on(OrderConfirmedEvent event) {
        final OrderedProduct orderedProduct = find(event.getOrderId());
        orderedProduct.setOrderConfirmed();
    }
    
    @EventHandler
    public void on(OrderShippedEvent event) {
        final OrderedProduct orderedProduct = find(event.getOrderId());
        orderedProduct.setOrderShipperd();
    }
    
    private OrderedProduct find(String orderId) {
        OrderedProduct orderedProduct = orderedProductDAO.findById(orderId)
                .orElseThrow(() -> new IllegalStateException("There is no ordered product with id " + orderId));
        return orderedProduct;
    }
    
}
