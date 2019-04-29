/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.ki.axonkafka.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.ki.axonkafka.api.commands.ConfirmOrderCommand;
import pl.ki.axonkafka.api.commands.PlaceOrderCommand;
import pl.ki.axonkafka.api.commands.ShipOrderCommand;
import org.springframework.web.bind.annotation.PutMapping;

/**
 *
 * @author Komputer
 */
@RestController
class OrderCommandRestController {
    
    @Value("${read-model.url}")
    private String readModelUrl;
     
    private final CommandGateway commandGateway;

    OrderCommandRestController(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
    }
        
    @PostMapping("/orders")
    ResponseEntity createOrder(@RequestBody CreateOrderRequest request) throws URISyntaxException {
        String orderId = UUID.randomUUID().toString();
        commandGateway.send(new PlaceOrderCommand(orderId, request.getProduct()));
        
        return ResponseEntity
                .created(new URI(readModelUrl + "/orders/" + orderId))
                .build();
    }
    
    @PutMapping("/orders/{orderId}/confirm")
    void confirmOrder(@PathVariable String orderId) {
        commandGateway.send(new ConfirmOrderCommand(orderId));
    }
    
    @PutMapping("/orders/{orderId}/ship")
    void shipOrder(@PathVariable String orderId) {
        commandGateway.send(new ShipOrderCommand(orderId));
    }
    
}
