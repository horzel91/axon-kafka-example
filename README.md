# Axon CQRS/Event Sourcing example with Kafka
Working example of CQRS and Event Sourcing patterns created with Spring Boot and Axon Framework. This example is based on [Baeldung guide](https://www.baeldung.com/axon-cqrs-event-sourcing) and it is configured to use Apache Kafka as event bus.

## Modules
### axon-kafka-command
Standalone module, that represents write model, which is "command" part in CQRS pattern. Uses H2 database as default event store for DDD aggregates. In this module domain events are published through event bus.

### axon-kafka-query
Standalone module, that represents read model, which is "query" part in CQRS pattern. Uses JPA and H2 database to handle queries. It handles domain events published by write model module, to update read model.

### axon-kafka-sharedkernel
Additional library used to share common language, which is this case are domain events.

## Kafka
To make example work, You will need to install Apacha Kafka (version 2.2.0) and run Kafka Server on Your localhost on default port (9092). This can be changed in application.properties files:  
```axon.kafka.bootstrap-servers=127.0.0.1:9092```  

