# Axon CQRS/Event Sourcing example with Kafka
A working example of CQRS and Event Sourcing patterns created with Spring Boot and Axon Framework. This example is based on [Baeldung guide](https://www.baeldung.com/axon-cqrs-event-sourcing) and it is configured to use Apache Kafka as an event bus.

## Modules
### axon-kafka-command
A standalone module that represents a write model, which is "command" part in the CQRS pattern. Uses H2 database as default event store for DDD aggregates. In this module, domain events are published through an event bus.

### axon-kafka-query
A standalone module that represents a read model, which is "query" part in the CQRS pattern. Uses JPA and H2 database to handle queries. It handles domain events published by write model module, to update read model.

### axon-kafka-sharedkernel
The additional library used to share a common language, which is this case is domain events.

## Kafka
To make the example work You will need to install Apache Kafka (version 2.2.0) and run Kafka Server on Your localhost on a default port (9092). This can be changed in an application.properties files:  
```axon.kafka.bootstrap-servers=127.0.0.1:9092```  
