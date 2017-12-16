package com.rabbitshop.mongodbweb.persistence.repos;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.rabbitshop.mongodbweb.persistence.daos.ZipCode;

public interface ZipCodeRepository extends MongoRepository<ZipCode, ObjectId> {
	
	ZipCode findById(final ObjectId id);
	
	ZipCode findById(final Object objId);
	
	ZipCode findById(final String hexStringId);

	List<ZipCode> findByCity(final String city);

	List<ZipCode> findByState(final String state);
	
}
