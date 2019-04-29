/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.ki.axonkafka.command;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import static org.axonframework.modelling.command.AggregateLifecycle.apply;
import org.axonframework.spring.stereotype.Aggregate;
import pl.ki.axonkafka.api.commands.ConfirmOrderCommand;
import pl.ki.axonkafka.api.commands.PlaceOrderCommand;
import pl.ki.axonkafka.api.commands.ShipOrderCommand;
import pl.ki.axonkafka.api.events.OrderConfirmedEvent;
import pl.ki.axonkafka.api.events.OrderPlacedEvent;
import pl.ki.axonkafka.api.events.OrderShippedEvent;

/**
 *
 * @author Komputer
 */
@Aggregate
public class Order {
    
    @AggregateIdentifier
    private String orderId;
    private boolean orderConfirmed;
    
    protected Order() {
    }
    
    @CommandHandler
    public Order(PlaceOrderCommand command) {
        AggregateLifecycle.apply(new OrderPlacedEvent(command.getOrderId(), command.getProduct()));
    }
    
    @CommandHandler
    public void handle(ConfirmOrderCommand command) {
        apply(new OrderConfirmedEvent(orderId));
    }
    
    @CommandHandler
    public void handle(ShipOrderCommand command) {
        if (!orderConfirmed) {
            throw new IllegalStateException("Cannot ship an order which has not been confirmed yet.");
        }
        apply(new OrderShippedEvent(orderId));
    }
      
    @EventSourcingHandler
    public void on(OrderPlacedEvent event) {
        this.orderId = event.getOrderId();
        this.orderConfirmed = false;
    }
       
    @EventSourcingHandler
    public void on(OrderConfirmedEvent event) {
        this.orderConfirmed = true;
    }
    
}
