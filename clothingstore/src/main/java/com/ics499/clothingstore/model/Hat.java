package com.ics499.clothingstore.model;

import java.util.Objects;

import javax.persistence.Entity;

/**
 * Hat Model, specifies a hat in the DB
 * 
 * @author Dylan Skokan - Isaiah Cuellar - Tom Waterman - Justin Pham - Kyle
 *         McClernon
 *
 */

@Entity
public class Hat extends Product {

	public enum HatType {
		Baseball, Beanie, Bucket, Fedora, Top
	}

	private HatType type;

	public Hat(float price, int stock, String description, String brand, String color, String size, String fit,
			HatType type, String imageSource) {
		super(price, stock, description, brand, color, size, fit, imageSource);
		this.type = type;
	}

	public Hat() {

	}

	public HatType getType() {
		return type;
	}

	public void setType(HatType type) {
		this.type = type;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Hat))
			return false;
		Hat hat = (Hat) o;
		return Float.compare(hat.price, price) == 0 && stock == hat.stock
				&& Objects.equals(description, hat.description) && Objects.equals(brand, hat.brand)
				&& Objects.equals(color, hat.color) && Objects.equals(size, hat.size) && Objects.equals(fit, hat.fit)
				&& type == hat.type && Objects.equals(image_source, hat.image_source);
	}
}
