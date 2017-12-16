package com.rabbitshop.mongodbweb.persistence.repos;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.rabbitshop.mongodbweb.persistence.daos.Address;

public interface AddressRepository extends MongoRepository<Address, ObjectId> {
	
	Address findById(final String hexStringId);

	List<Address> findByStreetName(final String streetName);
	
	List<Address> findByCity(final String city);
	
	List<Address> findByCountry(final String country);
	
}
