package com.rabbitshop.mongodbweb.persistence.repos;

/**
 * Custom UserRepository
 *
 */
public interface UserRepositoryCustom {
	
	/**
	 * Update a User surname finding it using the email
	 *
	 * @param email
	 * @param newSurname
	 *
	 * @return Number of documents affected
	 */
	int updateUserSurnameByEmail(final String email, final String newSurname);

}
