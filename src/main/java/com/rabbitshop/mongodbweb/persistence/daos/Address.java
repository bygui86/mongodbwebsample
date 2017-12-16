package com.rabbitshop.mongodbweb.persistence.daos;

import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@Document
@CompoundIndexes({
		@CompoundIndex(name = "address_idx", unique = true, def = "{ streetName: 1, streetNumber: 1, city: 1,  country: 1 }")
})
public class Address {

	@Id
	@JsonIgnore
	private ObjectId id;
	
	@NonNull
	private String streetName;
	
	private long streetNumber;
	
	@NonNull
	private String city;
	
	@NonNull
	private String country;

	@CreatedBy
	@JsonIgnore
	private String createdBy;

	@LastModifiedBy
	@JsonIgnore
	private String lastModifiedBy;

	@CreatedDate
	@JsonIgnore
	private DateTime creationDate;

	@LastModifiedDate
	@JsonIgnore
	private DateTime lastModifiedDate;
	
}
