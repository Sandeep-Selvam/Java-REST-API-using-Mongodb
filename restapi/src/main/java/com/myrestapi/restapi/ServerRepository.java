package com.myrestapi.restapi;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;



public interface ServerRepository extends MongoRepository<Server, Long> {
    
    Optional<List<Server>> findByNameIgnoreCase(String name);
    
}
