package com.rabbitshop.mongodbweb.facades.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import com.rabbitshop.mongodbweb.dtos.AddressDto;
import com.rabbitshop.mongodbweb.facades.AddressFacade;
import com.rabbitshop.mongodbweb.mappers.AddressMapper;
import com.rabbitshop.mongodbweb.persistence.daos.Address;
import com.rabbitshop.mongodbweb.services.AddressService;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component(value = "addressFacade")
public class AddressFacadeImpl implements AddressFacade {
	
	@Resource
	@Getter(value = AccessLevel.PROTECTED)
	@Setter(value = AccessLevel.PROTECTED)
	private AddressService addressService;

	@Resource
	@Getter(value = AccessLevel.PROTECTED)
	@Setter(value = AccessLevel.PROTECTED)
	private AddressMapper addressMapper;

	@Override
	public List<AddressDto> getAll() {
		
		log.debug("Get all Addresses");
		
		return bulkConvertToDto(
				getAddressService().findAll());
	}

	@Override
	public Optional<AddressDto> getById(final String hexStringId) {
		
		log.debug("Get Address by id: " + hexStringId);

		return Optional.ofNullable(
				convertToDto(
						getAddressService().findById(hexStringId).orElse(null)));
	}

	@Override
	public Optional<AddressDto> create(final AddressDto addressDtoIn) {

		log.debug("Create new Address: " + addressDtoIn);
		
		return Optional.ofNullable(
				convertToDto(
						getAddressService().insert(
								convertToDao(addressDtoIn))));
	}

	@Override
	public void removeAll() {
		
		log.debug("Remove all Addresses");
		
		getAddressService().deleteAll();
	}
	
	@Override
	public void removeById(final String hexStringId) {
		
		log.debug("Remove Address by id: " + hexStringId);
		
		getAddressService().deleteById(new ObjectId(hexStringId));
	}
	
	protected AddressDto convertToDto(final Address addressDao) {
		
		return addressDao != null
				? getAddressMapper().mapToDto(addressDao)
				: null;
	}
	
	protected List<AddressDto> bulkConvertToDto(final List<Address> addressDaos) {

		final ArrayList<AddressDto> addressDtos = new ArrayList<>();
		addressDaos.forEach(
				a -> addressDtos.add(convertToDto(a)));
		return addressDtos;
	}

	protected Address convertToDao(final AddressDto addressDto) {
		
		return addressDto != null
				? getAddressMapper().mapToDao(addressDto)
				: null;
	}

	protected List<Address> bulkConvertToDao(final List<AddressDto> addressDtos) {

		final ArrayList<Address> addressDaos = new ArrayList<>();
		addressDtos.forEach(
				a -> addressDaos.add(convertToDao(a)));
		return addressDaos;
	}

}
