/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.ki.axonkafka.command;

import pl.ki.axonkafka.command.Order;
import java.util.UUID;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pl.ki.axonkafka.api.commands.PlaceOrderCommand;
import pl.ki.axonkafka.api.commands.ShipOrderCommand;
import pl.ki.axonkafka.api.events.OrderConfirmedEvent;
import pl.ki.axonkafka.api.events.OrderPlacedEvent;
import pl.ki.axonkafka.api.events.OrderShippedEvent;

/**
 *
 * @author Komputer
 */
public class OrderTest {
    
    private FixtureConfiguration<Order> fixture;
    
    public OrderTest() {
    }
    
    @Before
    public void setUp() {
        fixture = new AggregateTestFixture<>(Order.class);
    }

    @Test
    public void testPlaceOrderCommand() {
        final String orderId = UUID.randomUUID().toString();
        final String product = "Fine Two-handed Sword +1";
        
        fixture.givenNoPriorActivity()
                .when(new PlaceOrderCommand(orderId, product))
                .expectEvents(new OrderPlacedEvent(orderId, product));
    }

    @Test
    public void testShipOrderCommand() {
        final String orderId = UUID.randomUUID().toString();
        final String product = "Fine Two-handed Sword +1";

        fixture.given(new OrderPlacedEvent(orderId, product), new OrderConfirmedEvent(orderId))
                .when(new ShipOrderCommand(orderId))
                .expectEvents(new OrderShippedEvent(orderId));
    }
    
    @Test
    public void testShipOrderCommandWhenOrderPlaced() {
        final String orderId = UUID.randomUUID().toString();
        final String product = "Fine Two-handed Sword +1";
        
        fixture.given(new OrderPlacedEvent(orderId, product))
                .when(new ShipOrderCommand(orderId))
                .expectException(IllegalStateException.class);
    }
    
}
