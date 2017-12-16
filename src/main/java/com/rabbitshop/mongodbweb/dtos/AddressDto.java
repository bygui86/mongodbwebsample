package com.rabbitshop.mongodbweb.dtos;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rabbitshop.mongodbweb.constants.MongoDBwebConstants;
import com.rabbitshop.mongodbweb.utils.JsonUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Slf4j
public class AddressDto {

	private String id;

	@NonNull
	private String street;

	@NonNull
	private String city;

	@NonNull
	private String country;
	
	@Override
	public String toString() {
		
		return parseToJson(false);
	}
	
	public String toStringPrettyPrint() {
		
		return parseToJson(true);
	}
	
	protected String parseToJson(final boolean prettyPrint) {
		
		try {
			return JsonUtils.serializeObjectToJsonString(this, prettyPrint);
			
		} catch (final JsonProcessingException e) {
			log.error("Error converting Address[id:" + getId() + ", street:" + getStreet() +
					", city:" + getCity() + ", country:" + getCountry() + "] :"
					+ e.getMessage() + MongoDBwebConstants.LOG_NEWLINE
					+ "Returning empty String to avoid possible NullPointerException");
		}
		return MongoDBwebConstants.EMPTY_STRING;
	}

}
