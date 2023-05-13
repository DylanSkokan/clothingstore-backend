package com.ics499.clothingstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ics499.clothingstore.model.Customer;

/**
 * Customer Repository for accessing DB
 * 
 * @author Dylan Skokan - Isaiah Cuellar - Tom Waterman - Justin Pham - Kyle
 *         McClernon
 *
 */

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	@Query("SELECT c FROM Customer c WHERE c.firstName = ?1")
	Customer findByName(@Param("name") String name);

	@Query("SELECT c FROM Customer c WHERE c.email = ?1")
	Customer findByEmail(@Param("email") String email);

	@Query("SELECT c FROM Customer c WHERE c.username = ?1")
	Customer findByUsername(@Param("username") String username);

	@Modifying
	@Query("UPDATE Customer c SET c.username = :username, c.password = :password, c.firstName = :firstName, c.lastName = :lastName, c.email = :email WHERE userId = :userId")
	void updateCustomer(@Param("userId") Long userId, @Param("username") String username,
			@Param("password") String password, @Param("firstName") String firstName,
			@Param("lastName") String lastName, @Param("email") String email);

	@Query("SELECT c FROM Customer c WHERE c.userId = ?1")
	Customer findByUserId(@Param("userId") Long userId);
}
