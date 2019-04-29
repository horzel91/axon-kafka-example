/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.ki.axonkafka.rest;

import lombok.Data;
import lombok.Value;

/**
 *
 * @author Komputer
 */
@Data
class CreateOrderRequest {
    
    private final String product;

    public CreateOrderRequest(String product) {
        this.product = product;
    }

    private CreateOrderRequest() {
        this.product = null;
    }
   
}
