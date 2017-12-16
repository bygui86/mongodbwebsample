package com.rabbitshop.mongodbweb.facades;

import java.util.List;
import java.util.Optional;

import com.rabbitshop.mongodbweb.dtos.AddressDto;

public interface AddressFacade {
	
	List<AddressDto> getAll();

	Optional<AddressDto> getById(final String hexStringId);

	Optional<AddressDto> create(final AddressDto addressDtoIn);
	
	void removeAll();
	
	void removeById(final String hexStringId);

}
