package com.rabbitshop.mongodbweb.services;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;

import com.rabbitshop.mongodbweb.persistence.daos.Address;

public interface AddressService {

	/**
	 * Insert a new Address
	 *
	 * @param address
	 *
	 * @return Inserted {@link com.rabbitshop.mongodbweb.persistence.daos.Address}
	 */
	Address insert(final Address address);

	/**
	 * Insert a new Address or Update the existing one
	 *
	 * @param address
	 *
	 * @return Inserted or Updated {@link com.rabbitshop.mongodbweb.persistence.daos.Address}
	 */
	Address insertOrUpdate(final Address address);

	/**
	 * Check if anAddress exists
	 *
	 * @param address
	 *
	 * @return TRUE if the Address exists, FALSE otherwise
	 */
	boolean exists(final Address address);
	
	/**
	 * Find all Addresses
	 *
	 * @return List of all {@link com.rabbitshop.mongodbweb.persistence.daos.Address} found
	 */
	List<Address> findAll();
	
	/**
	 * Find an Address by id (String)
	 *
	 * @param hexStringId
	 *
	 * @return {@link com.rabbitshop.mongodbweb.persistence.daos.Address}
	 */
	Optional<Address> findById(final String hexStringId);
	
	/**
	 * Find Address by example
	 *
	 * @param address
	 *
	 * @return {@link com.rabbitshop.mongodbweb.persistence.daos.Address}
	 */
	Address findByExample(final Address address);
	
	/**
	 * Delete all Addresses
	 */
	void deleteAll();

	/**
	 * Delete an Address by id (String)
	 *
	 * @param id
	 */
	void deleteById(final ObjectId id);
	
}
