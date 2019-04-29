/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.ki.axonkafka.query;

import pl.ki.axonkafka.api.queries.OrderedProductData;
import java.util.List;
import java.util.stream.Collectors;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;
import pl.ki.axonkafka.api.queries.FindAllOrderedProductsQuery;
import pl.ki.axonkafka.api.queries.FindOrderedProductByIdQuery;

/**
 *
 * @author Komputer
 */
@Service
public class OrderedProductsQueryHandler {
    
    final OrderedProductDAO orderedProductDAO;
    final OrderedProductDataMapper orderedProductDatamapper;

    public OrderedProductsQueryHandler(OrderedProductDAO orderedProductDAO, OrderedProductDataMapper orderedProductDatamapper) {
        this.orderedProductDAO = orderedProductDAO;
        this.orderedProductDatamapper = orderedProductDatamapper;
    }
        
    @QueryHandler
    public List<OrderedProductData> handle(FindAllOrderedProductsQuery query) {
        return orderedProductDAO.findAll()
                .stream()
                .map(orderedProductDatamapper::map)
                .collect(Collectors.toList());
    }
    
    @QueryHandler
    public OrderedProductData handle(FindOrderedProductByIdQuery query) {
        return orderedProductDAO.findById(query.getOrderId())
                .map(orderedProductDatamapper::map)
                .orElse(null);
    }
    
}
