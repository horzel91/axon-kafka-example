/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.ki.axonkafka.query;

import pl.ki.axonkafka.api.queries.*;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 *
 * @author Komputer
 */
@EqualsAndHashCode
@ToString
@Getter
@Entity
public class OrderedProduct implements Serializable {
    
    @Id
    private final String orderId;
    private final String product;
    private OrderStatus orderStatus;

    public OrderedProduct(String orderId, String product) {
        this.orderId = orderId;
        this.product = product;
        this.orderStatus = OrderStatus.PLACED;
    }
    
    protected OrderedProduct() {
        this.orderId = null;
        this.product = null;
    }
    
    public void setOrderConfirmed() {
        this.orderStatus = OrderStatus.CONFIRMED;
    }
    
    public void setOrderShipperd() {
        this.orderStatus = OrderStatus.SHIPPED;
    }
      
}
