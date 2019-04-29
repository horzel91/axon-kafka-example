/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.ki.axonkafka.api.commands;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 *
 * @author Komputer
 */
@Value
public class ConfirmOrderCommand {
    
    @TargetAggregateIdentifier
    String orderId;
    
}
