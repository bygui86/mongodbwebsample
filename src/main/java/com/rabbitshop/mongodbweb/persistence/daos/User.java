package com.rabbitshop.mongodbweb.persistence.daos;

import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
public class User {
	
	@Id
	// @JsonIgnore
	private ObjectId id;

	private String name;

	private String surname;

	@Indexed(unique = true)
	@NonNull
	private String email;

	@Field("birthday")
	private DateTime bDay;

	@Transient
	// @JsonIgnore
	private Integer age;

	@DBRef
	// @JsonIgnore
	private Address address;
	
	@CreatedBy
	// @JsonIgnore
	private String createdBy;
	
	@LastModifiedBy
	// @JsonIgnore
	private String lastModifiedBy;
	
	@CreatedDate
	// @JsonIgnore
	private DateTime creationDate;
	
	@LastModifiedDate
	// @JsonIgnore
	private DateTime lastModifiedDate;
	
	/**
	 * If birthday is not set, than it will be set to NULL by default, also after queries:
	 * mongoTemplate.findOne(Query.query(Criteria.where("name").is("Alex")), User.class).getBDay();
	 * The result will be NULL.
	 */
	@PersistenceConstructor
	public User(final ObjectId id, final String name, final String surname, final String email, @Value("#root.birthday ?: null") final DateTime bDay, final Address address) {
		
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.bDay = bDay;
		this.address = address;
	}
	
}
