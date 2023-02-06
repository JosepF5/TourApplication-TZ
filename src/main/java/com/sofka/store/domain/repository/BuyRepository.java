package com.sofka.store.domain.repository;

import com.sofka.store.domain.collections.Buy;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyRepository extends ReactiveMongoRepository<Buy,String> {
}
