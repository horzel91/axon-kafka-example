/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.ki.axonkafka.api.queries;

import lombok.Value;

/**
 *
 * @author Komputer
 */
@Value
public class FindOrderedProductByIdQuery {
    
    String orderId;
    
}
