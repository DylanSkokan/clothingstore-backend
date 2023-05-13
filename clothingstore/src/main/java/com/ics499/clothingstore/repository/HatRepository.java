package com.ics499.clothingstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ics499.clothingstore.model.Hat;

/**
 * Hat Repository for accessing DB
 * 
 * @author Dylan Skokan - Isaiah Cuellar - Tom Waterman - Justin Pham - Kyle
 *         McClernon
 *
 */
public interface HatRepository extends JpaRepository<Hat, Long> {

	@Query("SELECT h FROM Hat h WHERE h.productId = ?1")
	Hat findByProductId(@Param("id") Long id);
}
