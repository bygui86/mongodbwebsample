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
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@Document
@CompoundIndexes({
		@CompoundIndex(name = "zipcode_idx", unique = true, def = "{ zipCode: 1, city: 1, state: 1 }")
})
public class ZipCode {
	
	@Id
	@JsonIgnore
	private ObjectId id;

	@JsonProperty("_id")
	@NonNull
	private String zipCode;
	
	@NonNull
	private String city;
	
	@JsonProperty("loc")
	private Double[] location;
	
	@JsonProperty("pop")
	private Long population;
	
	@NonNull
	private String state;

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
