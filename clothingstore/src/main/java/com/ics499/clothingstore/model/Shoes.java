package com.ics499.clothingstore.model;

import java.util.Objects;

import javax.persistence.Entity;

/**
 * Shoes model, used to represent a pair of pants in the DB.
 * 
 * @author Dylan Skokan - Isaiah Cuellar - Tom Waterman - Justin Pham - Kyle
 *         McClernon
 *
 */

@Entity
public class Shoes extends Product {

	public enum ShoesType {
		sneakers, dress, sandles, boots, slippers
	}

	private ShoesType type;

	public Shoes(float price, int stock, String description, String brand, String color, String size, String fit,
			ShoesType type, String imageSource) {
		super(price, stock, description, brand, color, size, fit, imageSource);
		this.type = type;
	}

	public Shoes() {

	}

	public ShoesType getType() {
		return type;
	}

	public void setType(ShoesType type) {
		this.type = type;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Shoes))
			return false;
		Shoes shoes = (Shoes) o;
		return Float.compare(shoes.price, price) == 0 && stock == shoes.stock
				&& Objects.equals(description, shoes.description) && Objects.equals(brand, shoes.brand)
				&& Objects.equals(color, shoes.color) && Objects.equals(size, shoes.size)
				&& Objects.equals(fit, shoes.fit) && type == shoes.type
				&& Objects.equals(image_source, shoes.image_source);
	}
}
