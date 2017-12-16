package com.rabbitshop.mongodbweb.persistence.converters.impl;

import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Component;

import com.mongodb.DBObject;
import com.rabbitshop.mongodbweb.constants.MongoDBwebConstants;
import com.rabbitshop.mongodbweb.persistence.daos.Address;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@ReadingConverter
public class AddressReaderConverter implements Converter<DBObject, Address> {
	
	@Override
	public Address convert(final DBObject source) {
		
		log.debug("Converting DBObject: " + source);

		return Address.builder()
				.id(((ObjectId) source.get(MongoDBwebConstants.ADDRESS_FIELDS_ID)))
				.streetName((String) source.get(MongoDBwebConstants.ADDRESS_FIELDS_STREETNAME))
				.streetNumber((long) source.get(MongoDBwebConstants.ADDRESS_FIELDS_STREETNUMBER))
				.city((String) source.get(MongoDBwebConstants.ADDRESS_FIELDS_CITY))
				.country((String) source.get(MongoDBwebConstants.ADDRESS_FIELDS_COUNTRY))
				.build();
	}

}
