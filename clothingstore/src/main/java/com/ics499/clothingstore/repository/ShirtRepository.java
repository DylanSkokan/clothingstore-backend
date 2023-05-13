package com.ics499.clothingstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ics499.clothingstore.model.Shirt;

/**
 * Shirt Repository for accessing DB
 * 
 * @author Dylan Skokan - Isaiah Cuellar - Tom Waterman - Justin Pham - Kyle
 *         McClernon
 *
 */
public interface ShirtRepository extends JpaRepository<Shirt, Long> {
	@Query("SELECT s FROM Shirt s WHERE s.productId = ?1")
	Shirt findByProductId(@Param("id") Long id);
}
