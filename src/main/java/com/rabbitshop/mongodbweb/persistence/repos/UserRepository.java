package com.rabbitshop.mongodbweb.persistence.repos;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.rabbitshop.mongodbweb.constants.MongoDBwebConstants;
import com.rabbitshop.mongodbweb.persistence.daos.User;

public interface UserRepository extends MongoRepository<User, ObjectId>, UserRepositoryCustom {

	User findById(final ObjectId id);

	User findById(final Object objId);

	User findById(final String hexStringId);
	
	User findByEmail(final String email);

	List<User> findByName(final String name);
	
	List<User> findByNameStartingWith(final String regexp);
	
	List<User> findByNameEndingWith(final String regexp);

	@Query(MongoDBwebConstants.QUERY_FIND_USERS_BY_REGEXP_NAME)
	List<User> findByRegexpName(final String regexp);

	/**
	 * Projections are a way to fetch only the required fields of a document from a database.
	 * This reduces the amount of data that has to be transferred from database server to client and hence increases performance.
	 * With Spring Data MongDB, projections can be used both with MongoTemplate and MongoRepository.
	 *
	 * The value=”{}” denotes no filters and hence all the documents will be fetched.
	 *
	 * @return List of {@link com.rabbitshop.mongodbweb.persistence.daos.User}
	 */
	@Query(value = MongoDBwebConstants.QUERY_VALUE_FIND_USERS_ONLY_EMAILS, fields = MongoDBwebConstants.QUERY_FIELDS_FIND_USERS_ONLY_EMAILS)
	List<User> findOnlyEmails();
	
}
