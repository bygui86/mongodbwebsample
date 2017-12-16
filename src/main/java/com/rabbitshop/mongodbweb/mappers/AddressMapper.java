package com.rabbitshop.mongodbweb.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.rabbitshop.mongodbweb.dtos.AddressDto;
import com.rabbitshop.mongodbweb.persistence.daos.Address;

@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN)
public interface AddressMapper {

	AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);
	
	/**
	 * Convert an Address (DAO) to an AddressDTO
	 *
	 * @param dao
	 *
	 * @return {@link com.rabbitshop.mongodbweb.dtos.AddressDto}
	 */
	@Mapping(target = "id", expression = "java( dao.getId().toHexString() )")
	@Mapping(target = "street", expression = "java( dao.getStreetName() + \" \" + dao.getStreetNumber() )")
	AddressDto mapToDto(Address dao);

	/**
	 * Convert an AddressDTO to an Address (DAO)
	 *
	 * @param dto
	 *
	 * @return {@link com.rabbitshop.mongodbweb.persistence.daos.Address}
	 */
	@Mapping(target = "id", expression = "java( dto.getId() != null ? new org.bson.types.ObjectId(dto.getId()) : null )")
	@Mapping(target = "createdBy", ignore = true)
	@Mapping(target = "lastModifiedBy", ignore = true)
	@Mapping(target = "creationDate", ignore = true)
	@Mapping(target = "lastModifiedDate", ignore = true)
	@Mapping(target = "streetName", expression = "java( dto.getStreet().split(\" \")[0].trim() )")
	@Mapping(target = "streetNumber", expression = "java( Long.valueOf( dto.getStreet().split(\" \")[1].trim() ) )")
	@InheritInverseConfiguration
	Address mapToDao(AddressDto dto);
	
}
