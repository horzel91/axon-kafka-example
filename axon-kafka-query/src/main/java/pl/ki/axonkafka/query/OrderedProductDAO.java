/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.ki.axonkafka.query;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Komputer
 */
public interface OrderedProductDAO extends JpaRepository<OrderedProduct, String> {
         
}
