package com.rabbitshop.mongodbweb.persistence.converters.impl;

import java.util.Optional;

import javax.annotation.Resource;

import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.stereotype.Component;

import com.mongodb.DBObject;
import com.mongodb.DBRef;
import com.rabbitshop.mongodbweb.constants.MongoDBwebConstants;
import com.rabbitshop.mongodbweb.persistence.daos.Address;
import com.rabbitshop.mongodbweb.persistence.daos.User;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@ReadingConverter
public class UserReaderConverter implements Converter<DBObject, User> {

	/**
	 * This looks like a pretty weird workaround, but at the time of developing this project I was not able
	 * to find a better solution. The documentation is not covering this specific case.
	 */
	@Resource
	@Getter(value = AccessLevel.PROTECTED)
	@Setter
	protected DbRefResolver dbRefResolver;

	@Resource
	@Getter(value = AccessLevel.PROTECTED)
	@Setter
	protected AddressReaderConverter addressReaderConverter;

	@Override
	public User convert(final DBObject source) {

		log.debug("Converting DBObject: " + source);

		final DBRef addressDbRef = (DBRef) source.get(MongoDBwebConstants.USER_FIELDS_ADDRESS);

		final DateTime bDay = (DateTime) source.get(MongoDBwebConstants.USER_FIELDS_BDAY);
		return User.builder()
				.id(((ObjectId) source.get(MongoDBwebConstants.USER_FIELDS_ID)))
				.name((String) source.get(MongoDBwebConstants.USER_FIELDS_NAME))
				.surname((String) source.get(MongoDBwebConstants.USER_FIELDS_SURNAME))
				.email((String) source.get(MongoDBwebConstants.USER_FIELDS_EMAIL))
				.bDay(bDay)
				.age(calculateAge(bDay))
				.address(fetchAddress(addressDbRef))
				.build();
	}

	protected Address fetchAddress(final DBRef addressDbRef) {

		final Optional<DBObject> optAddress = fetchDbObj(addressDbRef);
		if (optAddress.isPresent()) {
			return getAddressReaderConverter().convert(optAddress.get());
		}
		return null;
	}
	
	protected Optional<DBObject> fetchDbObj(final DBRef dbRef) {

		return Optional.ofNullable(
				getDbRefResolver().fetch(dbRef));
	}

	protected Integer calculateAge(final DateTime bDay) {
		
		return bDay != null
				? Integer.valueOf(DateTime.now().getYear() - bDay.getYear())
				: null;
	}
	
}
