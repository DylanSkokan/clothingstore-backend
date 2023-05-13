package com.ics499.clothingstore.model;

import javax.persistence.Entity;

/**
 * Guest Model, used to represent a user with no account.
 * 
 * @author Dylan Skokan - Isaiah Cuellar - Tom Waterman - Justin Pham - Kyle
 *         McClernon
 *
 */

@Entity
public class Guest extends User {

	public Guest() {
		super();
	}

	@Override
	public String toString() {
		return "Guest: " + super.getUserId();
	}

}
