package com.rabbitshop.mongodbweb.persistence.repos.impl;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.WriteResult;
import com.rabbitshop.mongodbweb.constants.MongoDBwebConstants;
import com.rabbitshop.mongodbweb.persistence.daos.User;
import com.rabbitshop.mongodbweb.persistence.repos.UserRepositoryCustom;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * MongoRepository custom implementation
 *
 * The implementation class naming convention is very strict: the name must be "<POJO_NAME>RepositoryImpl", read following link for further info
 * {@link http://docs.spring.io/spring-data/data-document/docs/current/reference/html/#repositories.custom-implementations}
 */
public class UserRepositoryImpl implements UserRepositoryCustom {

	@Resource(name = "mongoTemplate")
	@Getter(value = AccessLevel.PROTECTED)
	@Setter
	private MongoOperations mongoOperations;
	
	@Override
	public int updateUserSurnameByEmail(final String email, final String newSurname) {
		
		final Query query = new Query(Criteria.where(MongoDBwebConstants.USER_FIELDS_EMAIL).is(email));
		final Update update = new Update();
		update.set(MongoDBwebConstants.USER_FIELDS_SURNAME, newSurname);
		
		final WriteResult result = getMongoOperations().updateFirst(query, update, User.class);
		return result != null ? result.getN() : 0;
	}

}
