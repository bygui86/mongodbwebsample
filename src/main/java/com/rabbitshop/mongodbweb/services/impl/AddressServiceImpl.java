package com.rabbitshop.mongodbweb.services.impl;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.rabbitshop.mongodbweb.persistence.daos.Address;
import com.rabbitshop.mongodbweb.persistence.repos.AddressRepository;
import com.rabbitshop.mongodbweb.services.AddressService;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("addressService")
public class AddressServiceImpl implements AddressService {
	
	@Resource
	@Getter(value = AccessLevel.PROTECTED)
	@Setter
	private AddressRepository addressRepository;

	// TODO always returning an Address? if error during insert what happens?
	@Override
	public Address insert(final Address address) {
		
		log.debug("Insert new Address: " + address);

		return getAddressRepository().insert(address);
	}
	
	// TODO always returning an Address? if error during insert/update what happens?
	@Override
	public Address insertOrUpdate(final Address address) {

		log.debug("Insert or Update Address: " + address);
		
		return getAddressRepository().save(address);
	}

	@Override
	public boolean exists(final Address address) {

		log.debug("Exists Address: " + address);

		return getAddressRepository().exists(
				Example.of(address));
	}

	@Override
	public List<Address> findAll() {

		log.debug("Find all Addresses");
		
		return getAddressRepository().findAll();
	}
	
	@Override
	public Optional<Address> findById(final String hexStringId) {

		log.debug("Find Address by id: " + hexStringId);
		
		return Optional.ofNullable(
				getAddressRepository().findById(hexStringId));
	}

	@Override
	public Address findByExample(final Address address) {

		log.debug("Find Address by example: " + address);
		
		return getAddressRepository().findOne(
				Example.of(address));
	}

	@Override
	public void deleteAll() {

		log.debug("Delete all Addresses");
		
		getAddressRepository().deleteAll();
	}

	@Override
	public void deleteById(final ObjectId id) {

		log.debug("Delete Address by id: " + id.toHexString());

		getAddressRepository().delete(id);
	}
	
}
