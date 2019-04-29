/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.ki.axonkafka.api.queries;

import lombok.Builder;
import lombok.Value;

/**
 *
 * @author Komputer
 */
@Value
@Builder
public class OrderedProductData {
    
    public enum OrderStatus {
        PLACED, CONFIRMED, SHIPPED
    }
    
    String orderId;
    String product;
    OrderStatus orderStatus;
    
}
