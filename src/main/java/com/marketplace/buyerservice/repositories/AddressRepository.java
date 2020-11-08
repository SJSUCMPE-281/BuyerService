package com.marketplace.buyerservice.repositories;

import com.marketplace.buyerservice.models.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address, String> {
}
